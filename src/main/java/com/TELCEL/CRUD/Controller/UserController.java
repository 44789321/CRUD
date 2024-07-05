package com.TELCEL.CRUD.Controller;

import com.TELCEL.CRUD.Entity.User;
import com.TELCEL.CRUD.Entity.Rol;
import com.TELCEL.CRUD.Services.UserService;
import com.TELCEL.CRUD.Services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RolService rolService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{firstName}/{lastName}")
    public ResponseEntity<User> getUserByFullName(@PathVariable String firstName, @PathVariable String lastName) {
        User user = userService.getUserByFullName(firstName, lastName);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        for (Rol rol : user.getRoles()) {
            Optional<Rol> existingRol = rolService.getRoleById(rol.getId());
            if (existingRol.isEmpty()) {
                return ResponseEntity.badRequest().body("El ID " + rol.getId() + " con el nombre '" + rol.getName() + "' no existe en la lista de roles. Por favor, pruebe con un ID válido o agregue el rol en la lista.");
            }
        }

        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User userDetails) {
        Optional<User> existingUser = userService.getUserById(id);
        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User userToUpdate = existingUser.get();
        userToUpdate.setFirstName(userDetails.getFirstName());
        userToUpdate.setMiddleName(userDetails.getMiddleName());
        userToUpdate.setLastName(userDetails.getLastName());

        for (Rol rol : userDetails.getRoles()) {
            Optional<Rol> existingRol = rolService.getRoleById(rol.getId());
            if (existingRol.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            if (!existingRol.get().getName().equals(rol.getName())) {
                return ResponseEntity.badRequest().build();
            }
        }
        userToUpdate.setRoles(userDetails.getRoles());

        Optional<User> updatedUser = userService.updateUser(id, userToUpdate);
        return updatedUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

