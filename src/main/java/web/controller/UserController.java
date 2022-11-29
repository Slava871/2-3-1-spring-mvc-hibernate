package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImpl;


import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    //shows all users
    @GetMapping(value ="/")
    public String showAllUsers(Model model) {
        List<User> totalList = userService.allUsers();
        model.addAttribute("totalList", totalList);
        return "allUsers";
    }

    //edit user
    @GetMapping(value ="/users/edit/{id}")
    public String editPage(@PathVariable(value="id") int id, Model model) {
        User usr = userService.getById(id);
        model.addAttribute("eduser", usr);
        return "edit";
    }
    //edit user
    @PatchMapping(value ="/users/edit/{id}")
    public String editUser(@ModelAttribute("eduser") User eduser, @PathVariable(value="id") int id) {
        userService.edit(eduser, id);
         return "redirect:/";
    }

    //add user getmap
    @GetMapping(value ="/users/add")
    public String newUser(Model model) {
         User usr = new User();
         model.addAttribute("newuser", usr);
        return "addUser";
    }

    //add user postmap
    @PostMapping(value ="/users")
    public String createUser(@ModelAttribute("newuser") User user) { // в этом объекте класса юзер будет лежать польз с данными из форма, со всеми полями заполненными/ благодаря аннотац @ModelAttribute
        userService.add(user);
        return "redirect:/";
    }

    //delete  user
    @GetMapping(value ="/users/delete/{id}")
    public String deleteUser(@PathVariable(value="id") int id, Model model) {
        User delusr = userService.getById(id);
        model.addAttribute("deluser", delusr);
        return "delete";
    }

    //delete user
    @DeleteMapping(value ="/users/delete/{id}")
    public String deletePage(@PathVariable(value="id") int id, Model model) {
        userService.delete(id);
        return "redirect:/";
    }
}
