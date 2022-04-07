package service.aplication.model.bolao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import service.aplication.model.seguranca.Usuario;

@Embeddable
public class RelNewsId implements Serializable {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7933449565274666112L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_fk", nullable = false)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "news_fk", nullable = false)
	private News news;
	
	@Column(name = "id")
	private String id;
	
	public RelNewsId(){
		
	}
	
	public RelNewsId(Usuario usuario, News news, String id){
		this.usuario = usuario;
		this.news    = news;
		this.id      = id;
	}	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

}
