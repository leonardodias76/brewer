package com.algaworks.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.repository.EstilosRepository;
import com.algaworks.brewer.service.exeptions.EstiloJaCadastradoException;

@Service
public class CadastroEstiloService {

	@Autowired
	private EstilosRepository estilosRepository;

	@Transactional
	public Estilo salvar(Estilo estilo) {
		Optional<Estilo> estiloOptional = estilosRepository.findByNomeIgnoreCase(estilo.getNome());
		if(estiloOptional.isPresent()) {
			throw new EstiloJaCadastradoException("Estilo já cadastrado.");
		}
		return estilosRepository.saveAndFlush(estilo);
	}
}
