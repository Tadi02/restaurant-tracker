package restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurant.domain.Restaurant;
import restaurant.dto.RestaurantSearchParams;
import restaurant.repository.RestaurantRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static restaurant.service.RestaurantSpecification.restaurantSearch;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants(){
        return StreamSupport.stream(restaurantRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public List<Restaurant> getRestaurants(RestaurantSearchParams restaurantSearchParams) {
        return restaurantRepository.findAll(restaurantSearch(restaurantSearchParams));
    }

    public List<Restaurant> getByAllowed(boolean allow) {
        return restaurantRepository.findByAllowed(allow);
    }

    public void saveRestaurant(Restaurant newRestaurant) {
        restaurantRepository.save(newRestaurant);
    }

    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(long restaurantId) {
        return restaurantRepository.findOne(restaurantId);
    }
    public void deleteRestaurantById(long id) {
        restaurantRepository.delete(id);
    }
}
