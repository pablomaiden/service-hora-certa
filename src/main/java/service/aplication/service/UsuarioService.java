package service.aplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.aplication.dto.UsuarioDTO;
import service.aplication.model.seguranca.Perfil;
import service.aplication.model.seguranca.RelPerfilUsuario;
import service.aplication.model.seguranca.Usuario;
import service.aplication.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<String> getPermissao(Long idUsuario) {		
		List<Object[]> permissoes = usuarioRepository.getPermissao(idUsuario);	
		List<String> permissao = new ArrayList<String>();
		for(Object obj: permissoes) {
			//Object[] o = (Object[]) obj;
			permissao.add((String) obj);			
		}
		return permissao;
	}
	
	@Transactional
	public void saveUserAuthentication(UsuarioDTO dto) {
				
		 Optional<Usuario> _usuario = usuarioRepository.findByEmail(dto.getEmail());
		 
		 if(!_usuario.isPresent()) {
			    Usuario usuario = new Usuario();
				RelPerfilUsuario relPerfil = new RelPerfilUsuario();
				Perfil perfil = new Perfil();
							
				BeanUtils.copyProperties(dto,usuario);		
				usuarioRepository.save(usuario);	
				
				perfil.setId(3L);		
				relPerfil.setPerfil(perfil);
				relPerfil.setUsuario(usuario);	
				
				usuarioRepository.salvarPerfilUsuario(relPerfil);			 
		 }else {
			    _usuario.get().setToken(dto.getToken());	
			    _usuario.get().setuId(dto.getuId());				
			    usuarioRepository.updateUsuario(_usuario.get());
			 
		 }
	}

}
