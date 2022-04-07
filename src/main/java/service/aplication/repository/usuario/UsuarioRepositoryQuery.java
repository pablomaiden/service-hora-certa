package service.aplication.repository.usuario;

import java.util.List;

import service.aplication.model.seguranca.RelPerfilUsuario;
import service.aplication.model.seguranca.Usuario;

public interface UsuarioRepositoryQuery {	
	public List<Object[]> getPermissao(Long idUsuario);
	public void salvarPerfilUsuario(RelPerfilUsuario perfil);
	public void updateUsuario(Usuario usuario);
}
