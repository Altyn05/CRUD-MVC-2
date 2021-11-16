package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.annotation.PostConstruct;


@Controller
@RequestMapping("/users")
public class UserController {

    @PostConstruct
    public void loadTestUser() {

        User user1 = new User("Ivan", "Ivanov", (byte) 20);
        User user2 = new User("Andrei", "Andreev", (byte) 21);
        User user3 = new User("Kolia", "Nikolaev", (byte) 22);
        User user4 = new User("Petr", "Petrov", (byte) 23);

        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
        userService.addUser(user4);
    }

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String allUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/all";
    }

    @GetMapping(value = "/new")
    public String add(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/edit/{id}")
    public String editUser(ModelMap model, @PathVariable("id") int id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "users/edit";
    }

    @PutMapping(value = "{id}")
    public String edit(@ModelAttribute("user") User user) {
        userService.upDateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}