package service.aplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tabela_medidas", schema="public")
public class TabelaMedidas implements java.io.Serializable {
		
	private static final long serialVersionUID = 2490090622499080501L;

	@Id
	private Long id;
	  
	@Column(name = "medida")
	private String medida;
	  
	@Column(name = "gramas")
	private Integer gramas;
	  
	@Column(name = "tipo")
	private String tipo;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_tabela_nutricional", nullable = false)
    private TabelaNutricional tabelaNutricional;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public Integer getGramas() {
		return gramas;
	}

	public void setGramas(Integer gramas) {
		this.gramas = gramas;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public TabelaNutricional getTabelaNutricional() {
		return tabelaNutricional;
	}

	public void setTabelaNutricional(TabelaNutricional tabelaNutricional) {
		this.tabelaNutricional = tabelaNutricional;
	}	  
	  
	  	

}
