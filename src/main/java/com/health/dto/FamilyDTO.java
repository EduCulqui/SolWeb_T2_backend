package com.health.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FamilyDTO {
    private Integer idFamily;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @Size(max = 150)
    private String description;
}
