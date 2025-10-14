package com.mapachebigoton.mapache.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CitaResponse {
    Integer idCita;
    String fecha;
    String hora;
    Integer idCliente;
    Integer idBarbero;
    Integer idServicio;
}
