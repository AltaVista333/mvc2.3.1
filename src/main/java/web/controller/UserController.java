/* (C)2022 */
package web.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String getUsersTable(Model model,
        RedirectAttributes redirectAttributes) {
        model.addAttribute("allUsersTable", service.getAllUsers());
       // model.addAttribute("errs",  model.getAttribute("errors"));

        return "users-view";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute @Validated User user,
        BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {

            List<String> errors = result.getAllErrors().stream()
                .map(x -> x.unwrap(ConstraintViolation.class))
                .map(ConstraintViolation::getMessage).toList();
            attributes.addFlashAttribute("errors", errors);

        } else {
            Optional.ofNullable(user.getId())
                .ifPresentOrElse(x -> service.updateUserById(user),
                    () -> service.addUser(user));
        }
        return "redirect:/";
    }
}
