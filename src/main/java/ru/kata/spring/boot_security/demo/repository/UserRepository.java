package com.spring.spring_boot.dao;


import com.spring.spring_boot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {
}
