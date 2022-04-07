package service.aplication.model.bolao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "times", schema = "bolao")
@SequenceGenerator(name = "sq_time", sequenceName = "bolao.sq_time", allocationSize = 1)
public class Time {
		
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_time")
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "url_image", nullable = false)
	private String urlImage;
	
	@Column(name = "nome_abreviado", nullable = false)
	private String nomeAbreviado;
	
	@Column(name = "cod_api_time", insertable=false, updatable=false)
	private String codApiTime;
	
	public Time() {
		
	}
	
    public Time(Long id) {
    	this.setId(id);		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getNomeAbreviado() {
		return nomeAbreviado;
	}

	public void setNomeAbreviado(String nomeAbreviado) {
		this.nomeAbreviado = nomeAbreviado;
	}

	public String getCodApiTime() {
		return codApiTime;
	}

	public void setCodApiTime(String codApiTime) {
		this.codApiTime = codApiTime;
	}	
	
	

}
