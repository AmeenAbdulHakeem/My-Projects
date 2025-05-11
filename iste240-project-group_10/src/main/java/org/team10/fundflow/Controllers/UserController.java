/*
 *       Project: FundFlow
 *       Team: 10
 *       @Authors: Abdulrahman Al Qaiwani, Ameen Ahmed, Ahmad Ahmadi, Yousuf Majwal
 *
 */

package org.team10.fundflow.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.team10.fundflow.Configs.User;
import org.team10.fundflow.Services.UserService;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }

    @GetMapping("/findByName")
    public User findUserByName(@RequestParam String name) {
        return userService.findUserByName(name);
    }

    @PutMapping("/transfer")
    public String transferFunds(@RequestParam String senderUsername, @RequestParam String receiverUsername, @RequestParam Double amount) {
        userService.transferFunds(senderUsername, receiverUsername, amount);
        return "Transfer successful!";
    }

    @GetMapping("/validate")
    public String validateUser(@RequestParam String username, @RequestParam String password) {
        User user = userService.findUserByUsername(username);
        if (user.getPassword().equals(password)) {
            return user.getName();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @GetMapping("/findByUsername")
    public User findUserByUsername(@RequestParam String username) {
        return userService.findUserByUsername(username);
    }
}