package com.mapachebigoton.mapache.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapachebigoton.mapache.dto.SucursalDTO;
import com.mapachebigoton.mapache.mapper.SucursalMapper;
import com.mapachebigoton.mapache.model.Sucursal;
import com.mapachebigoton.mapache.repository.SucursalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class SucursalService {
    private final SucursalRepository sucursalRepository;
    private final SucursalMapper sucursalMapper;

    @Transactional(readOnly = true)
    public List<SucursalDTO> obtenerTodas(){
        List<Sucursal> sucursales = sucursalRepository.findAll();
        return sucursalMapper.toDTOList(sucursales);
    }

     @Transactional(readOnly = true)
    public List<SucursalDTO> obtenerActivas() {
        List<Sucursal> sucursales = sucursalRepository.findByActivoTrue();
        return sucursalMapper.toDTOList(sucursales);
    }

    @Transactional(readOnly = true)
    public Optional<SucursalDTO> obtenerPorId(Long id) {
        return sucursalRepository.findById(id)
                .map(sucursalMapper::toDTO);
    }  

     @Transactional(readOnly = true)
     public List<SucursalDTO> obtenerPorCiudad(String ciudad){
        List<Sucursal> sucursales = sucursalRepository.findByCiudad(ciudad);
        return sucursalMapper.toDTOList(sucursales);
     }

     @Transactional(readOnly = true)
     public List<SucursalDTO> buscarPorDireccion(String direccion){
        List<Sucursal> sucursales = sucursalRepository.buscarPorDireccionContiene(direccion);
        return sucursalMapper.toDTOList(sucursales);
     }

     public SucursalDTO crear(SucursalDTO sucursalDTO){
        if (sucursalRepository.existsByNombre(sucursalDTO.getNombre())){
            throw new IllegalArgumentException("Ya existe esa sucursal con ese nombre");
        }
        Sucursal sucursal = sucursalMapper.toEntity(sucursalDTO);
        Sucursal sucursalGuardada = sucursalRepository.save(sucursal);
        return sucursalMapper.toDTO(sucursalGuardada);
     }

     public SucursalDTO actualizar(Long id, SucursalDTO sucursalDTO){
        Sucursal sucursalExistente = sucursalRepository.findById(id)
        .orElseThrow(()-> new IllegalArgumentException("Sucursal no encontrada conn ID"+id));

        if(!sucursalExistente.getNombre().equals(sucursalDTO.getNombre())
            && sucursalRepository.existsByNombre(sucursalDTO.getNombre())){
                throw new IllegalArgumentException("Ya existe una sucursal con ese nombre");
            }
            sucursalMapper.updateEntityFromDTO(sucursalDTO, sucursalExistente);
            Sucursal sucursalActualizada = sucursalRepository.save(sucursalExistente);
            return sucursalMapper.toDTO(sucursalActualizada);
     }

     public void desactivar(Long id) {
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sucursal no encontrada con ID: " + id));
        
        sucursal.setActivo(false);
        sucursalRepository.save(sucursal);
    }

      public void activar(Long id) {
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sucursal no encontrada con ID: " + id));
        
        sucursal.setActivo(true);
        sucursalRepository.save(sucursal);
    }

     public void eliminar(Long id){
        if(!sucursalRepository.existsById(id)){
            throw new IllegalArgumentException("Sucursal no encontrada con id"+ id);
        }
        sucursalRepository.deleteById(id);
     }

     @Transactional(readOnly = true)
    public Long contarPorCiudad(String ciudad) {
        return sucursalRepository.contarSucursalesActivasPorCiudad(ciudad);
    }

}
