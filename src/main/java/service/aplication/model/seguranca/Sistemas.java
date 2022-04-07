package service.aplication.model.seguranca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "perfil", schema = "seguranca")
@SequenceGenerator(name = "sq_sistemas", sequenceName = "seguranca.sq_sistemas", allocationSize = 1)
public class Sistemas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_sistemas")
	private Long id;
	
	@Column(name="sistema")
	private String sistema;
	
	@Column(name="ds_sistema")
	private String dsSistema;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getDsSistema() {
		return dsSistema;
	}

	public void setDsSistema(String dsSistema) {
		this.dsSistema = dsSistema;
	}	

}
