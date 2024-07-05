package com.TELCEL.CRUD.Repository;

import com.TELCEL.CRUD.Entity.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RolRepository extends MongoRepository<Rol, Integer> {
    Rol findByName(String name);
}


