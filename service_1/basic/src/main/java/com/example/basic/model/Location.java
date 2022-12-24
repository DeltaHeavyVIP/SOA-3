package com.example.basic.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "x", nullable = false, updatable = false)
    private int x;

    @Column(name = "y", nullable = false, updatable = false)
    private Double y; //Поле не может быть null

    @Column(name = "z", nullable = false, updatable = false)
    private double z;

}
