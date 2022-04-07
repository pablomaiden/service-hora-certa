package service.aplication.model.bolao;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import service.aplication.model.seguranca.Usuario;

@Embeddable
public class ContaCorrenteId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1271544735162694102L;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_fk", nullable = false)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bolao_fk", nullable = false)
	private Bolao bolao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rodada_fk", nullable = false)
	private Rodada rodada;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "time_a_fk", nullable = false)
	private Time timeA;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "time_b_fk", nullable = false)
	private Time timeB;
		
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Bolao getBolao() {
		return bolao;
	}

	public void setBolao(Bolao bolao) {
		this.bolao = bolao;
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

	
	

}
