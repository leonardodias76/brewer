package com.algaworks.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.repository.helper.estilo.EstilosRepositoryQueries;

@Repository
public interface EstilosRepository extends JpaRepository<Estilo, Long>, EstilosRepositoryQueries {

	public Optional<Estilo> findByNomeIgnoreCase(String nome);
}
