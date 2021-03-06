package restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import restaurant.auth.DBUserDetailsService;
import restaurant.auth.UserRole;
import restaurant.domain.Restaurant;
import restaurant.domain.User;
import restaurant.dto.EditRestaurant;
import restaurant.dto.RestaurantSearchParams;
import restaurant.service.RestaurantService;
import restaurant.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;


@Controller
@PropertySource("classpath:key.properties")
public class RestaurantController {

    @Value("${google.maps.api.key}")
    private String googleAPIKey;

    @Autowired
    MessageSource messageSource;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String getSearchForm(@ModelAttribute("search") RestaurantSearchParams restaurantSearchParams) {
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    String handleSearchForm(@Valid @ModelAttribute("search") RestaurantSearchParams restaurantSearchParams,
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        model.addAttribute("restaurants", restaurantService.getRestaurants(restaurantSearchParams));

        return "index";
    }

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    String getRestaurantMapPage(Model model) {
        model.addAttribute("apiKey", googleAPIKey);
        return "map";
    }

    @RequestMapping(value = "/map/data", method = RequestMethod.GET)
    ResponseEntity<List<Restaurant>> getRestaurantData() {
        Locale locale = LocaleContextHolder.getLocale();
        List<Restaurant> restaurants = restaurantService.getByAllowed(true);
        restaurants.stream().forEach(restaurant -> restaurant.setPriceCategoryString(messageSource.getMessage(restaurant.getPriceCategory().toString(), null, locale)));
        return new ResponseEntity<List<Restaurant>>(restaurants, HttpStatus.OK);
    }

    @RequestMapping(value = "/newRestaurant", method = RequestMethod.GET)
    String newRestaurant(@ModelAttribute("newRest") EditRestaurant restaurant) {
        return "new_restaurant";
    }

    @RequestMapping(value = "/newRestaurant", method = RequestMethod.POST)
    String handleNewRestaurant(@Valid @ModelAttribute("newRest") EditRestaurant restaurant,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("result", "error");
            return "new_restaurant";
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser = userService.getByEmail(username);
        restaurantService.addRestaurant(loggedInUser,restaurant);
        model.addAttribute("result", "success");
        model.addAttribute("newRest", new Restaurant());
        return "new_restaurant";
    }

    @RequestMapping(value = "/admin/editRestaruants", method = RequestMethod.GET)
    String editRestaurants(Model model) {
        List<Restaurant> allRest = restaurantService.getAllRestaurant();
        List<Restaurant> pendingRest = restaurantService.getByAllowed(false);
        model.addAttribute("allRest", allRest);
        model.addAttribute("pendingRest", pendingRest);
        return "edit_restaurants";
    }

    @RequestMapping(value = "/edit/restaurant/{id}", method = RequestMethod.GET)
    String editRestaurant(Model model, @PathVariable("id") long restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        EditRestaurant er = restaurantService.createEditRestaurantFromRestaurant(restaurant);
        model.addAttribute("restaurant", er);
        return "edit_rest";
    }

    @RequestMapping(value = "/edit/restaurant/{id}", method = RequestMethod.POST)
    String handleEditRestaurant(@Valid @ModelAttribute("restaurant") EditRestaurant restaurant,
                                BindingResult bindingResult, Model model,
                                @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("restaurant", restaurant);
            return "edit_rest";
        }
        restaurantService.updateRestaurant(restaurant, id);
        List<Restaurant> allRest = restaurantService.getAllRestaurant();
        List<Restaurant> pendingRest = restaurantService.getByAllowed(false);
        model.addAttribute("allRest", allRest);
        model.addAttribute("pendingRest", pendingRest);
        return "edit_restaurants";
    }



    @RequestMapping(value = "/delete/restaurant", method = RequestMethod.DELETE)
    @ResponseBody
    String handleDeleteRestaurant(Model model, @RequestParam("id") long id) {
        restaurantService.deleteRestaurantById(id);
        List<Restaurant> allRest = restaurantService.getAllRestaurant();
        List<Restaurant> pendingRest = restaurantService.getByAllowed(false);
        model.addAttribute("allRest", allRest);
        model.addAttribute("pendingRest", pendingRest);
        return "edit_restaurants";
    }

    @RequestMapping(value = "/restaurant/{id}", method = RequestMethod.GET)
    String getRestaurant(Model model, @PathVariable("id") long id) {
        Restaurant rest = restaurantService.getRestaurantById(id);
        if (rest == null || !rest.getAllowed()) {
            return "badRestaurant";
        }
        model.addAttribute("rest", rest);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return "restaurant_details";
    }
}
