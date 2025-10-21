package com.health.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamDTO {
    private Integer idExam;

    @NotNull
    private String nameExam;

    @NotNull
    private String descriptionExam;
}
