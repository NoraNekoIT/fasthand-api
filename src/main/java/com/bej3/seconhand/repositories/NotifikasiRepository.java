package com.bej3.seconhand.repositories;

import com.bej3.seconhand.entities.Notifikasi;
import com.bej3.seconhand.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotifikasiRepository extends JpaRepository<Notifikasi, Integer> {
    List<Notifikasi> findByUser(Users user);
}