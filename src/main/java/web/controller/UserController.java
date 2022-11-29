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

//    List<User> list1= new ArrayList<>();

//     {
//        User us1 = new User("vania", "petrov");
//        User us2 = new User("petya", "ivanov");
//        User us3 = new User("masha", "fedotova");
//        User us4 = new User("kira", "bushueva");
//        list1.add(us1);
//        list1.add(us2);
//        list1.add(us3);
//        list1.add(us4);
//    }

    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //shows all users
    @GetMapping(value ="/users")
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
         return "redirect:/users";
    }

    //add user getmap
    @GetMapping(value ="/users/add")  // этот юрл отраб-ся корректно
    public String newUser(Model model) {
        System.out.println("@@@@@@  контроллер add user getmap отработывается...");
        User usr = new User();
        System.out.println("@@@@@@  пустой польз был создан...");
        // до этого отраб-ся корректно
        model.addAttribute("newuser", usr);
        return "addUser";
    }

    //add user postmap
    @PostMapping(value ="/users")
    public String createUser(@ModelAttribute("newuser") User user) { // в этом объекте класса юзер будет лежать польз с данными из форма, со всеми полями заполненными/ благодаря аннотац @ModelAttribute
        userService.add(user);

        return "redirect:/users";
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
        return "redirect:/users";
    }
}
