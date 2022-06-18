package com.bej3.seconhand.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "user_details")
public class UserDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "id_user_details")
    private int idUserDetails;

    @Column(name = "alamat")
    private String alamat;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_kota")
    private Kota kota;

    @Column(name = "no_hp")
    private String noHp;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "gambar_user")
    private byte[] gambarUser;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = true,unique = true)
    private User user;

    public UserDetails(String alamat, String noHp, byte[] gambarUser) {
        this.alamat = alamat;
        this.noHp = noHp;
        this.gambarUser = gambarUser;
    }
}