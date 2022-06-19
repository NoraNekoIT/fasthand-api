package com.bej3.seconhand.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "kota")
public class Kota implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kota")
    private int idKota;

    @Column(name = "nama_kota",unique = true)
    private String namaKota;

    //setiap one to many dia bikin table
//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<UserDetails> userDetails;


    public Kota(String namaKota) {
        this.namaKota = namaKota;
    }

    public Kota() {

    }
}
