package restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import restaurant.domain.Restaurant;
import restaurant.service.RestaurantService;

import java.util.List;

@Controller
@PropertySource("classpath:key.properties")
public class RestaurantController {

    @Value("${google.maps.api.key}")
    private String googleAPIKey;

    @Autowired
    RestaurantService restaurantService;

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    String getRestaurantMapPage(Model model){
        model.addAttribute("apiKey",googleAPIKey);
        return "map";
    }

    @RequestMapping(value = "/map/data", method = RequestMethod.GET)
    ResponseEntity<List<Restaurant>> getRestaurantData(){
        return new ResponseEntity<List<Restaurant>>(restaurantService.getAllRestaurants(), HttpStatus.OK);
    }

}
