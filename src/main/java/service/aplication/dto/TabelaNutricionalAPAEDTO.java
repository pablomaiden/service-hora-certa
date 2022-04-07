package service.aplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TabelaNutricionalAPAEDTO extends AbstractDTO{
	
	private static final long serialVersionUID = -5211901104687598030L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("descricaoAlimento")
	private String descricaoAlimento;
	
	@JsonProperty("total")
	private Long total;	
	
	@JsonProperty("umidade")
	private Double umidade;	
	
	@JsonProperty("kcal")
	private Double kcal;	
	
	@JsonProperty("kj")
	private Double kj;	
	
	@JsonProperty("proteina")
	private Double proteina;	
	
	@JsonProperty("lipideos")
	private Double lipideos;	
	
	@JsonProperty("colesterol")
	private Double colesterol;	
	
	@JsonProperty("carboidratos")
	private Double carboidratos;	
	
	@JsonProperty("fibraAlimentar")
	private Double fibraAlimentar;	
	
	@JsonProperty("cinzas")
	private Double cinzas;	
	
	@JsonProperty("calcio")
	private Double calcio;	
	
	@JsonProperty("magnesio")
	private Double magnesio;
	
	@JsonProperty("manganes")
	private Double manganes;	
	
	@JsonProperty("fosforo")
	private Double fosforo;	
	
	@JsonProperty("ferro")
	private Double ferro;	
	
	@JsonProperty("sodio")
	private Double sodio;	
	
	@JsonProperty("potassio")
	private Double potassio;
	
	@JsonProperty("cobre")
	private Double cobre;	
	
	@JsonProperty("zinco")
	private Double zinco;	 
	
	@JsonProperty("retinol")
	private Double retinol; 
	
	@JsonProperty("re")
	private Double re;	 
	
	@JsonProperty("rae")
	private Double rae;	 
	
	@JsonProperty("tiamina")
	private Double tiamina;	 
	
	@JsonProperty("riboflavina")
	private Double riboflavina;	 
	
	@JsonProperty("piridoxina")
	private Double piridoxina;	
	
	@JsonProperty("niacina")
	private Double niacina;	 
	
	@JsonProperty("vitaminaC")
	private Double vitaminaC;
	
	@JsonProperty("fenilalanina")
	private Double fenilalanina;
		
	public Double getUmidade() {
		return umidade;
	}

	public void setUmidade(Double umidade) {
		this.umidade = umidade;
	}

	public Double getKcal() {
		return kcal;
	}

	public void setKcal(Double kcal) {
		this.kcal = kcal;
	}

	public Double getKj() {
		return kj;
	}

	public void setKj(Double kj) {
		this.kj = kj;
	}

	public Double getProteina() {
		return proteina;
	}

	public void setProteina(Double proteina) {
		this.proteina = proteina;
	}

	public Double getLipideos() {
		return lipideos;
	}

	public void setLipideos(Double lipideos) {
		this.lipideos = lipideos;
	}

	public Double getColesterol() {
		return colesterol;
	}

	public void setColesterol(Double colesterol) {
		this.colesterol = colesterol;
	}

	public Double getCarboidratos() {
		return carboidratos;
	}

	public void setCarboidratos(Double carboidratos) {
		this.carboidratos = carboidratos;
	}

	public Double getFibra_alimentar() {
		return fibraAlimentar;
	}

	public void setFibra_alimentar(Double fibra_alimentar) {
		this.fibraAlimentar = fibra_alimentar;
	}

	public Double getCinzas() {
		return cinzas;
	}

	public void setCinzas(Double cinzas) {
		this.cinzas = cinzas;
	}

	public Double getCalcio() {
		return calcio;
	}

	public void setCalcio(Double calcio) {
		this.calcio = calcio;
	}

	public Double getMagnesio() {
		return magnesio;
	}

	public void setMagnesio(Double magnesio) {
		this.magnesio = magnesio;
	}

	public Double getManganes() {
		return manganes;
	}

	public void setManganes(Double manganes) {
		this.manganes = manganes;
	}

	public Double getFosforo() {
		return fosforo;
	}

	public void setFosforo(Double fosforo) {
		this.fosforo = fosforo;
	}

	public Double getFerro() {
		return ferro;
	}

	public void setFerro(Double ferro) {
		this.ferro = ferro;
	}

	public Double getSodio() {
		return sodio;
	}

	public void setSodio(Double sodio) {
		this.sodio = sodio;
	}

	public Double getPotassio() {
		return potassio;
	}

	public void setPotassio(Double potassio) {
		this.potassio = potassio;
	}

	public Double getCobre() {
		return cobre;
	}

	public void setCobre(Double cobre) {
		this.cobre = cobre;
	}

	public Double getZinco() {
		return zinco;
	}

	public void setZinco(Double zinco) {
		this.zinco = zinco;
	}

	public Double getRetinol() {
		return retinol;
	}

	public void setRetinol(Double retinol) {
		this.retinol = retinol;
	}

	public Double getRe() {
		return re;
	}

	public void setRe(Double re) {
		this.re = re;
	}

	public Double getRae() {
		return rae;
	}

	public void setRae(Double rae) {
		this.rae = rae;
	}

	public Double getTiamina() {
		return tiamina;
	}

	public void setTiamina(Double tiamina) {
		this.tiamina = tiamina;
	}

	public Double getRiboflavina() {
		return riboflavina;
	}

	public void setRiboflavina(Double riboflavina) {
		this.riboflavina = riboflavina;
	}

	public Double getPiridoxina() {
		return piridoxina;
	}

	public void setPiridoxina(Double piridoxina) {
		this.piridoxina = piridoxina;
	}

	public Double getNiacina() {
		return niacina;
	}

	public void setNiacina(Double niacina) {
		this.niacina = niacina;
	}

	public Double getVitamina_c() {
		return vitaminaC;
	}

	public void setVitamina_c(Double vitamina_c) {
		this.vitaminaC = vitamina_c;
	}

	public Double getFenilalanina() {
		return fenilalanina;
	}

	public void setFenilalanina(Double fenilalanina) {
		this.fenilalanina = fenilalanina;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricaoAlimento() {
		return descricaoAlimento;
	}
	
	public void setDescricaoAlimento(String descricaoAlimento) {
		this.descricaoAlimento = descricaoAlimento;
	}
	
	public Long getTotal() {
		return total;
	}
	
	public void setTotal(Long total) {
		this.total = total;
	}

}
