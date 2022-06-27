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
    private Integer idPromosi;

    @Lob
    @Column(name = "gambar_banner")
    private byte[] gambarBanner;

    @Column(name = "type_gambar")
    private String typeGambarBanner;

    @Column(name = "label_promosi")
    private String labelPromosi;

    public PromosiBanner(byte[] gambarBanner, String typeGambarBanner, String labelPromosi) {
        this.gambarBanner = gambarBanner;
        this.typeGambarBanner = typeGambarBanner;
        this.labelPromosi = labelPromosi;
    }

    public PromosiBanner() {

    }

//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<Produk> produkList;
}