package com.health.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialtyDTO {

    private Integer idSpecialty;

    @NotNull
    private String nameSpecialty;

    @NotNull
    private String descriptionSpecialty;
}
