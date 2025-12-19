package org.example.carsharing.repository;

import org.example.carsharing.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
//    boolean exsistByEmail(String email);
}
