package com.bej3.seconhand.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "users")
public class Users implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int idUser;

    @Column(name = "name")
    private String name;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "password")
    private String password;

//    @Column(name = "role")
//    private boolean role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "id_user",
            referencedColumnName = "id_user"
            ),
            inverseJoinColumns = @JoinColumn(name = "id_role",
            referencedColumnName = "id_role"
            ))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "users")
    private UserDetails userDetail;

//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<Wishlist> wishlists;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<Transaksi> transaksiList;

    public Users(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
//        this.role = role;
    }

    public Users() {

    }
}