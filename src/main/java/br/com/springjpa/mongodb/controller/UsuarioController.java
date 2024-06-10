package br.com.springjpa.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.springjpa.mongodb.entity.Usuario;
import br.com.springjpa.mongodb.service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;	
	
	@RequestMapping("/user")
	public String findAll(Model model) {		
		List<Usuario> listaUsuarios = this.usuarioService.findAll();
		model.addAttribute("listaUsuarios", listaUsuarios);
		return "listaUsuarios";
	}
}