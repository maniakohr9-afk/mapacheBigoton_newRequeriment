package com.mapachebigoton.mapache.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mapachebigoton.mapache.dto.SucursalDTO;
import com.mapachebigoton.mapache.service.SucursalService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sucursales")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SucursalController {
    
    private final SucursalService sucursalService;
    
    /**
     * Obtener todas las sucursales
     * GET /api/sucursales
     */
    @GetMapping
    public ResponseEntity<List<SucursalDTO>> obtenerTodas() {
        List<SucursalDTO> sucursales = sucursalService.obtenerTodas();
        return ResponseEntity.ok(sucursales);
    }
    
    /**
     * Obtener todas las sucursales activas
     * GET /api/sucursales/activas
     */
    @GetMapping("/activas")
    public ResponseEntity<List<SucursalDTO>> obtenerActivas() {
        List<SucursalDTO> sucursales = sucursalService.obtenerActivas();
        return ResponseEntity.ok(sucursales);
    }
    
    /**
     * Obtener sucursal por ID
     * GET /api/sucursales/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<SucursalDTO> obtenerPorId(@PathVariable Long id) {
        return sucursalService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * Obtener sucursales por ciudad
     * GET /api/sucursales/ciudad/{ciudad}
     */
    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<List<SucursalDTO>> obtenerPorCiudad(@PathVariable String ciudad) {
        List<SucursalDTO> sucursales = sucursalService.obtenerPorCiudad(ciudad);
        return ResponseEntity.ok(sucursales);
    }
    
    /**
     * Buscar sucursales por dirección
     * GET /api/sucursales/buscar?direccion=texto
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<SucursalDTO>> buscarPorDireccion(@RequestParam String direccion) {
        List<SucursalDTO> sucursales = sucursalService.buscarPorDireccion(direccion);
        return ResponseEntity.ok(sucursales);
    }
    
    /**
     * Crear nueva sucursal
     * POST /api/sucursales
     */
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody SucursalDTO sucursalDTO) {
        try {
            SucursalDTO nuevaSucursal = sucursalService.crear(sucursalDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSucursal);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
    
    /**
     * Actualizar sucursal existente
     * PUT /api/sucursales/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id, 
            @Valid @RequestBody SucursalDTO sucursalDTO) {
        try {
            SucursalDTO sucursalActualizada = sucursalService.actualizar(id, sucursalDTO);
            return ResponseEntity.ok(sucursalActualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
    
    /**
     * Desactivar sucursal (soft delete)
     * PATCH /api/sucursales/{id}/desactivar
     */
    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<?> desactivar(@PathVariable Long id) {
        try {
            sucursalService.desactivar(id);
            return ResponseEntity.ok()
                    .body(Map.of("mensaje", "Sucursal desactivada exitosamente"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
    
    /**
     * Activar sucursal
     * PATCH /api/sucursales/{id}/activar
     */
    @PatchMapping("/{id}/activar")
    public ResponseEntity<?> activar(@PathVariable Long id) {
        try {
            sucursalService.activar(id);
            return ResponseEntity.ok()
                    .body(Map.of("mensaje", "Sucursal activada exitosamente"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
    
    /**
     * Eliminar sucursal permanentemente
     * DELETE /api/sucursales/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            sucursalService.eliminar(id);
            return ResponseEntity.ok()
                    .body(Map.of("mensaje", "Sucursal eliminada exitosamente"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }
    
    /**
     * Contar sucursales activas por ciudad
     * GET /api/sucursales/contar/{ciudad}
     */
    @GetMapping("/contar/{ciudad}")
    public ResponseEntity<Map<String, Long>> contarPorCiudad(@PathVariable String ciudad) {
        Long cantidad = sucursalService.contarPorCiudad(ciudad);
        return ResponseEntity.ok(Map.of("cantidad", cantidad));
    }
}