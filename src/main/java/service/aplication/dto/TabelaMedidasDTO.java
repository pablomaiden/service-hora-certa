package service.aplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TabelaMedidasDTO {
	
	@JsonProperty("medida")
	private String medida;
	
	@JsonProperty("gramas")
	private Integer gramas;
	
	@JsonProperty("tipo")
	private String tipo;

	
	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}

	public Integer getGramas() {
		return gramas;
	}

	public void setGramas(Integer gramas) {
		this.gramas = gramas;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}	

}
