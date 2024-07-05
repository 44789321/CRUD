package com.TELCEL.CRUD.Services;

import com.TELCEL.CRUD.Entity.User;
import com.TELCEL.CRUD.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public User getUserByFullName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public User createUser(User user) {
        user.setId((int) userRepository.count() + 1);
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public Optional<User> updateUser(int id, User userDetails) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User userToUpdate = userOptional.get();
            userToUpdate.setFirstName(userDetails.getFirstName());
            userToUpdate.setLastName(userDetails.getLastName());
            userToUpdate.setMiddleName(userDetails.getMiddleName());
            userToUpdate.setRoles(userDetails.getRoles());
            userRepository.save(userToUpdate);
        }
        return userOptional;
    }
}
