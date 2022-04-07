package service.aplication.dto.bolao;

import org.codehaus.jackson.annotate.JsonProperty;

import service.aplication.model.bolao.Bolao;

public class BoloesDTO implements Comparable<BoloesDTO>{
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("campeonato")
	private String campeonato;
	
	@JsonProperty("id_campeonato")
	private String idCampeonato;
	
	@JsonProperty("proprietario")
	private String proprietario;
	
	@JsonProperty("descricao")
	private String descricao;
	
	@JsonProperty("path_image")
	private String pathImage;
			
	@JsonProperty("publicoOrPrivado")
	private boolean publicoOrPrivado;
	
	@JsonProperty("membros")
	private int qtdMembros;	
	
	@JsonProperty("emailUsuarioLogado")
	private String emailUsuarioLogado;
	
	@JsonProperty("mePertence")
	private boolean mePertence;
	
	
	public BoloesDTO() {
		
	}
	
	public BoloesDTO(Bolao bolao, boolean mePertence, int qtdMembros) {		
		this.setPathImage(bolao.getCampeonato().getPathImage());
		this.setId(bolao.getId().toString());		
		this.setCampeonato(bolao.getCampeonato().getNome());
		this.setProprietario(bolao.getUsuario().getEmail());	
		this.setIdCampeonato(bolao.getCampeonato().getId().toString());
		this.setEmailUsuarioLogado("");	
		this.setMePertence(mePertence);
		this.setQtdMembros(qtdMembros);
	}

	public String getIdCampeonato() {
		return idCampeonato;
	}

	public void setIdCampeonato(String idCampeonato) {
		this.idCampeonato = idCampeonato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	public boolean getPublicoOrPrivado() {
		return publicoOrPrivado;
	}

	public void setPublicoOrPrivado(boolean publicoOrPrivado) {
		this.publicoOrPrivado = publicoOrPrivado;
	}	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getQtdMembros() {
		return qtdMembros;
	}

	public void setQtdMembros(int qtdMembros) {
		this.qtdMembros = qtdMembros;
	}

	public String getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(String campeonato) {
		this.campeonato = campeonato;
	}

	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	public String getEmailUsuarioLogado() {
		return emailUsuarioLogado;
	}

	public void setEmailUsuarioLogado(String emailUsuarioLogado) {
		this.emailUsuarioLogado = emailUsuarioLogado;
	}

	public boolean isMePertence() {
		return mePertence;
	}

	public void setMePertence(boolean mePertence) {
		this.mePertence = mePertence;
	}

	@Override
    public int compareTo(BoloesDTO dto) {
        return Integer.compare(this.qtdMembros,dto.getQtdMembros());
    }
	
	

}
