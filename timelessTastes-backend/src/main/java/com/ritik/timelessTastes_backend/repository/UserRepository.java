package com.ritik.timelessTastes_backend.repository;

import com.ritik.timelessTastes_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
}
