package com.mapachebigoton.mapache.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.mapachebigoton.mapache.dto.SucursalDTO;
import com.mapachebigoton.mapache.model.Sucursal;

public class SucursalMapper {
    public SucursalDTO toDTO(Sucursal sucursal){
        if(sucursal == null){
            return null;
        }

        SucursalDTO dto = new SucursalDTO();
        dto.setId(sucursal.getId());
        dto.setNombre(sucursal.getNombre());
        dto.setDireccion(sucursal.getDireccion());
        dto.setCiudad(sucursal.getCiudad());
        dto.setEstado(sucursal.getEstado());
        dto.setCodigoPostal(sucursal.getCodigoPostal());
        dto.setTelefono(sucursal.getTelefono());
        dto.setEmail(sucursal.getEmail());
        dto.setActivo(sucursal.getActivo());
        dto.setFechaCreacion(sucursal.getFechaCreacion());
        dto.setFechaActualizacion(sucursal.getFechaActualizacion());
        
        return dto;

    }

    public Sucursal toEntity(SucursalDTO dto){
        if(dto == null){
            return null;
        }

        Sucursal sucursal = new Sucursal();
        sucursal.setId(dto.getId());
        sucursal.setNombre(dto.getNombre());
        sucursal.setDireccion(dto.getDireccion());
        sucursal.setCiudad(dto.getCiudad());
        sucursal.setEstado(dto.getEstado());
        sucursal.setCodigoPostal(dto.getCodigoPostal());
        sucursal.setTelefono(dto.getTelefono());
        sucursal.setEmail(dto.getEmail());
        sucursal.setActivo(dto.getActivo());
        sucursal.setFechaCreacion(dto.getFechaCreacion());
        sucursal.setFechaActualizacion(dto.getFechaActualizacion());
        
        return sucursal;
    }

    public List<SucursalDTO> toDTOList(List<Sucursal> sucursales) {
        if (sucursales == null) {
            return null;
        }
        
        return sucursales.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

     public List<Sucursal> toEntityList(List<SucursalDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

     public void updateEntityFromDTO(SucursalDTO dto, Sucursal sucursal) {
        if (dto == null || sucursal == null) {
            return;
        }
        
        sucursal.setNombre(dto.getNombre());
        sucursal.setDireccion(dto.getDireccion());
        sucursal.setCiudad(dto.getCiudad());
        sucursal.setEstado(dto.getEstado());
        sucursal.setCodigoPostal(dto.getCodigoPostal());
        sucursal.setTelefono(dto.getTelefono());
        sucursal.setEmail(dto.getEmail());
        sucursal.setActivo(dto.getActivo());
    }
}
