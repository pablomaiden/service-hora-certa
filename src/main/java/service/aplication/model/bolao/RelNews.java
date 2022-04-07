package service.aplication.model.bolao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import service.aplication.model.seguranca.Usuario;

@Entity
@Table(name = "rel_news", schema = "bolao")
public class RelNews {
	
	@EmbeddedId
	RelNewsId id;
	
	@Column(name = "data_envio")
	private Date dataEnvio;
	
	@Column(name = "data_lido")
	private Date dataLido;
	
	@Column(name = "lido")
	private boolean lido;
	
	public RelNews() {
		
	}
	
    public RelNews(Usuario usuario, News news, String id) { 
    	this.id = new RelNewsId(usuario,news,id);    		
	}

	public RelNewsId getId() {
		return id;
	}

	public void setId(RelNewsId id) {
		this.id = id;
	}	

	public Date getDataLido() {
		return dataLido;
	}

	public void setDataLido(Date dataLido) {
		this.dataLido = dataLido;
	}

	public boolean isLido() {
		return lido;
	}

	public void setLido(boolean lido) {
		this.lido = lido;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	
	

}
