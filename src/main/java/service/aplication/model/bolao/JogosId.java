package service.aplication.model.bolao;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class JogosId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5325230302403810213L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "time_a_fk", nullable = false)
	private Time timeA;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "time_b_fk", nullable = false)
	private Time timeB;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rodada_fk", nullable = false)
	private Rodada rodada;

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

	public Rodada getRodada() {
		return rodada;
	}

	public void setRodada(Rodada rodada) {
		this.rodada = rodada;
	}	
	
	

}
