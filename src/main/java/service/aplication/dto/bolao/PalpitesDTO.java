package service.aplication.dto.bolao;

import java.io.Serializable;

public class PalpitesDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8071335875198631766L;
	private Long timeAId;
	private Long timeBId;
	private Long rodadaId;
	private Long bolaoId;
	private String usuarioId;
	
	private int placarTimeA;
	private int placarTimeB;
	
	public Long getTimeAId() {
		return timeAId;
	}
	public void setTimeAId(Long timeAId) {
		this.timeAId = timeAId;
	}
	public Long getTimeBId() {
		return timeBId;
	}
	public void setTimeBId(Long timeBId) {
		this.timeBId = timeBId;
	}
	public Long getRodadaId() {
		return rodadaId;
	}
	public void setRodadaId(Long rodadaId) {
		this.rodadaId = rodadaId;
	}
	public String getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}
	public int getPlacarTimeA() {
		return placarTimeA;
	}
	public void setPlacarTimeA(int placarTimeA) {
		this.placarTimeA = placarTimeA;
	}
	public int getPlacarTimeB() {
		return placarTimeB;
	}
	public void setPlacarTimeB(int placarTimeB) {
		this.placarTimeB = placarTimeB;
	}
	public Long getBolaoId() {
		return bolaoId;
	}
	public void setBolaoId(Long bolaoId) {
		this.bolaoId = bolaoId;
	}
	
	
	
	
	
	
	
	

}
