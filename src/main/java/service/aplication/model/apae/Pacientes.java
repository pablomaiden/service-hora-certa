package service.aplication.model.apae;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import service.aplication.model.geral.PessoaFisica;

@Entity
@Table(name="pacientes", schema="dashboard_apae")
@SequenceGenerator(name = "sq_tb_pacientes", sequenceName = "dashboard_apae.sq_tb_pacientes", allocationSize = 1)
public class Pacientes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tb_pacientes")
	@Column(name = "id")
	private Long id;
		
	@Column(name = "numero_paciente")
	private String numeroPaciente;
	
	@Column(name = "resultado_exame")
	private LocalDate resultadoExame;
	
	@Column(name = "data_exclusao")
	private LocalDateTime dataExclusao;
		
	@Column(name = "medidas_formula")
	private String medidasFormula;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pessoa_fisica_fk", nullable = false)
	private PessoaFisica pessoaFisica;
		
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}	
	
	public String getNumeroPaciente() {
		return numeroPaciente;
	}

	public void setNumeroPaciente(String numeroPaciente) {
		this.numeroPaciente = numeroPaciente;
	}

	public LocalDate getResultadoExame() {
		return resultadoExame;
	}

	public void setResultadoExame(LocalDate resultadoExame) {
		this.resultadoExame = resultadoExame;
	}	

	public LocalDateTime getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(LocalDateTime dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}	

	public String getMedidasFormula() {
		return medidasFormula;
	}

	public void setMedidasFormula(String medidasFormula) {
		this.medidasFormula = medidasFormula;
	}
	
	

}
