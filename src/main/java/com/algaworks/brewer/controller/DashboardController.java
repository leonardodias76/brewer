package com.algaworks.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.algaworks.brewer.repository.VendasRepository;

@Controller
public class DashboardController {
	
	@Autowired
	private VendasRepository vendasRepository;

	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("Dashboard");
		mv.addObject("vendasNoAno", vendasRepository.valorTotalNoAno());
		mv.addObject("vendasNoMes", vendasRepository.valorTotalNoMes());
		mv.addObject("ticketMedio", vendasRepository.valorTicketMedioNoAno());
		return mv;
	}
}