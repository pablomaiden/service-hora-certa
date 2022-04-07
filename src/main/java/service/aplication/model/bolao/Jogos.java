package service.aplication.model.bolao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "jogos", schema = "bolao")
public class Jogos {
		
	@EmbeddedId
	JogosId id;
		
	@Column(name = "hora_jogo")
	private Date horaJogo;
	
	@Column(name = "placar_time_a")
	private int placarTimeA;
	
	@Column(name = "placar_time_b")
	private int placarTimeB;
	
	@Column(name = "jogo_encerrado")
	private boolean jogoEncerrado;
	
	@Column(name = "data_exec_batch_calculo_pontuacao_top_rodada")
	private Date dataExecBatchCalculoPontTopRodada;
	
	@Column(name = "melhores_momentos_cod")
	private String melhoresMomentos;
			
	public Date getHoraJogo() {
		return horaJogo;
	}

	public void setHoraJogo(Date horaJogo) {
		this.horaJogo = horaJogo;
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

	public JogosId getId() {
		return id;
	}

	public void setId(JogosId id) {
		this.id = id;
	}

	public boolean isJogoEncerrado() {
		return jogoEncerrado;
	}

	public void setJogoEncerrado(boolean jogoEncerrado) {
		this.jogoEncerrado = jogoEncerrado;
	}

	public Date getDataExecBatchCalculoPontTopRodada() {
		return dataExecBatchCalculoPontTopRodada;
	}

	public void setDataExecBatchCalculoPontTopRodada(Date dataExecBatchCalculoPontTopRodada) {
		this.dataExecBatchCalculoPontTopRodada = dataExecBatchCalculoPontTopRodada;
	}

	public String getMelhoresMomentos() {
		return melhoresMomentos;
	}

	public void setMelhoresMomentos(String melhoresMomentos) {
		this.melhoresMomentos = melhoresMomentos;
	}
	
	
	
	

}
