package com.algaworks.brewer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.brewer.model.Cliente;
import com.algaworks.brewer.repository.helper.cliente.ClientesRepositoryQueries;

@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Long>, ClientesRepositoryQueries {

	Optional<Cliente> findByCpfOuCnpj(String cpfOuCnpjSemFormatacao);

}