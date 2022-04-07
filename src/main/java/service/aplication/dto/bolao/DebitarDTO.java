package service.aplication.dto.bolao;

import java.io.Serializable;

public class DebitarDTO implements Serializable{		
	/**
	 * 
	 */
	private static final long serialVersionUID = 3625328839627752253L;
	private String email;
	private Long idBolao;
	private Long idRodada;
	private Long timeA;
	private Long timeB;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getIdBolao() {
		return idBolao;
	}
	public void setIdBolao(Long idBolao) {
		this.idBolao = idBolao;
	}
	public Long getIdRodada() {
		return idRodada;
	}
	public void setIdRodada(Long idRodada) {
		this.idRodada = idRodada;
	}
	public Long getTimeA() {
		return timeA;
	}
	public void setTimeA(Long timeA) {
		this.timeA = timeA;
	}
	public Long getTimeB() {
		return timeB;
	}
	public void setTimeB(Long timeB) {
		this.timeB = timeB;
	}	
}
