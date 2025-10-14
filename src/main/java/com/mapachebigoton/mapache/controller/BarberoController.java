package com.mapachebigoton.mapache.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mapachebigoton.mapache.service.BarberoService;
import com.mapachebigoton.mapache.dto.BarberoRequest;
import com.mapachebigoton.mapache.dto.BarberoResponse;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/barbero")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class BarberoController {
    private final BarberoService service;

    @GetMapping
    public List<BarberoResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{idBarbero}")
    public BarberoResponse getById(@PathVariable Integer idBarbero) {
        return service.findById(idBarbero);
    }

    @PostMapping
    public ResponseEntity<BarberoResponse> create(@Valid @RequestBody BarberoRequest req) {
        BarberoResponse created = service.create(req);
        return ResponseEntity
                .created(URI.create("/barbero/" + created.getIdBarbero()))
                .body(created);
    }

    @PutMapping("/{idBarbero}")
    public BarberoResponse update(@PathVariable Integer idBarbero, @Valid @RequestBody BarberoRequest req) {
        return service.update(idBarbero, req);
    }

    @DeleteMapping("/{idBarbero}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer idBarbero) {
        service.delete(idBarbero);
    }   
}
