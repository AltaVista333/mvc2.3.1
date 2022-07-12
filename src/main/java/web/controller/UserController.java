/* (C)2022 */
package web.controller;

import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.services.UserService;

@Controller
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @ModelAttribute("users")
    public List<User> add() {
        return service.getAllUsers();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String allUsers(ModelMap model) {
        //model.addAttribute("users", service.getAllUsers());
        return "users-view";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String addOrUpdateUser(
        @Valid @ModelAttribute(value = "user") User user,
        BindingResult bindingResult) {


            if(!bindingResult.hasErrors()) {
                Optional.ofNullable(user.getId())
                    .ifPresentOrElse(
                        x -> service.updateUserById(user),
                        () -> service.addUser(user));
            }

        return "users-view";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam Long userId, ModelMap modelMap) {
        service.deleteUserById(userId);
        modelMap.addAttribute("users", service.getAllUsers());
        return "qwe/users-view";
    }
}
