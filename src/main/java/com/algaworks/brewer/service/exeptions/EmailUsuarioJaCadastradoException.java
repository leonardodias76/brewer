package com.algaworks.brewer.service.exeptions;

public class EmailUsuarioJaCadastradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmailUsuarioJaCadastradoException(String message) {
		super(message);
	}

}