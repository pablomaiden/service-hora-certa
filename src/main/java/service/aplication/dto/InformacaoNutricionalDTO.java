package service.aplication.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InformacaoNutricionalDTO {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("alimento")
	private String alimento;
	
	@JsonProperty("kcal")
	private BigDecimal kcal;
	
	@JsonProperty("carboidrato")
	private Double carboidrato; 
	
	@JsonProperty("proteina")
	private Double proteina; 
	
	@JsonProperty("gorduraTotal")
	private Double gorduraTotal; 
	
	@JsonProperty("gorduraSaturada")
	private Double gorduraSaturada; 
	
	@JsonProperty("fibraAlimentar")
	private Double fibraAlimentar; 
	
	@JsonProperty("sodio")
	private Double sodio;
	
	@JsonProperty("lstTabelaMedidasDTO")
	private List<TabelaMedidasDTO> lstTabelaMedidasDTO;
	
	//Valores Diários(*) % Valores Diários de referência com base em uma dieta de 2.000 kcal ou 8400 kJ. 
	//Seus valores diários podem ser maiores ou menores dependendo de suas necessidades energéticas.
	
	@JsonProperty("vdAlimento")
	private String vdAlimento;
	
	@JsonProperty("vdKcal")
	private String vdKcal;
	
	@JsonProperty("vdCarboidrato")
	private String vdCarboidrato; 
	
	@JsonProperty("vdProteina")
	private String vdProteina; 
	
	@JsonProperty("vdGorduraTotal")
	private String vdGorduraTotal; 
	
	@JsonProperty("vdGorduraSaturada")
	private String vdGorduraSaturada; 
	
	@JsonProperty("vdFibraAlimentar")
	private String vdFibraAlimentar; 
	
	@JsonProperty("vdSodio")
	private String vdSodio;
	

	public String getAlimento() {
		return alimento;
	}

	public void setAlimento(String alimento) {
		this.alimento = alimento;
	}

	public BigDecimal getKcal() {
		return kcal;
	}

	public void setKcal(BigDecimal kcal) {
		this.kcal = kcal;
	}

	public Double getCarboidrato() {
		return carboidrato;
	}

	public void setCarboidrato(Double carboidrato) {
		this.carboidrato = carboidrato;
	}

	public Double getProteina() {
		return proteina;
	}

	public void setProteina(Double proteina) {
		this.proteina = proteina;
	}

	public Double getGorduraTotal() {
		return gorduraTotal;
	}

	public void setGorduraTotal(Double gorduraTotal) {
		this.gorduraTotal = gorduraTotal;
	}

	public Double getGorduraSaturada() {
		return gorduraSaturada;
	}

	public void setGorduraSaturada(Double gorduraSaturada) {
		this.gorduraSaturada = gorduraSaturada;
	}

	public Double getFibraAlimentar() {
		return fibraAlimentar;
	}

	public void setFibraAlimentar(Double fibraAlimentar) {
		this.fibraAlimentar = fibraAlimentar;
	}

	public Double getSodio() {
		return sodio;
	}

	public void setSodio(Double sodio) {
		this.sodio = sodio;
	}

	public String getVdAlimento() {
		return vdAlimento;
	}

	public void setVdAlimento(String vdAlimento) {
		this.vdAlimento = vdAlimento;
	}

	public String getVdKcal() {
		return vdKcal;
	}

	public void setVdKcal(String vdKcal) {
		this.vdKcal = vdKcal;
	}

	public String getVdCarboidrato() {
		return vdCarboidrato;
	}

	public void setVdCarboidrato(String vdCarboidrato) {
		this.vdCarboidrato = vdCarboidrato;
	}

	public String getVdProteina() {
		return vdProteina;
	}

	public void setVdProteina(String vdProteina) {
		this.vdProteina = vdProteina;
	}

	public String getVdGorduraTotal() {
		return vdGorduraTotal;
	}

	public void setVdGorduraTotal(String vdGorduraTotal) {
		this.vdGorduraTotal = vdGorduraTotal;
	}

	public String getVdGorduraSaturada() {
		return vdGorduraSaturada;
	}

	public void setVdGorduraSaturada(String vdGorduraSaturada) {
		this.vdGorduraSaturada = vdGorduraSaturada;
	}

	public String getVdFibraAlimentar() {
		return vdFibraAlimentar;
	}

	public void setVdFibraAlimentar(String vdFibraAlimentar) {
		this.vdFibraAlimentar = vdFibraAlimentar;
	}

	public String getVdSodio() {
		return vdSodio;
	}

	public void setVdSodio(String vdSodio) {
		this.vdSodio = vdSodio;
	}

	public List<TabelaMedidasDTO> getLstTabelaMedidasDTO() {
		return lstTabelaMedidasDTO;
	}

	public void setLstTabelaMedidasDTO(List<TabelaMedidasDTO> lstTabelaMedidasDTO) {
		this.lstTabelaMedidasDTO = lstTabelaMedidasDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

}
