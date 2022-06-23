package com.bej3.seconhand.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
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

    public PromosiBanner(String originalFilename, byte[] bytes) {
        this.labelPromosi = originalFilename;
        this.gambarBanner = bytes;
    }

    public PromosiBanner() {

    }


//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<Produk> produkList;
}