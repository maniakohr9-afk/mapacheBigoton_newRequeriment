package com.mapachebigoton.mapache.service;

import com.mapachebigoton.mapache.model.Cliente;
import com.mapachebigoton.mapache.repository.ClienteRepository;
import com.mapachebigoton.mapache.mapper.ClienteMapper;
import com.mapachebigoton.mapache.dto.ClienteRequest;
import com.mapachebigoton.mapache.dto.ClienteResponse;

import java.util.List;

import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServicelmpl implements ClienteService{
    private final ClienteRepository repository;

    @Override
    public List<ClienteResponse> findAll() {
        return repository.findAll().stream()
                .map(ClienteMapper::toResponse)
                .toList();
    }

    @Override
    public ClienteResponse findById(Integer idCliente) {
        Cliente cliente = repository.findById(idCliente)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado: " + idCliente));
        return ClienteMapper.toResponse(cliente);
    }

    @Override
    public ClienteResponse create(ClienteRequest request) {
        Cliente saved = repository.save(ClienteMapper.toEntity(request));
        return ClienteMapper.toResponse(saved);
    }

    @Override
    public ClienteResponse update(Integer idCliente, ClienteRequest dto) {
        Cliente existing = repository.findById(idCliente)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado: " + idCliente));
        ClienteMapper.copyToEntity(dto, existing);
        Cliente saved = repository.save(existing);
        return ClienteMapper.toResponse(saved);
    }

    @Override
    public void delete(Integer idCliente) {
        if (!repository.existsById(idCliente)) {
            throw new EntityNotFoundException("Cliente no encontrado: " + idCliente);
        }
        repository.deleteById(idCliente);
    }
}
