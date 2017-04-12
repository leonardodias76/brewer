package com.algaworks.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.algaworks.brewer.model.Estado;

public interface EstadosRepository extends JpaRepository<Estado, Long> {

}