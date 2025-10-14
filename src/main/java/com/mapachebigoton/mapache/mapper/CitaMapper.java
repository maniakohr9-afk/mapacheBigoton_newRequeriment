package com.mapachebigoton.mapache.mapper;

import com.mapachebigoton.mapache.dto.CitaResponse;
import com.mapachebigoton.mapache.model.Cita;
import com.mapachebigoton.mapache.dto.CitaRequest;

public class CitaMapper {
    public static CitaResponse toResponse(Cita cita) {
        if (cita == null)
            return null;
        return CitaResponse.builder()
                .idCita(cita.getIdCita())
                .fecha(cita.getFecha() != null ? cita.getFecha().toString() : null)
                .hora(cita.getHora())
                .idBarbero(cita.getBarbero() != null ? cita.getBarbero().getIdBarbero() : null)
                .idCliente(cita.getCliente() != null ? cita.getCliente().getIdCliente() : null)
                .idServicio(cita.getServicio() != null ? cita.getServicio().getIdServicio() : null)
                .build();
    }
    
    public static Cita toEntity(CitaRequest dto){
        if (dto == null)
            return null;
        return Cita.builder()
                .fecha(dto.getFecha())
                .hora(dto.getHora())
                .build();
    }

    public static void copyToEntity(CitaRequest dto, Cita entity){
        if (dto == null || entity == null)
            return;
        entity.setFecha(dto.getFecha());
        entity.setHora(dto.getHora());
    }
}
