package restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import restaurant.domain.User;
import restaurant.dto.RegisteringUser;
import restaurant.exception.NotFoundException;
import restaurant.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

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

    @RequestMapping(value = "/admin/users")
    String getUserAdminPage(Model model, @RequestParam(value = "page", required = false, defaultValue = "0") int page){
        Page<User> users = userService.getUsersPage(page);
        boolean isLast = false;
        if(users.getTotalPages() == (page + 1)){
            isLast = true;
        }
        model.addAttribute("users",users);
        model.addAttribute("isLast",isLast);
        model.addAttribute("current",page);
        return "users";
    }

    @RequestMapping(value = "/admin/users/{id}", method = RequestMethod.GET)
    String getUserEditingForm(@ModelAttribute("user") User user, @PathVariable("id") long id, Model model){
        user = userService.getUser(id);
        if(user == null){
            throw new NotFoundException();
        }
        model.addAttribute("user",user);
        return "user_edit";
    }

    @RequestMapping(value = "/admin/users/{id}", method = RequestMethod.POST)
    String handleUserEditingForm(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, @PathVariable("id") long id, Model model){
        if(bindingResult.hasErrors()){
            return "user_edit";
        }
        user = userService.updateUser(id,user);
        model.addAttribute("success",true);
        return "user_edit";
    }
}
