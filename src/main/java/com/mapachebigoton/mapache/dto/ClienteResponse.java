package com.mapachebigoton.mapache.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ClienteResponse {
    Integer idCliente;
    String nombre;
    String telefono;
}
