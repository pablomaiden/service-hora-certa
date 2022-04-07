package service.aplication.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="pesquisa_usuario", schema="public")
@SequenceGenerator(name = "sq_pesquisa_usuario", sequenceName = "public.sq_pesquisa_usuario", allocationSize = 1)
public class PesquisaUsuario implements java.io.Serializable{	
		
	private static final long serialVersionUID = -5032042111015502832L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_pesquisa_usuario")
	@Column(name = "id")
	private Long id;
	
	@Column(name="campo_pesquisa")
	private String campoPesquisa;
	
	@Column(name="data_cadastro")
	private Date dataCadastro;
	
	@Transient
	private String data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCampoPesquisa() {
		return campoPesquisa;
	}

	public void setCampoPesquisa(String campoPesquisa) {
		this.campoPesquisa = campoPesquisa;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}	

}
