package com.mapachebigoton.mapache.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ServicioResponse {
    Integer idServicio;
    String descripcion;
    Long costo;
}
