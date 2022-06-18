package com.bej3.seconhand.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private int idUser;

    @Column(name = "name")
    private String name;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private boolean role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private UserDetails userDetail;

//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<Wishlist> wishlists;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<Transaksi> transaksiList;

    public User(String name, String email, String password, boolean role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {

    }
}