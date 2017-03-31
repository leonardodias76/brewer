package com.algaworks.brewer.repository.helper.cerveja;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.filter.CervejaFilter;

public class CervejasRepositoryImpl implements CervejasRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Cerveja> filtrar(CervejaFilter cervejaFilter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cerveja.class);
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		
		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistrosPorPagina);
		
		if (cervejaFilter != null) {
			if (!StringUtils.isEmpty(cervejaFilter.getSku())) {
				criteria.add(Restrictions.eq("sku", cervejaFilter.getSku()));
			}
			
			if (!StringUtils.isEmpty(cervejaFilter.getNome())) {
				criteria.add(Restrictions.ilike("nome", cervejaFilter.getNome(), MatchMode.ANYWHERE));
			}

			if (isEstiloPresente(cervejaFilter)) {
				criteria.add(Restrictions.eq("estilo", cervejaFilter.getEstilo()));
			}

			if (cervejaFilter.getSabor() != null) {
				criteria.add(Restrictions.eq("sabor", cervejaFilter.getSabor()));
			}

			if (cervejaFilter.getOrigem() != null) {
				criteria.add(Restrictions.eq("origem", cervejaFilter.getOrigem()));
			}

			if (cervejaFilter.getValorDe() != null) {
				criteria.add(Restrictions.ge("valor", cervejaFilter.getValorDe()));
			}

			if (cervejaFilter.getValorAte() != null) {
				criteria.add(Restrictions.le("valor", cervejaFilter.getValorAte()));
			}
		}
		return criteria.list();
	}
	
	private boolean isEstiloPresente(CervejaFilter filtro) {
		return filtro.getEstilo() != null && filtro.getEstilo().getCodigo() != null;
	}

}
