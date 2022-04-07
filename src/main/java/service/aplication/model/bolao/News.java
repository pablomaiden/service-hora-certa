package service.aplication.model.bolao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "news", schema = "bolao")
@SequenceGenerator(name = "sq_news", sequenceName = "bolao.sq_news", allocationSize = 1)
public class News {
		
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_news")	
	private Long id;
	
	@Column(name = "informacao_titulo")	
	private String informacaoTitulo;
	
	@Column(name = "informacao_desc")	
	private String informacaoDesc;
	
	@Column(name = "path_imagem")	
	private String pathImagem;
		

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInformacaoTitulo() {
		return informacaoTitulo;
	}

	public void setInformacaoTitulo(String informacaoTitulo) {
		this.informacaoTitulo = informacaoTitulo;
	}

	public String getInformacaoDesc() {
		return informacaoDesc;
	}

	public void setInformacaoDesc(String informacaoDesc) {
		this.informacaoDesc = informacaoDesc;
	}

	public String getPathImagem() {
		return pathImagem;
	}

	public void setPathImagem(String pathImagem) {
		this.pathImagem = pathImagem;
	}
	
	
	

}
