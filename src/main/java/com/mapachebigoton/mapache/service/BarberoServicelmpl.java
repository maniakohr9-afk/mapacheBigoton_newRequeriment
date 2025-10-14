package com.mapachebigoton.mapache.service;

import com.mapachebigoton.mapache.model.Barbero;
import com.mapachebigoton.mapache.repository.BarberoRepository;
import com.mapachebigoton.mapache.mapper.BarberoMapper;
import com.mapachebigoton.mapache.dto.BarberoRequest;
import com.mapachebigoton.mapache.dto.BarberoResponse;

import java.util.List;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class BarberoServicelmpl implements BarberoService {
    private final BarberoRepository repository;

    @Override
    public List<BarberoResponse> findAll() {
        return repository.findAll().stream()
                .map(BarberoMapper::toResponse)
                .toList();
    }

    @Override
    public BarberoResponse findById(Integer idBarbero) {
        Barbero barbero = repository.findById(idBarbero)
                .orElseThrow(() -> new EntityNotFoundException("Barbero no encontrado: " + idBarbero));
        return BarberoMapper.toResponse(barbero);
    }

    @Override
    public BarberoResponse create(BarberoRequest request) {
        Barbero saved = repository.save(BarberoMapper.toEntity(request));
        return BarberoMapper.toResponse(saved);
    }

    @Override
    public BarberoResponse update(Integer idMedicina, BarberoRequest dto) {
        Barbero existing = repository.findById(idMedicina)
                .orElseThrow(() -> new EntityNotFoundException("Barbero no encontrado: " + idMedicina));
        BarberoMapper.copyToEntity(dto, existing);
        Barbero saved = repository.save(existing);
        return BarberoMapper.toResponse(saved);
    }

    @Override
    public void delete(Integer idBarbero) {
        if (!repository.existsById(idBarbero)) {
            throw new EntityNotFoundException("Barbero no encontrado: " + idBarbero);
        }
        repository.deleteById(idBarbero);
    }
}
