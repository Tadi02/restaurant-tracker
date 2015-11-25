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
import restaurant.service.RestaurantService;
import restaurant.service.ReviewService;
import restaurant.service.UserService;

import java.util.List;

@Controller
public class ReviewController {
    @Autowired
    UserService userService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    ReviewService reviewService;

    @RequestMapping(value = "/remote/restaurant/{id}/saverating", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    String saveRating(@RequestParam("rateType") String rateType,
                      @RequestParam("rateScore") int rateScore,
                      @PathVariable("id") long restaurantId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = userService.getUserByEmail(username);
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        String result = reviewService.updateReview(loggedInUser, restaurant, rateType, rateScore);

        return result;
    }

}
