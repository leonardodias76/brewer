package com.algaworks.brewer.service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.brewer.model.StatusVenda;
import com.algaworks.brewer.model.Venda;
import com.algaworks.brewer.repository.VendasRepository;;

@Service
public class CadastroVendaService {

	@Autowired
	private VendasRepository vendasRepository;

	@Transactional
	public Venda salvar(Venda venda) {
		if (venda.isNova()) {
			venda.setDataCriacao(LocalDateTime.now());
		} else {
			Venda vendaExistente = vendasRepository.findOne(venda.getCodigo());
			venda.setDataCriacao(vendaExistente.getDataCriacao());
		}

		if (venda.getDataEntrega() != null) {
			venda.setDataHoraEntrega(LocalDateTime.of(venda.getDataEntrega(), venda.getHorarioEntrega() != null ? venda.getHorarioEntrega() : LocalTime.NOON));
		}

		return vendasRepository.saveAndFlush(venda);
	}

	@Transactional
	public void emitir(Venda venda) {
		venda.setStatus(StatusVenda.EMITIDA);
		salvar(venda);
	}

}