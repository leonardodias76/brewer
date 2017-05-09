package com.algaworks.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.repository.UsuarioRepository;
import com.algaworks.brewer.service.exeptions.SenhaObrigatoriaUsuarioException;
import com.algaworks.brewer.service.exeptions.UsuarioJaCadastradoException;

@Service
public class CadastroUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(usuario.getEmail());
		if(usuarioOptional.isPresent()) {
			throw new UsuarioJaCadastradoException("Usuario já cadastrado.");
		}
		
		if (usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória para novo usuário");
		}
		
		if (usuario.isNovo()) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
			usuario.setConfirmacaoSenha(usuario.getSenha());
		}
		
		return usuarioRepository.saveAndFlush(usuario);
	}
	
	@Transactional
	public void alterarStatus(Long[] codigos, StatusUsuario statusUsuario) {
		statusUsuario.executar(codigos, usuarioRepository);
	}

}
