package service.aplication.model.bolao;

import java.io.Serializable;
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
@Table(name = "campeonato", schema = "bolao")
@SequenceGenerator(name = "sq_campeonato", sequenceName = "bolao.sq_campeonato", allocationSize = 1)
public class Campeonato implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2628129423824222453L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_campeonato")	
	private Long id;	
	
	@Column(name = "nome")	
	private String nome;
		
	@Column(name = "rodada")
	private String rodada;
	
	@Column(name = "path_image")
	private String pathImage;
		
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "campeonato")
	private List<Bolao> boloes;

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

	public String getRodada() {
		return rodada;
	}

	public void setRodada(String rodada) {
		this.rodada = rodada;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	public List<Bolao> getBoloes() {
		return boloes;
	}

	public void setBoloes(List<Bolao> boloes) {
		this.boloes = boloes;
	}	

}
