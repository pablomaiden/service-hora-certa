package service.aplication.dto.bolao;

import java.io.Serializable;

public class AgendaJogosDTO implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7671963726906390058L;
	private String timeA;
	private String escudoTimeA;
	private String nomeAbreviadoTimeA;
		
	private String timeB;
	private String escudoTimeB;
	private String nomeAbreviadoTimeB;	
	private String horaJogo;
	private String campeonato;
	
	public AgendaJogosDTO(String timeA, String escudoTimeA, String timeB, String escudoTimeB, String horaJogo){
		
		this.setTimeA(timeA);
		this.setEscudoTimeA(escudoTimeA);
		
		this.setTimeB(timeB);		
		this.setEscudoTimeB(escudoTimeB);
		
		this.setHoraJogo(horaJogo);
	}
	
    public AgendaJogosDTO(){
		
	}

	public String getTimeA() {
		return timeA;
	}

	public void setTimeA(String timeA) {
		this.timeA = timeA;
	}

	public String getEscudoTimeA() {
		return escudoTimeA;
	}

	public void setEscudoTimeA(String escudoTimeA) {
		this.escudoTimeA = escudoTimeA;
	}

	public String getNomeAbreviadoTimeA() {
		return nomeAbreviadoTimeA;
	}

	public void setNomeAbreviadoTimeA(String nomeAbreviadoTimeA) {
		this.nomeAbreviadoTimeA = nomeAbreviadoTimeA;
	}

	public String getTimeB() {
		return timeB;
	}

	public void setTimeB(String timeB) {
		this.timeB = timeB;
	}

	public String getEscudoTimeB() {
		return escudoTimeB;
	}

	public void setEscudoTimeB(String escudoTimeB) {
		this.escudoTimeB = escudoTimeB;
	}

	public String getNomeAbreviadoTimeB() {
		return nomeAbreviadoTimeB;
	}

	public void setNomeAbreviadoTimeB(String nomeAbreviadoTimeB) {
		this.nomeAbreviadoTimeB = nomeAbreviadoTimeB;
	}

	public String getHoraJogo() {
		return horaJogo;
	}

	public void setHoraJogo(String horaJogo) {
		this.horaJogo = horaJogo;
	}

	public String getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(String campeonato) {
		this.campeonato = campeonato;
	}	
	
	

}
