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


    public List<Review> getByRatedRestaurant(Restaurant restaurant) {
        return reviewRepository.findByRatedRestaurant(restaurant);
    }

    public void saveReview(Review review) {
        reviewRepository.save(review);
    }

    public Review getByRatedRestaurantAndRatedUser(User loggedInUser, Restaurant restaurant) {
        return reviewRepository.findByRatedRestaurantAndRatedUser(restaurant, loggedInUser);
    }

}

