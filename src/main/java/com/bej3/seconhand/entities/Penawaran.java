package com.bej3.seconhand.entities;

import javax.persistence.*;

@Entity(name = "penawaran")
public class Penawaran {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "id_penawaran")
    private int idPenawaran;

    @Column(name = "harga_tawaran")
    private int harga_tawaran;

    @Column(name = "status_tawaran")
    private int status_tawaran;
}
