package com.algaworks.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.brewer.repository.CervejasRepository;
import com.algaworks.brewer.repository.ClientesRepository;
import com.algaworks.brewer.repository.VendasRepository;

@Controller
public class DashboardController {
	
	@Autowired
	private VendasRepository vendasRepository;
	
	@Autowired
	private CervejasRepository cervejasRepository;
	
	@Autowired
	private ClientesRepository clientesRepository;

	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("Dashboard");
		mv.addObject("vendasNoAno", vendasRepository.valorTotalNoAno());
		mv.addObject("vendasNoMes", vendasRepository.valorTotalNoMes());
		mv.addObject("ticketMedio", vendasRepository.valorTicketMedioNoAno());
		mv.addObject("valorItensEstoque", cervejasRepository.valorItensEstoque());
		mv.addObject("totalClientes", clientesRepository.count());
		return mv;
	}
}