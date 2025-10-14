package com.mapachebigoton.mapache.service;

import java.util.List;

import com.mapachebigoton.mapache.dto.ServicioResponse;
import com.mapachebigoton.mapache.dto.ServicioRequest;

public interface ServicioService {
    List<ServicioResponse> findAll();

	ServicioResponse findById(Integer idServicio);

	ServicioResponse create(ServicioRequest req);

	ServicioResponse update(Integer idServicio, ServicioRequest req);

	void delete(Integer idServicio);
}
