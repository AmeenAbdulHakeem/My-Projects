/*
 *       Project: FundFlow
 *       Team: 10
 *       @Authors: Abdulrahman Al Qaiwani, Ameen Ahmed, Ahmad Ahmadi, Yousuf Majwal
 *
 */

package org.team10.fundflow.Services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.team10.fundflow.Configs.User;
import org.team10.fundflow.Repositories.UserRepository;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void transferFunds(String senderUsername, String receiverUsername, Double amount) {
        User sender = userRepository.findByUsername(senderUsername);
        if (sender == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sender not found");
        }

        User receiver = userRepository.findByUsername(receiverUsername);
        if (receiver == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receiver not found");
        }

        if (sender.getBalance() < amount) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient balance");
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        userRepository.save(sender);
        userRepository.save(receiver);
    }

    public User saveUser(User user) {
        if (user.getBalance() == null) {
            user.setBalance(0.0);
        }
        return userRepository.save(user);
    }

    public User updateUser(int id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));

        if (updatedUser.getName() != null) {
            existingUser.setName(updatedUser.getName());
        }

        if (updatedUser.getUsername() != null) {
            existingUser.setUsername(updatedUser.getUsername());
        }

        if (updatedUser.getBalance() != null) {
            existingUser.setBalance(updatedUser.getBalance());
        }

        return userRepository.save(existingUser);
    }

    public User getUser(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found to delete");
        }
        userRepository.deleteById(id);
    }

    public User findUserByName(String name) {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with name: " + name);
        }
        return user;
    }

    public User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with username: " + username);
        }
        return user;
    }

    public boolean validateUser(String username, String password) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}