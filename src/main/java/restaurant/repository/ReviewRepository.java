package restaurant.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restaurant.domain.Restaurant;
import restaurant.domain.Review;
import restaurant.domain.User;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    Review findByRatedRestaurantAndRatedUser(Restaurant restaurant, User user);
    List<Review> findByRatedRestaurant(Restaurant restaurant);

}
