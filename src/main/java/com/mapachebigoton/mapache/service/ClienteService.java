package com.mapachebigoton.mapache.service;

import java.util.List;

import com.mapachebigoton.mapache.dto.ClienteRequest;
import com.mapachebigoton.mapache.dto.ClienteResponse;

public interface ClienteService {
    List<ClienteResponse> findAll();

	ClienteResponse findById(Integer idCliente);

	ClienteResponse create(ClienteRequest req);

	ClienteResponse update(Integer idCliente, ClienteRequest req);

	void delete(Integer idCliente);
}
