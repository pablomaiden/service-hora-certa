package service.aplication.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import service.aplication.model.seguranca.Usuario;
import service.aplication.repository.usuario.UsuarioRepositoryQuery;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery{
	
	public Optional<Usuario> findByEmail(String email);	
	public Optional<Usuario> findByEmailAndUId(String email,String uid);	
	public Optional<Usuario> findByUId(String uId);
	
	@Query("select usuario from Usuario usuario ")
	List<Usuario> listaUsuariosBolao();
		
}
