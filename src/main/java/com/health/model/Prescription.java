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
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idPrescription;

    private String detail;

    @ManyToOne
    @JoinColumn(name ="id_consult", nullable = false,
            foreignKey = @ForeignKey( name="FK_PRESCRIPTION_CONSULT"))
    private Consult consult;
}
