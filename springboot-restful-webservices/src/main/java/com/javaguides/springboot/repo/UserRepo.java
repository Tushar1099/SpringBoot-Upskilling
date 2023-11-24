package com.javaguides.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaguides.springboot.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{

}
