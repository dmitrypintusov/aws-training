package com.pintusau.banking.system.controllers;

import com.pintusau.banking.system.entities.User;
import com.pintusau.banking.system.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

  private UserService userService;

  @GetMapping
  @Operation(summary = "Get all users")
  public List<User> getAll() {
    return userService.getAll();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get user by id")
  public User getById(@PathVariable Long id) {
    return userService.getById(id);
  }

  @PostMapping
  @Operation(summary = "Create new user")
  public User create(@RequestBody User user) {
    return userService.createOrUpdate(user);
  }

  @PutMapping
  @Operation(summary = "Update the user")
  public User update(@RequestBody User user) {
    return userService.createOrUpdate(user);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete the user")
  public void deleteById(@PathVariable Long id) {
    userService.deleteById(id);
  }
}
