package com.health.model;

import com.mysql.cj.xdevapi.DatabaseObject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idSale;

    @ManyToOne
    @JoinColumn(name ="id_patient", nullable = false,
            foreignKey = @ForeignKey( name="FK_SALE_PATIENT"))
    private Patient patient;

    private LocalDateTime saleDate;
    private Double total;
}
