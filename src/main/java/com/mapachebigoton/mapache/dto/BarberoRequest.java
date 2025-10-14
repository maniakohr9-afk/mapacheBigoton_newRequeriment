package com.mapachebigoton.mapache.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class BarberoRequest {
    @NotBlank
    @Size(max = 50)
    String nombre;
}
