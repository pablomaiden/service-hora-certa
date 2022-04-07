package service.aplication.dto;

import java.time.LocalDate;

import org.codehaus.jackson.annotate.JsonProperty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class PacientesDTO {
		
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("foto")
	private String foto;
		
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dataNascimento;
	
	@JsonProperty("numeroPaciente")
	private String numeroPaciente;
		
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)	
	private LocalDate resultadoExame;
		
	@JsonProperty("cpf")
	private String cpf;
		
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
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
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
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
