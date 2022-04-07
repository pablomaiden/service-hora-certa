package service.aplication.model.bolao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "palpites", schema = "bolao")
public class Palpites {
	
	@EmbeddedId
	PalpitesId id;
	
	@Column(name = "placar_time_a")
	private Integer placarTimeA;
	
	@Column(name = "placar_time_b")
	private Integer placarTimeB;
	
	@Column(name = "data_palpite")
	private Date dataPalpite;
	
	@Column(name = "trofeu_utilizado")
	private boolean trofeuUtilizado;
	
	public Palpites(Long usuarioId, int placarTimeA, int placarTimeB, Long timeAId, Long timeBId, Long rodadaId, Long bolaoId) {		
		this.setPlacarTimeA(placarTimeA);
		this.setPlacarTimeB(placarTimeB);
		this.setDataPalpite(new Date());
		
		PalpitesId id = new PalpitesId(usuarioId,timeAId,timeBId,rodadaId,bolaoId);
		this.setId(id);		
	}
	
    public Palpites() {
		
	}

	public PalpitesId getId() {
		return id;
	}

	public void setId(PalpitesId id) {
		this.id = id;
	}	

	public Integer getPlacarTimeA() {
		return placarTimeA;
	}

	public void setPlacarTimeA(Integer placarTimeA) {
		this.placarTimeA = placarTimeA;
	}

	public Integer getPlacarTimeB() {
		return placarTimeB;
	}

	public void setPlacarTimeB(Integer placarTimeB) {
		this.placarTimeB = placarTimeB;
	}

	public Date getDataPalpite() {
		return dataPalpite;
	}

	public void setDataPalpite(Date dataPalpite) {
		this.dataPalpite = dataPalpite;
	}

	public boolean isTrofeuUtilizado() {
		return trofeuUtilizado;
	}

	public void setTrofeuUtilizado(boolean trofeuUtilizado) {
		this.trofeuUtilizado = trofeuUtilizado;
	}
	
	
	

}
