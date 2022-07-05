package com.bej3.seconhand.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "notifikasi")
public class Notifikasi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notifikasi")
    private Integer idNotifikasi;

    @Column(name = "pesan_notifikasi")
    private String pesanNotifkasi;

    @Column(name = "created")
    private Date created = new Date();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_user")
    private Users user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_transaksi")
    private Transaksi transaksi;

    public Notifikasi(String pesanNotifkasi, Users user, Transaksi transaksi) {
        this.pesanNotifkasi = pesanNotifkasi;
        this.user = user;
        this.transaksi = transaksi;
    }
}