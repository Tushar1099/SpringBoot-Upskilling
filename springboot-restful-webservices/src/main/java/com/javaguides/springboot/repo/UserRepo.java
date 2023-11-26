package com.javaguides.springboot.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaguides.springboot.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);
}
