package com.TELCEL.CRUD.Services;

import com.TELCEL.CRUD.Entity.Rol;
import com.TELCEL.CRUD.Repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    public List<Rol> getAllRoles() {
        return rolRepository.findAll();
    }

    public Optional<Rol> getRoleById(int id) {
        return rolRepository.findById(id);
    }

    public Rol getRoleByName(String name) {
        return rolRepository.findByName(name);
    }

    public Rol createRol(Rol rol) {
        List<Rol> allRoles = rolRepository.findAll();
        int nextId = allRoles.isEmpty() ? 1 : allRoles.size() + 1;
        rol.setId(nextId);
        return rolRepository.save(rol);
    }

    public void deleteRol(int id) {
        rolRepository.deleteById(id);
    }

    public Optional<Rol> updateRol(int id, Rol rolDetails) {
        Optional<Rol> rolOptional = rolRepository.findById(id);
        if (rolOptional.isPresent()) {
            Rol rolToUpdate = rolOptional.get();
            rolToUpdate.setName(rolDetails.getName());
            rolRepository.save(rolToUpdate);
        }
        return rolOptional;
    }
}


