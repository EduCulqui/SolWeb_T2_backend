package com.health.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LaboratoryDTO {
    private Integer idLaboratory;

    @NotNull
    @Size(min = 3, max = 70)
    private String name;

    @Size(max = 150)
    private String address;

    @Pattern(regexp = "[0-9]+", message = "Solo se permiten números")
    private String phone;

    @Email
    private String email;
}
