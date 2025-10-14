package com.mapachebigoton.mapache.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BarberoResponse {
    Integer idBarbero;
    String nombre;
}