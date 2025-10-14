package com.mapachebigoton.mapache.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mapachebigoton.mapache.service.ClienteService;
import com.mapachebigoton.mapache.dto.ClienteRequest;
import com.mapachebigoton.mapache.dto.ClienteResponse;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})

public class ClienteController {
     private final ClienteService service;

    @GetMapping
    public List<ClienteResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{idCliente}")
    public ClienteResponse getById(@PathVariable Integer idCliente) {
        return service.findById(idCliente);
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> create(@Valid @RequestBody ClienteRequest req) {
        ClienteResponse created = service.create(req);
        return ResponseEntity
                .created(URI.create("/clientes/" + created.getIdCliente()))
                .body(created);
    }

    @PutMapping("/{idCliente}")
    public ClienteResponse update(@PathVariable Integer idCliente, @Valid @RequestBody ClienteRequest req) {
        return service.update(idCliente, req);
    }

    @DeleteMapping("/{idCliente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer idCliente) {
        service.delete(idCliente);
    }   
}
