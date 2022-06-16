package com.bej3.seconhand.entities;

import javax.persistence.*;

@Entity(name = "wishist")
public class Wishlist {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column(name = "status")
    private int status;
}
