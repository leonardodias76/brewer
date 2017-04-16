package com.algaworks.brewer.service.exeptions;

public class CpfCnpjClienteJaCadastradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public CpfCnpjClienteJaCadastradoException(String message) {
		super(message);
	}
}