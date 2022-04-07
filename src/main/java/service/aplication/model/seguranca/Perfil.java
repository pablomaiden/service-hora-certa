package service.aplication.model.seguranca;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "perfil", schema = "seguranca")
@SequenceGenerator(name = "sq_perfil", sequenceName = "seguranca.sq_perfil", allocationSize = 1)
public class Perfil {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_perfil")
	private Long id;	
	
	@Column(name="perfil")
	private String perfil;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
	private List<RelPerfil> perfis;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public List<RelPerfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<RelPerfil> perfis) {
		this.perfis = perfis;
	}

	

}
