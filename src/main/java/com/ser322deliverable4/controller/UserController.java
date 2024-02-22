package com.ser322deliverable4.controller;

import com.ser322deliverable4.model.User;
import com.ser322deliverable4.service.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


@Controller
public class UserController {

    private final IUserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-services")
    public String addUserPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        List<User> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "user-services";
    }

    @PostMapping("/add-user")
    public String addUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        logger.info("RECEIVED USER: {}", user.getEmail());
        userService.addUser(user);
        logger.info("SAVED USER: {}", user.getEmail());
        return "redirect:user-services";
    }

    @GetMapping("/edit-user/{userId}")
    public String editUserPage(@PathVariable Long userId, Model model) {
        User user = userService.getUserById(userId);
        model.addAttribute("editUser", user);
        return "edit-user";
    }

    @PostMapping("/edit-user")
    public String editUser(@ModelAttribute("editUser") User editUser, BindingResult bindingResult) {
        logger.info("EDIT USER ID: {}", editUser.getId());
        logger.info("EDIT USER EMAIL: {}", editUser.getEmail());
        logger.info("EDIT USER FIRSTNAME: {}", editUser.getFirstName());
        logger.info("EDIT USER LASTNAME: {}", editUser.getLastName());
        int response = userService.editUser(editUser);
        logger.info("ROWS CHANGED IN DB: {}", response);
        return "redirect:user-services";
    }

    @GetMapping("/delete-user/{userId}")
    public String deleteUser(@PathVariable Long userId, RedirectAttributes redirectAttributes) {
        logger.info("DELETING USER BY ID: {}", userId);
        try {
            int response = userService.deleteUser(userId);
            logger.info("ROWS CHANGED IN DB: {}", response);
            return "redirect:../user-services";
        } catch (SQLIntegrityConstraintViolationException ex) {
            logger.error("SQL INTEGRITY CONSTRAINT VIOLATION EXCEPTION");
            redirectAttributes.addFlashAttribute("error", "Cannot delete manufacturer due to foreign key constraint.");
            return "redirect:../user-services";
        }
    }

}
