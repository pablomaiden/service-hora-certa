package service.aplication.model.bolao;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import service.aplication.model.seguranca.Usuario;


@Embeddable
public class PalpitesId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8578850809253191607L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_fk", nullable = false)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rodada_fk", nullable = false)
	private Rodada rodada;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "time_a_fk", nullable = false)
	private Time timeA;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "time_b_fk", nullable = false)
	private Time timeB;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bolao_fk", nullable = false)
	private Bolao bolao;
	
	public PalpitesId(Long usuarioId, Long timeAId, Long timeBId, Long rodadaId, Long bolaoId) {
		
		Usuario usurio_ = new Usuario();
		usurio_.setId(usuarioId);
		this.setUsuario(usurio_);
		
		Time timeA_ = new Time();
		timeA_.setId(timeAId);
		this.setTimeA(timeA_);
		
		Time timeB_ = new Time();
		timeB_.setId(timeBId);
		this.setTimeB(timeB_);
		
		Rodada rodada_ = new Rodada();
		rodada_.setId(rodadaId);
		this.setRodada(rodada_);
		
		Bolao bolao_ = new Bolao();
		bolao_.setId(bolaoId);
		this.setBolao(bolao_);
		
	}
	
    public PalpitesId() {
		
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Rodada getRodada() {
		return rodada;
	}

	public void setRodada(Rodada rodada) {
		this.rodada = rodada;
	}

	public Time getTimeA() {
		return timeA;
	}

	public void setTimeA(Time timeA) {
		this.timeA = timeA;
	}

	public Time getTimeB() {
		return timeB;
	}

	public void setTimeB(Time timeB) {
		this.timeB = timeB;
	}

	public Bolao getBolao() {
		return bolao;
	}

	public void setBolao(Bolao bolao) {
		this.bolao = bolao;
	}
	
	
	
	

}
