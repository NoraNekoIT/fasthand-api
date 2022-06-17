package com.bej3.seconhand.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "user_details")
public class UserDetails {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "id_user_details")
    private int idUserDetails;

    @Column(name = "alamat")
    private String alamat;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_kota")
    private Kota kota;

    @Column(name = "no_hp")
    private String noHp;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "gambar_user")
    private byte[] gambarUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = true,unique = true)
    private User user;

}