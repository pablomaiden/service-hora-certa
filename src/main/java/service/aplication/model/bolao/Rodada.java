package service.aplication.model.bolao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "rodada", schema = "bolao")
@SequenceGenerator(name = "sq_rodada", sequenceName = "bolao.sq_rodada", allocationSize = 1)
public class Rodada {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_rodada")
	private Long id;	
	
	@Column(name = "rodada_descricao")
	private String rodadaDescricao;
	
	@Column(name = "data_rodada")
	private Date dataRodada;
	
	@Column(name = "numero_rodada")
	private int numeroRodada;
	
	@Column(name = "encerrada")
	private boolean rodadaEncerrada;
	
	@Column(name = "rodada_atual")
	private boolean rodadaAtual;
	
	@Column(name = "data_exec_batch_calculo_pontuacao")
	private Date dataExecBatchCalculoPontuacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "campeonato_fk", nullable = false)
	private Campeonato campeonato;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRodadaDescricao() {
		return rodadaDescricao;
	}

	public void setRodadaDescricao(String rodadaDescricao) {
		this.rodadaDescricao = rodadaDescricao;
	}

	public Date getDataRodada() {
		return dataRodada;
	}

	public void setDataRodada(Date dataRodada) {
		this.dataRodada = dataRodada;
	}

	public int getNumeroRodada() {
		return numeroRodada;
	}

	public void setNumeroRodada(int numeroRodada) {
		this.numeroRodada = numeroRodada;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public boolean isRodadaEncerrada() {
		return rodadaEncerrada;
	}

	public void setRodadaEncerrada(boolean rodadaEncerrada) {
		this.rodadaEncerrada = rodadaEncerrada;
	}

	public Date getDataExecBatchCalculoPontuacao() {
		return dataExecBatchCalculoPontuacao;
	}

	public void setDataExecBatchCalculoPontuacao(Date dataExecBatchCalculoPontuacao) {
		this.dataExecBatchCalculoPontuacao = dataExecBatchCalculoPontuacao;
	}

	public boolean isRodadaAtual() {
		return rodadaAtual;
	}

	public void setRodadaAtual(boolean rodadaAtual) {
		this.rodadaAtual = rodadaAtual;
	}
	
	
	
	
	

}
