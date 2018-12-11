package com.adrrriannn.store.user.repository;

import com.adrrriannn.store.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
