package com.mapachebigoton.mapache.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CitaRequest {
    @NotNull
    Date fecha;
    @NotNull
    String hora;
    @NotNull
    Integer idCliente;
    @NotNull
    Integer idBarbero;
    @NotNull
    Integer idServicio;
}
