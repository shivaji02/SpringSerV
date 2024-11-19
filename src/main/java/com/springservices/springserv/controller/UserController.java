package com.springservices.springserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.springservices.springserv.entity.User;
import com.springservices.springserv.service.UserService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    // GET all users
    @GetMapping ("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // GET a single user by ID
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // POST to create a new user
    @PostMapping (value = "/adduser" ,  consumes = "multipart/form-data")
    public User createUser(@RequestBody User user) {
      return userService.createUser(
              user.getName(),
              user.getEmail(),
              user.getLatitude(),
              user.getLongitude(),
              user.getPassword(),
              user.getLocationName()
      );

    }

    // PUT to update an existing user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails);
    }

    // DELETE a user by ID
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User with ID " + id + " deleted successfully";
    }
}
