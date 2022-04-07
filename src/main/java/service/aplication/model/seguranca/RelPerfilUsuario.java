package service.aplication.model.seguranca;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "rel_perfil_usuario", schema = "seguranca")
@SequenceGenerator(name = "sq_relperfil", sequenceName = "seguranca.sq_reperfil", allocationSize = 1)
public class RelPerfilUsuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_relperfil")
	private Long id;	
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "perfil_usuario_fk", nullable = false)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "perfil_fk", nullable = false)
	private Perfil perfil;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}	

}
