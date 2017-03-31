package com.algaworks.brewer.repository.helper.cerveja;

import java.util.List;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.filter.CervejaFilter;

public interface CervejasRepositoryQueries {

	public List<Cerveja> filtrar(CervejaFilter cervejaFilter);
}
