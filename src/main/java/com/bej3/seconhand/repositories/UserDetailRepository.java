package com.bej3.seconhand.repositories;

import com.bej3.seconhand.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetails, Integer> {
}