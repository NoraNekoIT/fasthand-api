package com.bej3.seconhand.entities;

import javax.persistence.*;

@Entity(name = "promosi_banner")
public class PromosiBanner {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id_promosi")
    private int idPromosi;

    @Column(name = "gambar_banner")
    private byte[] gambarBanner;

    @Column(name = "label_promosi")
    private String labelPromosi;



//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<Produk> produkList;
}