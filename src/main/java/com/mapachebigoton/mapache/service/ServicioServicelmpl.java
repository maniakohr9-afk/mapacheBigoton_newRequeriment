package com.mapachebigoton.mapache.service;

import com.mapachebigoton.mapache.model.Servicio;
import com.mapachebigoton.mapache.repository.ServicioRepository;
import com.mapachebigoton.mapache.mapper.ServicioMapper;
import com.mapachebigoton.mapache.dto.ServicioRequest;
import com.mapachebigoton.mapache.dto.ServicioResponse;

import java.util.List;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicioServicelmpl implements ServicioService{
    private final ServicioRepository repository;

    @Override
    public List<ServicioResponse> findAll() {
        return repository.findAll().stream()
                .map(ServicioMapper::toResponse)
                .toList();
    }

    @Override
    public ServicioResponse findById(Integer idServicio) {
        Servicio servicio = repository.findById(idServicio)
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado: " + idServicio));
        return ServicioMapper.toResponse(servicio);
    }

    @Override
    public ServicioResponse create(ServicioRequest request) {
        Servicio saved = repository.save(ServicioMapper.toEntity(request));
        return ServicioMapper.toResponse(saved);
    }

    @Override
    public ServicioResponse update(Integer idServicio, ServicioRequest dto) {
        Servicio existing = repository.findById(idServicio)
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado: " + idServicio));
        ServicioMapper.copyToEntity(dto, existing);
        Servicio saved = repository.save(existing);
        return ServicioMapper.toResponse(saved);
    }

    @Override
    public void delete(Integer idServicio) {
        if (!repository.existsById(idServicio)) {
            throw new EntityNotFoundException("Servicio no encontrado: " + idServicio);
        }
        repository.deleteById(idServicio);
    }
}
