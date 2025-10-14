package com.mapachebigoton.mapache.service;

import java.util.List;

import com.mapachebigoton.mapache.dto.CitaRequest;
import com.mapachebigoton.mapache.dto.CitaResponse;

public interface CitaService {
    List<CitaResponse> findAll();
    CitaResponse findById(Integer idCita);
    CitaResponse create(CitaRequest req);
    CitaResponse update(Integer idCita, CitaRequest req);
    void delete(Integer idCita);
}
