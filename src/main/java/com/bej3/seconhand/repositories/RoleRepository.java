package com.bej3.seconhand.repositories;

import com.bej3.seconhand.entities.ERole;
import com.bej3.seconhand.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByNamaRole(ERole namaRole);
}
