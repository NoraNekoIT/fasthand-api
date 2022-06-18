package com.bej3.seconhand.entities;

import javax.persistence.*;

@Entity(name = "kota")
public class Kota {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_kota")
    private int idKota;

    @Column(name = "nama_kota")
    private String namaKota;

    //setiap one to many dia bikin table
//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<UserDetails> userDetails;
}
