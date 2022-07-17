package com.bej3.seconhand.repositories;

import com.bej3.seconhand.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query(value = "SELECT u from users u WHERE u.email = :username and u.password = :password")
    Optional<Users> login(@Param("username")String Username,
                          @Param("password") String Password);

    Optional<Users> findByEmail(String email);
    Boolean existsByEmail(String email);
}