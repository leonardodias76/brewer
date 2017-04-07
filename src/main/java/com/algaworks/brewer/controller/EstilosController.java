package com.algaworks.brewer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.controller.page.PageWrapper;
import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.repository.filter.EstiloFilter;
import com.algaworks.brewer.repository.helper.estilo.EstilosRepositoryQueries;
import com.algaworks.brewer.service.EstilosService;
import com.algaworks.brewer.service.exeptions.EstiloJaCadastradoException;

@Controller
@RequestMapping("/estilos")
public class EstilosController {

	@Autowired
	private EstilosService estilosService;
	
	@Autowired
	private EstilosRepositoryQueries estilosRepository;

	@GetMapping("/novo")
	public ModelAndView novo(Estilo estilo) {
		ModelAndView mv = new ModelAndView("estilo/CadastroEstilo");
		mv.addObject("estilo", estilo);
		return mv;
	}

	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Estilo estilo, BindingResult result, RedirectAttributes attributes) {
		if (result.hasFieldErrors()) {
			return novo(estilo);
		}
		try {
			estilosService.salvar(estilo);
		} catch (EstiloJaCadastradoException e) {
			result.rejectValue("nome", null, e.getMessage());
			return novo(estilo);
		}
		attributes.addFlashAttribute("mensagem", "Estilo salvo com sucesso.");
		return new ModelAndView("redirect:/estilos/novo");
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Estilo estilo, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		estilo = estilosService.salvar(estilo);
		return ResponseEntity.ok(estilo);
	}

	@GetMapping()
	public ModelAndView pesquisar(EstiloFilter estiloFilter, BindingResult result, @PageableDefault(size = 2) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("estilo/PesquisaEstilos");

		PageWrapper<Estilo> paginaWrapper = new PageWrapper<>(estilosRepository.filtrar(estiloFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
}