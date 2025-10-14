package com.mapachebigoton.mapache.mapper;

import com.mapachebigoton.mapache.dto.ServicioRequest;
import com.mapachebigoton.mapache.dto.ServicioResponse;
import com.mapachebigoton.mapache.model.Servicio;

public class ServicioMapper {
    public static ServicioResponse toResponse(Servicio servicio) {
        if (servicio == null)
            return null;
        return ServicioResponse.builder()
                .idServicio(servicio.getIdServicio())
                .descripcion(servicio.getDescripcion())
                .costo(servicio.getCosto())
                .build();
    }

    public static Servicio toEntity(ServicioRequest dto){
        if (dto == null)
            return null;
        return Servicio.builder()
                .descripcion(dto.getDescripcion())
                .costo(dto.getCosto())
                .build();
    }

    public static void copyToEntity(ServicioRequest dto, Servicio entity){
        if (dto == null || entity == null)
            return;
        entity.setDescripcion(dto.getDescripcion());
        entity.setCosto(dto.getCosto());
    }
}
