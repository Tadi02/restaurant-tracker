package restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import restaurant.dto.RegisteringUser;
import restaurant.service.UserService;

import javax.validation.Valid;

@Controller
public class SecurityController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login")
    String getLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    String getRegisterForm(@ModelAttribute("user") RegisteringUser user) {

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    String handleRegistrationForm(@Valid @ModelAttribute("user") RegisteringUser user, BindingResult bindingResult) {
        synchronized (this) {
            if (!userService.isEmailAvailable(user.getEmail())) {
                bindingResult.rejectValue("email", "EmailUsed");
            }
            if (!bindingResult.hasErrors()) {
                userService.registerUser(user);
            }else{
                return "register";
            }
        }
        return "redirect:/register/success";
    }


    @RequestMapping(value = "/register/success", method = RequestMethod.GET)
    String getRegisterSuccessPage() {

        return "registration_success";
    }

}
