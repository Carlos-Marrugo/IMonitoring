package com.backend.IMonitoring.repository;

import com.backend.IMonitoring.model.User;
import com.backend.IMonitoring.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(Rol role);
}