package com.TELCEL.CRUD.Repository;

import com.TELCEL.CRUD.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, Integer> {

    User findByFirstNameAndLastName(String firstName, String lastName);
}
