package web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.model.UserDto;
import web.model.User;
import web.services.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService service;
    private final ModelMapper modelMapper;

    public UserController(UserService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userDto")
    public UserDto newMyForm() {
        return new UserDto();
    }

    @RequestMapping(value = "/")
    public String allUsers( ModelMap model) {
        model.addAttribute("users", service.getAllUsers());
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String addOrUpdateUser(@Valid @ModelAttribute(value = "userDto") UserDto userDto,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
           redirectAttributes
                   .addFlashAttribute("org.springframework.validation.BindingResult.userDto"
                           ,bindingResult);
           redirectAttributes.addFlashAttribute("userDto", userDto);
        } else {
            if(userDto.getId() == null) {
                User user = modelMapper.map(userDto, User.class);
                service.addUser(user);
            } else {
                service.updateUserById(userDto);
            }
        }
        return "redirect:/";
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam String userId, ModelMap modelMap) {
        service.deleteUserById(Long.parseLong(userId));
        modelMap.addAttribute("users", service.getAllUsers());
        return "redirect:/";
    }
}
