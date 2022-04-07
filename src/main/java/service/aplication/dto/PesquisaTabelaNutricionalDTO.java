package service.aplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PesquisaTabelaNutricionalDTO {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("alimento")
	private String alimento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlimento() {
		return alimento;
	}

	public void setAlimento(String alimento) {
		this.alimento = alimento;
	}
	

}
