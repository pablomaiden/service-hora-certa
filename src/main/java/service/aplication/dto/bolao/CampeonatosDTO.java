package service.aplication.dto.bolao;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class CampeonatosDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6092739892669429410L;

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("rodada")
	private String rodada;
	
	@JsonProperty("pathImagem")
	private String pathImage;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRodada() {
		return rodada;
	}

	public void setRodada(String rodada) {
		this.rodada = rodada;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

}
