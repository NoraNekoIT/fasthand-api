package com.bej3.seconhand.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "wishlist")
public class Wishlist {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "id_wishlist")
    private int idWishlist;

    @Column(name = "status")
    private boolean status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_produk")
    private Produk produk;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_user_pembeli")
    private User user;
}