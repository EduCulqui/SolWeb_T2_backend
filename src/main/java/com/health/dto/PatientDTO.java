package com.health.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private int idPatient;

    @NotNull
    //@NotBlank
    //@NotEmpty
    @Size(min = 3, max = 70)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 70)
    private String lastName;

    @NotNull
    private String dni;

    private String address;

    @NotNull
    @Pattern(regexp = "[0-9]+")
    //@Pattern(regexp = "^9\\d{8}$"); // Para que siempre empiece con 9
    private String phone;

    @Email
    private String email;
}
