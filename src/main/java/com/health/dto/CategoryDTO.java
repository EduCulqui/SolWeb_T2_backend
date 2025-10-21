package com.health.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private int idCategory;

    @NotNull
    @Size(min = 3, max = 70)
    private String name;

    @Size(max = 255)
    private String description;
}
