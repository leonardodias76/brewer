package com.algaworks.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.helper.cerveja.CervejasRepositoryQueries;

@Repository
public interface CervejasRepository extends JpaRepository<Cerveja, Long>, CervejasRepositoryQueries {

	public Optional<Cerveja> findBySku(String sku);
}
