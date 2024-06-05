package br.com.springjpa.mongodb.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.springjpa.mongodb.entity.Usuario;
import br.com.springjpa.mongodb.repository.UsuarioRepository;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		/*
		 * 1 - Save
		 * 2 - update
		 * 3 - delete
		 * 4 - consultar tudo
		 * 5 - Consultar um Usuario
		 * 6 - Consultar Por Nome
		 * 7 - Consultar Aproximada Por Nome
		 * 8 - Consultar Por Email
		 */
		int opcao = 4;
		
		switch(opcao) {			
			case 1 : 
				createUser("Marcelo", "mdomingues53@hotmail.com");
				createUser("Michele", "chele53@hotmail.com");
				createUser("Manuela", "manu@hotmail.com");
				break;
			case 2:
				updateUser(52L, "Marcelo Domingues", null);
				updateUser(53L, "Michele Domingues", null);
				updateUser(54L, "Manuela Domingues", null);
				break;
			case 3:
				deleteUser(52L);
				break;
			case 4:
				consultarTudo();
				break;
			case 5:
				consultarUsuario(53L);
				break;
			case 6:
				consultarPorNome("michele domingues");
				break;
			case 7:
				consultarPorLikeNome("Ma");
				break;
			case 8:
				consultarPorEmail("chele53@hotmail.com");
				break;
			default:
				System.out.println("Digite alguma opcao");
		}						
		
		
	}

	private void consultarPorEmail(String email) {
		Usuario usuario = userRepository.findByEmail(email);
		
		if (usuario != null) {
			System.out.println("Nome :" + usuario.getNome());
			System.out.println("Email :" + usuario.getEmail());
		}else {
			System.out.println("Nenhum registro encontrado");
		}
	}

	private void consultarPorLikeNome(String string) {
		Usuario usuario = userRepository.findByLikeNome("Ma");
		
		if (usuario != null) {
			System.out.println("Nome :" + usuario.getNome());
		}else {
			System.out.println("Nenhum registro encontrado");
		}
		
	}

	private void consultarPorNome(String nome) {
		Usuario usuario = userRepository.findByNomeIgnoreCase(nome.toUpperCase());
		
		if (usuario != null) {
			System.out.println("Nome  :" + usuario.getNome());
		}else {
			System.out.println("Nenhum registro encontrado");
		}
		
	}

	private void consultarUsuario(Long id) {
		Usuario usuario = userRepository.getOne(id);
		if (usuario != null){
			System.out.println("Nome: " + usuario.getNome());
		}else {
			System.out.println("Nenhum registro encontrado");
		}
		
	}
	private void consultarTudo() {

		List<Usuario> lista = userRepository.findAll();
		
		if (!lista.isEmpty()) {
			for (Usuario usuario : lista) {
				System.out.println("Nome : " + usuario.getNome());
			}
		}else {
			System.out.println("Nenhum usuario encontrado");
		}
		
	}

	private void deleteUser(Long id) {
		userRepository.deleteById(id);
		System.out.println("Usuario excluido com sucesso");
	}

	private void createUser(String nome, String senha) {
		Usuario usuario = new  Usuario(nome, senha);
		userRepository.save(usuario);
	}
	
	private void updateUser(Long id, String novoNome, String novoEmail) {
		
		Usuario usuario = (Usuario) userRepository.getReferenceById(id);
		
		if (novoNome != null && !"".equals(novoNome)) {
			usuario.setNome(novoNome);
		}
		
		if (novoEmail != null && !"".equals(novoEmail)) {
			usuario.setEmail(novoEmail);		
		}
		
		userRepository.save(usuario);
	}
}
