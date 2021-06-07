package com.pintusau.banking.system.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pintusau.banking.system.entities.User;
import com.pintusau.banking.system.services.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    @ApiOperation(value = "Get all users")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    @ApiOperation(value = "Get user by id")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping("/user")
    @ApiOperation(value = "Create new user")
    public User create(@RequestBody User user) {
        return userService.createOrUpdate(user);
    }

    @PutMapping("/user")
    @ApiOperation(value = "Update user")
    public User update(@RequestBody User user) {
        return userService.createOrUpdate(user);
    }

    @DeleteMapping("/user/{id}")
    @ApiOperation(value = "Delete user")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}