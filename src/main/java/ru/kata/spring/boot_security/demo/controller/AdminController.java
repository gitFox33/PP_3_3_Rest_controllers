package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.utill.UserErrorResponse;
import ru.kata.spring.boot_security.demo.utill.UserNotCreatedException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    //Список всех пользователей
    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid User user,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new UserNotCreatedException(errorMsg.toString());
        }
        userService.createUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
//    public String deleteUser(@RequestParam(value = "id") long id) {
//        userService.deleteUser(id);
//        return ADMIN_PAGE;
//    }


//
//
//    @GetMapping("/admin")
//    public String adminPage(Model model) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDetails ud = (UserDetails) authentication.getPrincipal();
//
//        List<Role> allRoles = new ArrayList<>();
//        allRoles.add(new Role("ROLE_ADMIN"));
//        allRoles.add(new Role("ROLE_USER"));
//
//
//
//        model.addAttribute("userModel", new User());
//        model.addAttribute("allRoles", allRoles);
//        model.addAttribute("currentUser", userService.getUserByUsername(ud.getUsername()));
//        model.addAttribute("users", userService.getAllUsers());
//        return "admin";
//    }
//
//
//    @PostMapping("/create")
//    public String create(@ModelAttribute("user") @Valid User user,
//                         BindingResult bindingResult,
//                         @RequestParam("selectedRoles") List<String> selectedRoles) {
//        if (bindingResult.hasErrors()) {
//            System.out.println("Incorrect create input");
//            return ADMIN_PAGE;
//        }
//
//        for (String roleName : selectedRoles) {
//            user.addRole(userService.getRoleByName(roleName));
//        }
//        userService.createUser(user);
//        return ADMIN_PAGE;
//    }
//
//    @GetMapping("/create")
//    public String newPage(Model model) {
//        model.addAttribute("user", new User());
//        return ADMIN_PAGE;
//    }
//
//
//    @PostMapping("/edit")
//    public String edit(@RequestParam("id") long id,
//                       @RequestParam("edit_name") @Valid String name,
//                       @RequestParam("edit_email") String lastName,
//                       @RequestParam("edit_age") Integer age,
//                       @RequestParam("edit_username") String username,
//                       @RequestParam("edit_password") String password,
//                       @RequestParam("selectedRoles") List<String> selectedRoles) {
//        User user = new User(name, lastName, age, username, password);
//
//        for (String roleName : selectedRoles) {
//            user.addRole(userService.getRoleByName(roleName));
//        }
//        userService.editUser(id, user);
//        return ADMIN_PAGE;
//    }
//
//
//    @PostMapping("/delete")
//    public String deleteUser(@RequestParam(value = "id") long id) {
//        userService.deleteUser(id);
//        return ADMIN_PAGE;
//    }
    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handlerException(UserNotCreatedException e) {
        UserErrorResponse response = new UserErrorResponse(
                "Юзер не создан...",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }
}
