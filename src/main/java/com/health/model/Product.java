package com.health.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idProduct;

    private String name;
    private String description;
    private String presentation;
    private Double unitPrice;
    private Integer stock;
    private LocalDate expired;

    @ManyToOne
    @JoinColumn(name ="id_category", nullable = false,
            foreignKey = @ForeignKey( name="FK_PRODUCT_CATEGORY"))
    private Category category;

    @ManyToOne
    @JoinColumn(name ="id_family", nullable = false,
            foreignKey = @ForeignKey( name="FK_PRODUCT_FAMILY"))
    private Family family;

    @ManyToOne
    @JoinColumn(name ="id_laboratory", nullable = false,
            foreignKey = @ForeignKey( name="FK_PRODUCT_LABORATORY"))
    private Laboratory laboratory;

}
