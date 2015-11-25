package restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import restaurant.auth.UserRole;
import restaurant.domain.Restaurant;
import restaurant.domain.User;
import restaurant.dto.EditRestaurant;
import restaurant.dto.RestaurantSearchParams;
import restaurant.repository.RestaurantRepository;

import javax.validation.Valid;
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

    public Restaurant saveRestaurant(Restaurant newRestaurant) {
        return restaurantRepository.save(newRestaurant);
    }

    public Restaurant updateRestaurant(EditRestaurant restaurant, long restaurantId) {
        Restaurant newRestaurant = getRestaurantById(restaurantId);
        newRestaurant.setId(restaurant.getId());
        newRestaurant.setAddress(restaurant.getAddress());
        newRestaurant.setDescription(restaurant.getDescription());
        try {
            newRestaurant.setGpsCoordX(Float.valueOf(restaurant.getGpsCoordX()));
        }catch (NumberFormatException ex){
            newRestaurant.setGpsCoordX(0);
        }
        try{
            newRestaurant.setGpsCoordY(Float.valueOf(restaurant.getGpsCoordY()));
        }catch (NumberFormatException ex){
            newRestaurant.setGpsCoordY(0);
        }
        newRestaurant.setName(restaurant.getName());
        newRestaurant.setPhoneNumber(restaurant.getPhoneNumber());
        newRestaurant.setPriceCategory(restaurant.getPriceCategory());
        newRestaurant.setUrl(restaurant.getUrl());
        newRestaurant.setAllowed(restaurant.isAllowed());

        newRestaurant = saveRestaurant(newRestaurant);
        return newRestaurant;
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

    public EditRestaurant createEditRestaurantFromRestaurant(Restaurant restaurant){
        EditRestaurant er = new EditRestaurant();
        er.setAddress(restaurant.getAddress());
        er.setDescription(restaurant.getDescription());
        er.setGpsCoordX(String.valueOf(restaurant.getGpsCoordX()));
        er.setGpsCoordY(String.valueOf(restaurant.getGpsCoordY()));
        er.setName(restaurant.getName());
        er.setPhoneNumber(restaurant.getPhoneNumber());
        er.setPriceCategory(restaurant.getPriceCategory());
        er.setUrl(restaurant.getUrl());
        er.setId(restaurant.getId());
        er.setAllowed(restaurant.getAllowed());
        return er;
    }

    public Restaurant addRestaurant(User loggedInUser, EditRestaurant restaurant){
        if (loggedInUser.getPermissionLevel() == UserRole.ROLE_ADMIN) {
            restaurant.setAllowed(true);
        } else {
            restaurant.setAllowed(false);
        }
        Restaurant newRestaurant = new Restaurant();
        newRestaurant.setId(restaurant.getId());
        newRestaurant.setAddress(restaurant.getAddress());
        newRestaurant.setDescription(restaurant.getDescription());
        try {
            newRestaurant.setGpsCoordX(Float.valueOf(restaurant.getGpsCoordX()));
        } catch (NumberFormatException ex) {
            newRestaurant.setGpsCoordX(0);
        }
        try {
            newRestaurant.setGpsCoordY(Float.valueOf(restaurant.getGpsCoordY()));
        } catch (NumberFormatException ex) {
            newRestaurant.setGpsCoordY(0);
        }
        newRestaurant.setName(restaurant.getName());
        newRestaurant.setPhoneNumber(restaurant.getPhoneNumber());
        newRestaurant.setPriceCategory(restaurant.getPriceCategory());
        newRestaurant.setUrl(restaurant.getUrl());
        newRestaurant.setAllowed(restaurant.isAllowed());

        return saveRestaurant(newRestaurant);
    }
}
