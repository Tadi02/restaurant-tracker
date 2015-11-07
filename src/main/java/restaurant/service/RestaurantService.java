package restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restaurant.domain.Restaurant;
import restaurant.repository.RestaurantRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants(){
        return StreamSupport.stream(restaurantRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

}
