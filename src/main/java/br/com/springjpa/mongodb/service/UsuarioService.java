package br.com.springjpa.mongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springjpa.mongodb.entity.Usuario;
import br.com.springjpa.mongodb.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll(){
		return this.usuarioRepository.findAll();
	}

}
