package service.aplication.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tabela_nutricional", schema = "public")
public class TabelaNutricional implements java.io.Serializable {
	
	private static final long serialVersionUID = -7913754179011541666L;

	@Id
	private Long id;

	@Column(name = "descricao_alimento")
	@JsonProperty("descricaoAlimento")
	private String descricaoAlimento;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "fk_base_dados")	
	private BaseDados baseDados;
		
	@ManyToOne
	@JoinColumn(name = "fk_categoria_alimentos")	
	private CategoriaAlimentos categoriaAlimentos;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tabelaNutricional")
	private List<TabelaMedidas> lstTabelaMedidas;	

	@Column(name = "umidade")
	private Double umidade;

	@Column(name = "kcal")
	private Double kcal;

	@Column(name = "kj")
	private Double kj;

	@Column(name = "proteina")
	private Double proteina;

	@Column(name = "lipideos")
	private Double lipideos;

	@Column(name = "colesterol")
	private Double colesterol;

	@Column(name = "carboidratos")
	private Double carboidratos;

	@Column(name = "fibra_alimentar")
	private Double fibraAlimentar;

	@Column(name = "cinzas")
	private Double cinzas;

	@Column(name = "calcio")
	private Double calcio;

	@Column(name = "magnesio")
	private Double magnesio;

	@Column(name = "manganes")
	private Double manganes;

	@Column(name = "fosforo")
	private Double fosforo;

	@Column(name = "ferro")
	private Double ferro;

	@Column(name = "sodio")
	private Double sodio;

	@Column(name = "potassio")
	private Double potassio;

	@Column(name = "cobre")
	private Double cobre;

	@Column(name = "zinco")
	private Double zinco;

	@Column(name = "retinol")
	private Double retinol;

	@Column(name = "re")
	private Double re;

	@Column(name = "rae")
	private Double rae;

	@Column(name = "tiamina")
	private Double tiamina;

	@Column(name = "riboflavina")
	private Double riboflavina;

	@Column(name = "piridoxina")
	private Double piridoxina;

	@Column(name = "niacina")
	private Double niacina;

	@Column(name = "vitamina_c")
	private Double vitaminaC;
	
	@Column(name = "fenilalanina")
	private Double fenilalanina;

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

	public CategoriaAlimentos getCategoriaAlimentos() {
		return categoriaAlimentos;
	}

	public void setCategoriaAlimentos(CategoriaAlimentos categoriaAlimentos) {
		this.categoriaAlimentos = categoriaAlimentos;
	}

	public List<TabelaMedidas> getLstTabelaMedidas() {
		return lstTabelaMedidas;
	}

	public void setLstTabelaMedidas(List<TabelaMedidas> lstTabelaMedidas) {
		this.lstTabelaMedidas = lstTabelaMedidas;
	}

	public BaseDados getBaseDados() {
		return baseDados;
	}

	public void setBaseDados(BaseDados baseDados) {
		this.baseDados = baseDados;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabelaNutricional other = (TabelaNutricional) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	

}
