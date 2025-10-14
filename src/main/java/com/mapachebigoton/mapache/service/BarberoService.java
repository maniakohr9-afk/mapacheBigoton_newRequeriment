package com.mapachebigoton.mapache.service;

import java.util.List;

import com.mapachebigoton.mapache.dto.BarberoRequest;
import com.mapachebigoton.mapache.dto.BarberoResponse;

public interface BarberoService {
    List<BarberoResponse> findAll();

	BarberoResponse findById(Integer idBarbero);

	BarberoResponse create(BarberoRequest req);

	BarberoResponse update(Integer idMedicina, BarberoRequest req);

	void delete(Integer idBarbero);
}
