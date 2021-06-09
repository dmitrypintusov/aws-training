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

import com.pintusau.banking.system.entities.CompositeKey;
import com.pintusau.banking.system.entities.Key;
import com.pintusau.banking.system.entities.User;
import com.pintusau.banking.system.entities.enums.AttributeType;
import com.pintusau.banking.system.entities.enums.Index;
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

  @GetMapping("/user/{id}/role/{role}")
  @ApiOperation(value = "Get user by id and role")
  public User getByCompositeKey(@PathVariable String id, @PathVariable String role) {
    Key primary = new Key("id", id, AttributeType.NUMBER);
    Key sort = new Key("role", role, AttributeType.STRING);
    CompositeKey compositeKey = new CompositeKey(primary, sort);
    return userService.getByCompositeKey(compositeKey);
  }

  @DeleteMapping("/user/{id}/role/{role}")
  @ApiOperation(value = "Delete user by id and role")
  public void deleteByCompositeKey(@PathVariable String id, @PathVariable String role) {
    Key primary = new Key("id", id, AttributeType.NUMBER);
    Key sort = new Key("role", role, AttributeType.STRING);
    CompositeKey compositeKey = new CompositeKey(primary, sort);
    userService.deleteByCompositeKey(compositeKey);
  }

  @GetMapping("/user/firstName/{firstName}/lastName/{lastName}")
  @ApiOperation(value = "Get user by first name and last name")
  public User getByFirstNameLastName(@PathVariable String firstName, @PathVariable String lastName) {
    Key primary = new Key("firstName", firstName, AttributeType.STRING);
    Key sort = new Key("lastName", lastName, AttributeType.STRING);
    CompositeKey compositeKey = new CompositeKey(primary, sort);
    return userService.getByIndex(Index.GLOBAL_FIRST_NAME_LAST_NAME.getIndexName(), compositeKey);
  }

  @GetMapping("/user/{id}/email/{email}")
  @ApiOperation(value = "Get user by id and email")
  public User getByIdEmail(@PathVariable String id, @PathVariable String email) {
    Key primary = new Key("id", id, AttributeType.NUMBER);
    Key sort = new Key("email", email, AttributeType.STRING);
    CompositeKey compositeKey = new CompositeKey(primary, sort);
    return userService.getByIndex(Index.LOCAL_ID_EMAIL.getIndexName(), compositeKey);
  }
}