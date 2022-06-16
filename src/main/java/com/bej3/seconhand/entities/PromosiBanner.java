package com.bej3.seconhand.entities;

import javax.persistence.*;

@Entity(name = "promosi_banner")
public class PromosiBanner {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "id_promosi")
    private int idPromosi;

    @Column(name = "gambar_banner")
    private int gambarBanner;

    @Column(name = "label_promosi")
    private int labelPromosi;
}
