package com.anffercastillo.heroes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anffercastillo.heroes.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findUserByUsername(String username);
}
