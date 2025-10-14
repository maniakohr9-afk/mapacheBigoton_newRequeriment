package com.mapachebigoton.mapache.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mapachebigoton.mapache.dto.CitaRequest;
import com.mapachebigoton.mapache.dto.CitaResponse;
import com.mapachebigoton.mapache.service.CitaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/citas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class CitaController {
    private final CitaService service;

    @GetMapping
    public List<CitaResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{idCita}")
    public CitaResponse getById(@PathVariable Integer idCita) {
        return service.findById(idCita);
    }

    @PostMapping
    public ResponseEntity<CitaResponse> create(@Valid @RequestBody CitaRequest req) {
        CitaResponse created = service.create(req);
        return ResponseEntity
                .created(URI.create("/citas/" + created.getIdCita()))
                .body(created);
    }

    @PutMapping("/{idCita}")
    public CitaResponse update(@PathVariable Integer idCita, @Valid @RequestBody CitaRequest req) {
        return service.update(idCita, req);
    }

    @DeleteMapping("/{idCita}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer idCita) {
        service.delete(idCita);
    }    
}
