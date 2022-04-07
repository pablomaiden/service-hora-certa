package service.aplication.repository.usuario;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import service.aplication.model.seguranca.RelPerfilUsuario;
import service.aplication.model.seguranca.Usuario;

public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPermissao(Long idUsuario) {
		StringBuilder query = new StringBuilder();
		query.append(" select func.role as permissao from seguranca.usuario usuario");
		query.append(" inner join seguranca.rel_perfil_usuario relperfil on usuario.id=relperfil.perfil_usuario_fk");
		query.append(" inner join seguranca.perfil perfil on perfil.id=relperfil.perfil_fk");
		query.append(" inner join seguranca.rel_perfil relp on perfil.id=relp.perfil_fk");
		query.append(" inner join seguranca.funcionalidades func on func.id=relp.funcionalidade_fk");
		query.append(" where usuario.id=?");

		Query query_ = manager.createNativeQuery(query.toString());
		query_.setParameter(1, idUsuario);
		return query_.getResultList();
	}
	
	
	@Override
	public void salvarPerfilUsuario(RelPerfilUsuario perfil) {		
		manager.persist(perfil);		
	}
	
	@Override
	public void updateUsuario(Usuario usuario) {		
		manager.merge(usuario);		
	}

	

}
