package com.algaworks.brewer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.model.Estado;
import com.algaworks.brewer.repository.helper.cidade.CidadesRepositoryQueries;

public interface CidadesRepository extends JpaRepository<Cidade, Long>, CidadesRepositoryQueries {

	public List<Cidade> findByEstadoCodigo(Long codigoEstado);
	
	public Optional<Cidade> findByNomeAndEstado(String nome, Estado estado);

}