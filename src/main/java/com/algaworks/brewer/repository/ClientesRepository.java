package com.algaworks.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.brewer.model.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente, Long> {

}