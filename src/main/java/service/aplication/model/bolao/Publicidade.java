package service.aplication.model.bolao;

import javax.persistence.Column;
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
@Table(name = "publicidade", schema = "bolao")
@SequenceGenerator(name = "sq_publicidade", sequenceName = "bolao.sq_publicidade", allocationSize = 1)
public class Publicidade {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_publicidade")
	private Long id;	
	
	@Column(name = "path_image")
	private String pathImage;	
	
	@Column(name = "atual")
	private boolean atual;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bolao_fk", nullable = false)
	private Bolao bolao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	public boolean isAtual() {
		return atual;
	}

	public void setAtual(boolean atual) {
		this.atual = atual;
	}

	public Bolao getBolao() {
		return bolao;
	}

	public void setBolao(Bolao bolao) {
		this.bolao = bolao;
	}
	
	
	
	
	
	

}
