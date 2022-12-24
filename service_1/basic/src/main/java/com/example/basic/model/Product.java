package com.example.basic.model;

import com.example.objects.common.UnitOfMeasureDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "product")
public class Product {

    public Product(String name, Coordinates coordinates, Long price, String partNumber, Integer manufactureCost, UnitOfMeasure unitOfMeasure, Person person) {
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.partNumber = partNumber;
        this.manufactureCost = manufactureCost;
        this.unitOfMeasure = unitOfMeasure;
        this.owner = person;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @NotBlank
    @Column(name = "name")
    private String name; //Поле не может быть null, Строка не может быть пустой

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Coordinates coordinates; //Поле не может быть null

    @CreationTimestamp
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @NotNull
    @Min(1)
    @Column(name = "price")
    private Long price; //Поле не может быть null, Значение поля должно быть больше 0

    @NotNull
    @Column(name = "partNumber")
    private String partNumber; //Поле может быть null

    @NotNull
    @Column(name = "manufactureCost")
    private Integer manufactureCost; //Поле не может быть null

    @NotNull
    @Column(name = "unitOfMeasure")
    @Enumerated(EnumType.STRING)
    private UnitOfMeasure unitOfMeasure; //Поле может быть null

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Person owner; //Поле не может быть null

    public UnitOfMeasureDto getUnitOfMeasureDtoFromProduct() {
        return UnitOfMeasureDto.valueOf(this.getUnitOfMeasure().toString());
    }
}
