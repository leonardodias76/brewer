package com.algaworks.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.repository.UsuarioRepository;
import com.algaworks.brewer.service.exeptions.UsuarioJaCadastradoException;

@Service
public class CadastroUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(usuario.getEmail());
		if(usuarioOptional.isPresent()) {
			throw new UsuarioJaCadastradoException("Usuario já cadastrado.");
		}
		return usuarioRepository.saveAndFlush(usuario);
	}
}
