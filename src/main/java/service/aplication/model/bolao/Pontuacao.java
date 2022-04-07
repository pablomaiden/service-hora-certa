package service.aplication.model.bolao;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import service.aplication.model.seguranca.Usuario;

@Entity
@Table(name = "pontuacao", schema = "bolao")
public class Pontuacao {
	
	@EmbeddedId
	private PontuacaoId id;
	
	@Column(name = "pontos")
	private Integer pontos;
	
	@Column(name = "trofeus")
	private Integer trofeus;
	
	public Pontuacao() {
		
	}
	
	public Pontuacao(Usuario usuario, Bolao bolao) {				
		PontuacaoId id_ = new PontuacaoId();
		id_.setUsuario(usuario);
		id_.setBolao(bolao);		
		this.setId(id_);		
	}

	public PontuacaoId getId() {
		return id;
	}

	public void setId(PontuacaoId id) {
		this.id = id;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public Integer getTrofeus() {
		return trofeus;
	}

	public void setTrofeus(Integer trofeus) {
		this.trofeus = trofeus;
	}	
	

}
