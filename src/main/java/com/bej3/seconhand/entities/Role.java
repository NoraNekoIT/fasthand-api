package com.bej3.seconhand.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity(name = "role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private int idRole;

    @Column(name = "name_role",nullable = false,unique = true)
    private String nameRole;

    @Override
    public String getAuthority() {
        return "ROLE_"+nameRole;
    }
}