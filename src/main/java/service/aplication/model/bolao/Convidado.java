package service.aplication.model.bolao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import service.aplication.model.seguranca.Usuario;

@Entity
@Table(name = "convidados", schema = "bolao")
public class Convidado {
	
	@EmbeddedId
	ConvidadoId id;
	
	@Column(name = "aceito", nullable = false)
	private boolean aceito;
	
	@Column(name = "data", nullable = false)
	private Date dataSolicitacao;
	
	@Column(name = "data_negada", nullable = false)
	private Date dataNegada;
	
	@Column(name = "data_aceite", nullable = false)
	private Date dataAceite;
	
	public Convidado(){
		
	}
	
    public Convidado(Usuario usuario, Bolao bolao){    	
    	ConvidadoId id  = new ConvidadoId();    	
    	id.setBolao(bolao);
    	id.setUsuario(usuario);    	
    	this.setId(id);    	
    	this.setDataSolicitacao(new Date());
    	this.setAceito(false);
	}

	public ConvidadoId getId() {
		return id;
	}

	public void setId(ConvidadoId id) {
		this.id = id;
	}

	public boolean isAceito() {
		return aceito;
	}

	public void setAceito(boolean aceito) {
		this.aceito = aceito;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	
	public Date getDataNegada() {
		return dataNegada;
	}

	public void setDataNegada(Date dataNegada) {
		this.dataNegada = dataNegada;
	}

	public Date getDataAceite() {
		return dataAceite;
	}

	public void setDataAceite(Date dataAceite) {
		this.dataAceite = dataAceite;
	}
	
	
	

}
