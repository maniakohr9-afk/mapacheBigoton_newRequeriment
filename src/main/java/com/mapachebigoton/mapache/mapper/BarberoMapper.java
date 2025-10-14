package com.mapachebigoton.mapache.mapper;

import com.mapachebigoton.mapache.dto.BarberoRequest;
import com.mapachebigoton.mapache.dto.BarberoResponse;
import com.mapachebigoton.mapache.model.Barbero;

public class BarberoMapper {
    public static BarberoResponse toResponse(Barbero barbero) {
        if (barbero == null)
            return null;
        return BarberoResponse.builder()
                .idBarbero(barbero.getIdBarbero())
                .nombre(barbero.getNombre())
                .build();
    }

    public static Barbero toEntity(BarberoRequest dto){
        if (dto == null)
            return null;
        return Barbero.builder()
                .nombre(dto.getNombre())
                .build();
    }

    public static void copyToEntity(BarberoRequest dto, Barbero entity){
        if (dto == null || entity == null)
            return;
        entity.setNombre(dto.getNombre());
    }
}
