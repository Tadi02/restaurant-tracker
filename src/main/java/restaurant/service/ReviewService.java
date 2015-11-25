package restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurant.domain.Restaurant;
import restaurant.domain.Review;
import restaurant.domain.User;
import restaurant.repository.ReviewRepository;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    private RestaurantService restaurantService;

    public List<Review> getByRatedRestaurant(Restaurant restaurant) {
        return reviewRepository.findByRatedRestaurant(restaurant);
    }

    public void saveReview(Review review) {
        reviewRepository.save(review);
    }

    public Review getByRatedRestaurantAndRatedUser(User loggedInUser, Restaurant restaurant) {
        return reviewRepository.findByRatedRestaurantAndRatedUser(restaurant, loggedInUser);
    }

    public String updateReview(User loggedInUser, Restaurant restaurant, String rateType, int rateScore) {
        Review review = getByRatedRestaurantAndRatedUser(loggedInUser, restaurant);
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
        saveReview(review);

        List<Review> reviewList = getByRatedRestaurant(restaurant);

        int countEnvir = 0;
        int countSpeedServ = 0;
        int countDish = 0;
        int countServ = 0;
        int sumEnvir = 0;
        int sumSpeedServ = 0;
        int sumDish = 0;
        int sumServ = 0;

        for (Review r : reviewList) {
            if (r.getSpeedOfService() > 0) {
                countSpeedServ++;
                sumSpeedServ += r.getSpeedOfService();
            }
            if (r.getEnvironment() > 0) {
                countEnvir++;
                sumEnvir += r.getEnvironment();
            }
            if (r.getValueOfMeal() > 0) {
                countDish++;
                sumDish += r.getValueOfMeal();
            }
            if (r.getValueOfService() > 0) {
                countServ++;
                sumServ += r.getValueOfService();
            }
        }
        if (countEnvir > 0)
            restaurant.setEnvironmentScore(sumEnvir / countEnvir);
        if (countSpeedServ > 0)
            restaurant.setSpeedOfServiceScore(sumSpeedServ / countSpeedServ);
        if (countDish > 0)
            restaurant.setValueOfMealScore(sumDish / countDish);
        if (countServ > 0)
            restaurant.setValueOfServiceScore(sumServ / countServ);

        restaurantService.saveRestaurant(restaurant);

        String result = "[\"" + restaurant.getEnvironmentScore() + "\"," +
                "\"" + restaurant.getSpeedOfServiceScore() + "\"," +
                "\"" + restaurant.getValueOfMealScore() + "\"," +
                "\"" + restaurant.getValueOfServiceScore() + "\"]";

        return result;
    }

}

