package com.health.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ConsultDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idDetail;

    @ManyToOne
    @JoinColumn(name = "id_consult", nullable = false, foreignKey = @ForeignKey(name = "FK_DETAIL_CONSULT"))
    private Consult consult;

    @Column(nullable = false, length = 70)
    private String diagnosis;

    @Column(nullable = false, length = 300)
    private String treatment;
}
