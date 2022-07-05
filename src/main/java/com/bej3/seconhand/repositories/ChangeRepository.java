package com.bej3.seconhand.repositories;

import com.bej3.seconhand.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangeRepository extends JpaRepository<Users, Integer> {
}
