package web.controller;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.User;
import web.services.UserService;

@Controller
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String getUsersTable(Model model) {
        model.addAttribute("allUsersTable", service.getAllUsers());
        return "users-view";
    }

    @GetMapping("/new")
    public String getUserCreateForm(@ModelAttribute User user) {
        return "user-modify-form";
    }

    @GetMapping("/update/{id}")
    public String getUserUpdateForm(@PathVariable Long id, Model model) {
        return service.getUserById(id).map(model::addAttribute).isPresent()
            ? "user-modify-form" : "redirect:/";
    }

    @PostMapping("/new/add")
    public String addUser(@Valid @ModelAttribute("user") User user,
        BindingResult result) {
        if (result.hasErrors()) {
            return "user-modify-form";
        }

        Optional.ofNullable(user.getId())
            .ifPresentOrElse(x -> service.updateUserById(user),
                () -> service.addUser(user));

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        service.deleteUserById(id);
        return "redirect:/";
    }
}
