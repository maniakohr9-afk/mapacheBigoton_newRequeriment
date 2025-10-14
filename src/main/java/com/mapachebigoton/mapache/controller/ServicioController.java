package com.mapachebigoton.mapache.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mapachebigoton.mapache.service.ServicioService;
import com.mapachebigoton.mapache.dto.ServicioRequest;
import com.mapachebigoton.mapache.dto.ServicioResponse;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/servicios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class ServicioController {
     private final ServicioService service;

    @GetMapping
    public List<ServicioResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{idServicio}")
    public ServicioResponse getById(@PathVariable Integer idServicio) {
        return service.findById(idServicio);
    }

    @PostMapping
    public ResponseEntity<ServicioResponse> create(@Valid @RequestBody ServicioRequest req) {
        ServicioResponse created = service.create(req);
        return ResponseEntity
                .created(URI.create("/servicio/" + created.getIdServicio()))
                .body(created);
    }

    @PutMapping("/{idServicio}")
    public ServicioResponse update(@PathVariable Integer idServicio, @Valid @RequestBody ServicioRequest req) {
        return service.update(idServicio, req);
    }

    @DeleteMapping("/{idServicio}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer idServicio) {
        service.delete(idServicio);
    }   
}
