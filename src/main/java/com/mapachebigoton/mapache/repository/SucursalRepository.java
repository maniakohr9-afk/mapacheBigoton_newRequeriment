package com.mapachebigoton.mapache.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mapachebigoton.mapache.model.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long>{
    List<Sucursal> findByActivoTrue();

    Optional<Sucursal> findByNombre(String nombre);

    List<Sucursal> findByCiudad(String ciudad);

    List<Sucursal> findByEstado(String estado);

    List<Sucursal> findByCiudadActivoTrue(String ciudad);

     // Búsqueda personalizada por dirección (contiene)
    @Query("SELECT s FROM Sucursal s WHERE LOWER(s.direccion) LIKE LOWER(CONCAT('%', :direccion, '%'))")
    List<Sucursal> buscarPorDireccionContiene(@Param("direccion") String direccion);
    
    // Verificar si existe una sucursal con ese nombre
    boolean existsByNombre(String nombre);
    
    // Contar sucursales activas por ciudad
    @Query("SELECT COUNT(s) FROM Sucursal s WHERE s.ciudad = :ciudad AND s.activo = true")
    Long contarSucursalesActivasPorCiudad(@Param("ciudad") String ciudad);
}
