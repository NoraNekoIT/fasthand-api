package com.bej3.seconhand.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "produk")
@SQLDelete(sql = "UPDATE produk SET status_terhapus = true WHERE id_produk = ?")
@Where(clause = "status_terhapus=false and status_terjual=false")
public class Produk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produk")
    private Integer idProduk;

    @Column(name = "status_terhapus",columnDefinition = "boolean default false")
    private boolean statusTerhapus;

    @Column(name = "nama_produk")
    private String namaProduk;

    @Column(name = "status_terjual", columnDefinition = "boolean default false")
    private boolean statusTerjual;

    @Column(name = "deskripsi_produk")
    private String deskripsiProduk;

    @Column(name = "harga_produk")
    private double hargaProduk;

    @Column(name = "potongan_diskon")
    private double potonganDiskon;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_kategori")
    private Kategori kategori;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_user_penjual")
    private Users user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_promosi", nullable = true)
    private PromosiBanner promosiBanner;

//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<GambarProduk> gambarProdukList;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<Wishlist> wishlists ;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<Transaksi> transaksiList ;

    public Produk(String namaProduk, String deskripsiProduk, double hargaProduk, double potonganDiskon) {
        this.namaProduk = namaProduk;
        this.deskripsiProduk = deskripsiProduk;
        this.hargaProduk = hargaProduk;
        this.potonganDiskon = potonganDiskon;
    }
}