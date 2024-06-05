package br.com.springjpa.mongodb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.springjpa.mongodb.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByNomeIgnoreCase(String nome);
	
	@Query("select u from Usuario u where u.nome like %?1%")
	Usuario findByLikeNome(String nome);
	
	
	Usuario findByEmail(String email);
	
}
