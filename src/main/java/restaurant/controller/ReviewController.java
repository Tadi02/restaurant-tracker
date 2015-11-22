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

/**
 * Created by Máté on 2015.11.22..
 */

@Controller
public class ReviewController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @RequestMapping(value = "/remote/restaurant/{id}/saverating", method = RequestMethod.POST)
    @ResponseBody
    String saveRating(@RequestParam("rateType") String rateType,
                      @RequestParam("rateScore") int rateScore,
                      @PathVariable("id") long restaurantId){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = userRepository.findByEmail(username);

        Restaurant restaurant = restaurantRepository.findOne(restaurantId);
        Review review = reviewRepository.findByRatedRestaurantAndRatedUser(restaurant,loggedInUser);
        if(review == null){
            review = new Review();
        }
        review.setRatedUser(loggedInUser);
        review.setRatedRestaurant(restaurant);
        String state = "ok";
        switch (rateType){
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
                state="error";
        }
        reviewRepository.save(review);
    //ToDO recount avg reviews
        return "[\"saved\"]";
    }
}
