package com.algaworks.brewer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.repository.UsuarioRepository;
import com.algaworks.brewer.service.exeptions.EmailUsuarioJaCadastradoException;
import com.algaworks.brewer.service.exeptions.SenhaObrigatoriaUsuarioException;

@Service
public class CadastroUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new EmailUsuarioJaCadastradoException("E-mail já cadastrado");
		}
		
		if (usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória para novo usuário");
		}
		
		if (usuario.isNovo() || !StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		} else if (StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(usuarioExistente.get().getSenha());
		}
		usuario.setConfirmacaoSenha(usuario.getSenha());
		
		if (!usuario.isNovo() && usuario.getAtivo() == null) {
			usuario.setAtivo(usuarioExistente.get().getAtivo());
		}
		
		usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void alterarStatus(Long[] codigos, StatusUsuario statusUsuario) {
		statusUsuario.executar(codigos, usuarioRepository);
	}

}
