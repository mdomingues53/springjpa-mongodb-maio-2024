package br.com.springjpa.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.springjpa.mongodb.entity.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, Long>{

	List<Usuario> findByNomeIgnoreCaseLike(String nome);		
	
	List<Usuario> findByNomeIgnoreCase(String nome);	
	
	@Query("{ 'email' : ?0}")
	List<Usuario> findByEmailUsuario(String email);
	
}
