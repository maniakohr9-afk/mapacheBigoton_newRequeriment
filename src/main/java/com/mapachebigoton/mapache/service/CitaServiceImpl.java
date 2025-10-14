/*package com.mapachebigoton.mapache.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mapachebigoton.mapache.dto.CitaRequest;
import com.mapachebigoton.mapache.dto.CitaResponse;
import com.mapachebigoton.mapache.mapper.CitaMapper;

import com.mapachebigoton.mapache.model.Cita;

import com.mapachebigoton.mapache.repository.CitaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService{
    private final CitaRepository repository;

    @Override
    public List<CitaResponse> findAll() {
        return repository.findAll().stream()
                .map(CitaMapper::toResponse)
                .toList();
    }

    @Override
    public CitaResponse findById(Integer idCita) {
        Cita cita = repository.findById(idCita)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada: " + idCita));
        return CitaMapper.toResponse(cita);
    }

    @Override
    public CitaResponse create(CitaRequest request) {
        Cita saved = repository.save(CitaMapper.toEntity(request));
        return CitaMapper.toResponse(saved);
    }

    @Override
    public CitaResponse update(Integer idCita, CitaRequest dto) {
        Cita existing = repository.findById(idCita)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrado: " + idCita));
        CitaMapper.copyToEntity(dto, existing);
        Cita saved = repository.save(existing);
        return CitaMapper.toResponse(saved);
    }

    @Override
    public void delete(Integer idCita) {
        if (!repository.existsById(idCita)) {
            throw new EntityNotFoundException("Cita no encontrado: " + idCita);
        }
        repository.deleteById(idCita);
    }
    
}
*/
package com.mapachebigoton.mapache.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mapachebigoton.mapache.dto.CitaRequest;
import com.mapachebigoton.mapache.dto.CitaResponse;
import com.mapachebigoton.mapache.mapper.CitaMapper;
import com.mapachebigoton.mapache.model.Cita;
import com.mapachebigoton.mapache.repository.CitaRepository;
import com.mapachebigoton.mapache.repository.ClienteRepository;
import com.mapachebigoton.mapache.repository.BarberoRepository;
import com.mapachebigoton.mapache.repository.ServicioRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {
    private final CitaRepository repository;
    private final ClienteRepository clienteRepository;
    private final BarberoRepository barberoRepository;
    private final ServicioRepository servicioRepository;

    @Override
    public List<CitaResponse> findAll() {
        return repository.findAll().stream()
                .map(CitaMapper::toResponse)
                .toList();
    }

    @Override
    public CitaResponse findById(Integer idCita) {
        Cita cita = repository.findById(idCita)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada: " + idCita));
        return CitaMapper.toResponse(cita);
    }

    @Override
    public CitaResponse create(CitaRequest request) {
        Cita cita = CitaMapper.toEntity(request);

        cita.setCliente(clienteRepository.findById(request.getIdCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado: " + request.getIdCliente())));
        cita.setBarbero(barberoRepository.findById(request.getIdBarbero())
                .orElseThrow(() -> new EntityNotFoundException("Barbero no encontrado: " + request.getIdBarbero())));
        cita.setServicio(servicioRepository.findById(request.getIdServicio())
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado: " + request.getIdServicio())));

        Cita saved = repository.save(cita);
        return CitaMapper.toResponse(saved);
    }

    @Override
    public CitaResponse update(Integer idCita, CitaRequest dto) {
        Cita existing = repository.findById(idCita)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada: " + idCita));

        CitaMapper.copyToEntity(dto, existing);

        existing.setCliente(clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado: " + dto.getIdCliente())));
        existing.setBarbero(barberoRepository.findById(dto.getIdBarbero())
                .orElseThrow(() -> new EntityNotFoundException("Barbero no encontrado: " + dto.getIdBarbero())));
        existing.setServicio(servicioRepository.findById(dto.getIdServicio())
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado: " + dto.getIdServicio())));

        Cita saved = repository.save(existing);
        return CitaMapper.toResponse(saved);
    }

    @Override
    public void delete(Integer idCita) {
        if (!repository.existsById(idCita)) {
            throw new EntityNotFoundException("Cita no encontrada: " + idCita);
        }
        repository.deleteById(idCita);
    }
}
