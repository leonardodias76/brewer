package com.algaworks.brewer.service.exeptions;

public class ImpossivelExcluirEntidadeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ImpossivelExcluirEntidadeException(String msg) {
		super(msg);
	}

}