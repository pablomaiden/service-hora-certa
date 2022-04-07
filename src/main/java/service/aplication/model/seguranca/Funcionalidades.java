package service.aplication.model.seguranca;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "funcionalidades", schema = "seguranca")
@SequenceGenerator(name = "sq_funcionalidades", sequenceName = "seguranca.sq_funcionalidades", allocationSize = 1)
public class Funcionalidades {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_funcionalidades")
	private Long id;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "ds_funcionalidade")
	private String dsFuncionalidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDsFuncionalidade() {
		return dsFuncionalidade;
	}

	public void setDsFuncionalidade(String dsFuncionalidade) {
		this.dsFuncionalidade = dsFuncionalidade;
	}
	
	
}
