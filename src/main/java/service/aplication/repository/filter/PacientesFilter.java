package service.aplication.repository.filter;

import java.util.Date;

public class PacientesFilter {
	
	private Long id;
	private String nome;	
	private Date nascimento;
	private String numeroPaciente;	
	private Date resultadoExame;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public String getNumeroPaciente() {
		return numeroPaciente;
	}
	public void setNumeroPaciente(String numeroPaciente) {
		this.numeroPaciente = numeroPaciente;
	}
	public Date getResultadoExame() {
		return resultadoExame;
	}
	public void setResultadoExame(Date resultadoExame) {
		this.resultadoExame = resultadoExame;
	}
}
