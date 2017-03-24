package com.algaworks.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.CervejasRepository;

@Service
public class CadastroCervejaService {

	@Autowired
	private CervejasRepository cervejasRepository;
	
	@Transactional
	public void save(Cerveja cerveja) {
		cervejasRepository.save(cerveja);
	}
}
