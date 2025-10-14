package com.mapachebigoton.mapache.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapachebigoton.mapache.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    
}
