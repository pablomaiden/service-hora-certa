package service.aplication.model.bolao;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import service.aplication.model.seguranca.Usuario;

@Entity
@Table(name = "top_rodada_pontuacao", schema = "bolao")
public class TopRodada {
		
	@EmbeddedId
	private TopRodadaId id;
	
	@Column(name = "pontuacao")
	private Integer pontos;
	
	@Column(name = "trofeu")
	private Integer trofeus;
	
	public TopRodada() {
		
	}
	
	public TopRodada(Usuario usuario, Bolao bolao, Rodada rodada) {		
		TopRodadaId id_ = new TopRodadaId();
		id_.setUsuario(usuario);
		id_.setBolao(bolao);
		id_.setRodada(rodada);
		this.setId(id_);		
	}

	public TopRodadaId getId() {
		return id;
	}

	public void setId(TopRodadaId id) {
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
