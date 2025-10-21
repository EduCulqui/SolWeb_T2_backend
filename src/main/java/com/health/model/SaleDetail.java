package com.health.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idDetail;

    @ManyToOne
    @JoinColumn(name ="id_sale", nullable = false,
            foreignKey = @ForeignKey( name="FK_DETAIL_SALE"))
    private Sale sale;

    @ManyToOne
    @JoinColumn(name ="id_product", nullable = false,
            foreignKey = @ForeignKey( name="FK_DETAIL_PRODUCT"))
    private Product product;

    private Integer quantity;
    private Double unitPrice;
    private Double subtotal;
}
