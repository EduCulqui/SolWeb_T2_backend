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
public class PrescriptionProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idPrescriptionProduct;

    @ManyToOne
    @JoinColumn(name ="id_product", nullable = false,
            foreignKey = @ForeignKey( name="FK_PRESCRIPTION_PRODUCT_PRODUCT"))
    private Product product;

    @ManyToOne
    @JoinColumn(name ="id_prescription", nullable = false,
            foreignKey = @ForeignKey( name="FK_PRESCRIPTION_PRODUCT_PRESCRIPTION"))
    private Prescription prescription;

    private Integer quantityPrescription;
}
