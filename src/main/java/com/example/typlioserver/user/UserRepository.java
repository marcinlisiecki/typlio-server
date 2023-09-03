package com.example.typlioserver.user;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
