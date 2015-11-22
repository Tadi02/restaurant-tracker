package restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import restaurant.domain.Restaurant;
import restaurant.domain.Review;
import restaurant.domain.User;
import restaurant.repository.RestaurantRepository;
import restaurant.repository.ReviewRepository;
import restaurant.repository.UserRepository;

import java.util.List;

@Controller
public class ReviewController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @RequestMapping(value = "/remote/restaurant/{id}/saverating", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    String saveRating(@RequestParam("rateType") String rateType,
                      @RequestParam("rateScore") int rateScore,
                      @PathVariable("id") long restaurantId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = userRepository.findByEmail(username);

        Restaurant restaurant = restaurantRepository.findOne(restaurantId);
        Review review = reviewRepository.findByRatedRestaurantAndRatedUser(restaurant, loggedInUser);
        if (review == null) {
            review = new Review();
        }
        review.setRatedUser(loggedInUser);
        review.setRatedRestaurant(restaurant);
        String state = "ok";
        switch (rateType) {
            case "envir":
                review.setEnvironment(rateScore);
                break;
            case "speedServ":
                review.setSpeedOfService(rateScore);
                break;
            case "dish":
                review.setValueOfMeal(rateScore);
                break;
            case "serv":
                review.setValueOfService(rateScore);
                break;
            default:
                state = "error";
        }
        reviewRepository.save(review);

        List<Review> reviewList = reviewRepository.findByRatedRestaurant(restaurant);

        int countEnvir = 0;
        int countSpeedServ = 0;
        int countDish = 0;
        int countServ = 0;
        int sumEnvir = 0;
        int sumSpeedServ = 0;
        int sumDish = 0;
        int sumServ = 0;

        for(Review r : reviewList){
            if(r.getSpeedOfService()>0){
                countSpeedServ++;
                sumSpeedServ+=r.getSpeedOfService();
            }
            if(r.getEnvironment()>0){
                countEnvir++;
                sumEnvir+=r.getEnvironment();
            }
            if(r.getValueOfMeal()>0){
                countDish++;
                sumDish+=r.getValueOfMeal();
            }
            if(r.getValueOfService()>0) {
                countServ++;
                sumServ+=r.getValueOfService();
            }
        }
        if(countEnvir>0)
            restaurant.setEnvironmentScore(sumEnvir/countEnvir);
        if(countSpeedServ>0)
            restaurant.setSpeedOfServiceScore(sumSpeedServ/countSpeedServ);
        if(countDish>0)
            restaurant.setValueOfMealScore(sumDish/countDish);
        if(countServ>0)
            restaurant.setValueOfServiceScore(sumServ/countServ);

        restaurantRepository.save(restaurant);

        String result="[\""+restaurant.getEnvironmentScore()+"\"," +
                "\""+restaurant.getSpeedOfServiceScore()+"\"," +
                "\""+restaurant.getValueOfMealScore()+"\","+
        "\""+restaurant.getValueOfServiceScore()+"\"]";

        return result;
    }
}
