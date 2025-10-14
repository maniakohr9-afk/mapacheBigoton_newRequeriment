package com.mapachebigoton.mapache.mapper;

import com.mapachebigoton.mapache.dto.ClienteRequest;
import com.mapachebigoton.mapache.dto.ClienteResponse;
import com.mapachebigoton.mapache.model.Cliente;

public class ClienteMapper {
    public static ClienteResponse toResponse(Cliente cliente) {
        if (cliente == null)
            return null;
        return ClienteResponse.builder()
                .idCliente(cliente.getIdCliente())
                .nombre(cliente.getNombre())
                .telefono(cliente.getTelefono())
                .build();
    }

    public static Cliente toEntity(ClienteRequest dto){
        if (dto == null)
            return null;
        return Cliente.builder()
                .nombre(dto.getNombre())
                .telefono(dto.getTelefono())
                .build();
    }

    public static void copyToEntity(ClienteRequest dto, Cliente entity){
        if (dto == null || entity == null)
            return;
        entity.setNombre(dto.getNombre());
        entity.setTelefono(dto.getTelefono());
    }
}
