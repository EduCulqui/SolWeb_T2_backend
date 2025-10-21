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
public class Medic {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMedic;

    @ManyToOne
    @JoinColumn(name ="id_specialty", nullable = false,
                    foreignKey = @ForeignKey( name="FK_MEDIC_SPECIALTY"))
    private Specialty specialty;

    @Column(nullable = false, length = 70)
    private String firstName;

    @Column(nullable = false, length = 70)
    private String lastName;

    @Column(nullable = false, length = 12)
    private String cmp;

    @Column(length = 250)
    private String photoUrl;
}
