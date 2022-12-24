package com.example.basic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coordinates")
public class Coordinates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Max(791)
    @Column(name = "x", nullable = false, updatable = false)
    private float x; //Максимальное значение поля: 791

    @Column(name = "y", nullable = false, updatable = false)
    private Float y; //Поле не может быть null

}
