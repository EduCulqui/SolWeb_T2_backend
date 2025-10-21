package com.health.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(ConsultExamPK.class)
public class ConsultExam {
    @Id
    private Consult consult;

    @Id
    private Exam exam;

    /*static class ConsultExamPK implements Serializable{
        @ManyToOne
        private Consult consult;

        @ManyToOne
        private Exam exam;
    }
     */
}
