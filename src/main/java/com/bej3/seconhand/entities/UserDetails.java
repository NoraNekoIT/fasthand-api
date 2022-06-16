package com.bej3.seconhand.entities;

import javax.persistence.*;

//@Entity(name = "user_details")
//public class UserDetails {
//    @Id
//    @GeneratedValue( strategy = GenerationType.AUTO)
//    @Column(name = "id_user_details")
//    private int idUserDetails;
//
//    @Column(name = "alamat")
//    private String alamat;
//
//    @Column(name = "kota")
//    private String kota;
//
//    @Column(name = "no_hp")
//    private String noHp;
//
//    @Lob
//    @Basic(fetch = FetchType.LAZY)
//    @Column(name = "gambar_user")
//    private byte[] gambarUser;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_user", nullable = false,unique = true)
//    private User user;
//
//}