package com.algaworks.brewer.service.exeptions;

public class UsuarioJaCadastradoException extends RuntimeException {

	private static final long serialVersionUID = -2268662652416165828L;

	public UsuarioJaCadastradoException(String message) {
		super(message);
	}
}
