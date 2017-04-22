package com.algaworks.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.brewer.model.Estado;

@Repository
public interface EstadosRepository extends JpaRepository<Estado, Long> {

}