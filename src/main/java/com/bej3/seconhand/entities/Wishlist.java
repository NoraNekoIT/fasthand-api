//package com.bej3.seconhand.entities;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.*;
//
//@Entity(name = "wishlist")
//public class Wishlist {
//    @Id
//    @GeneratedValue( strategy = GenerationType.IDENTITY)
//    @Column(name = "id_wishlist")
//    private Integer idWishlist;
//
//    @Column(name = "status")
//    private boolean status;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "id_produk")
//    private Produk produk;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "id_user_pembeli")
//    private Users user;
//}