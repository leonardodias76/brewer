package com.algaworks.brewer.service.exeptions;

public class SenhaObrigatoriaUsuarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SenhaObrigatoriaUsuarioException(String message) {
		super(message);
	}

}
