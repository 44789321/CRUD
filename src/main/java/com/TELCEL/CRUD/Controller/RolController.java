package com.TELCEL.CRUD.Controller;

import com.TELCEL.CRUD.Entity.Rol;
import com.TELCEL.CRUD.Services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public ResponseEntity<List<Rol>> getAllRoles() {
        List<Rol> roles = rolService.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> getRolById(@PathVariable int id) {
        Optional<Rol> rol = rolService.getRoleById(id);
        if (rol.isPresent()) {
            return new ResponseEntity<>(rol.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Rol> getRolByName(@PathVariable String name) {
        Rol rol = rolService.getRoleByName(name);
        if (rol != null) {
            return new ResponseEntity<>(rol, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Rol> createRol(@RequestBody Rol rol) {
        Rol createdRol = rolService.createRol(rol);
        return new ResponseEntity<>(createdRol, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> updateRol(@PathVariable int id, @RequestBody Rol rolDetails) {
        Optional<Rol> updatedRol = rolService.updateRol(id, rolDetails);
        return updatedRol.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable int id) {
        rolService.deleteRol(id);
        return ResponseEntity.noContent().build();
    }
}
