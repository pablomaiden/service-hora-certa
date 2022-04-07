package service.aplication.dto.bolao;

import service.aplication.model.bolao.Pontuacao;
import service.aplication.model.bolao.TopRodada;

public class RankingDTO {
	
	private Integer ranking;
	private String nome;
	private String urlPhoto;
	private String pontos;
	private String trofeus;
	private String idBolao;
	private String idCampeonato;
	private String email;
	private String rodada;
	
	public RankingDTO() {
		
	}
	
    public  RankingDTO(Pontuacao pontuacao, Integer ranking) {    	
    	this.setRanking(ranking);
    	this.setNome(pontuacao.getId().getUsuario().getNome());
    	this.setUrlPhoto(pontuacao.getId().getUsuario().getPhotoUrl());
    	this.setPontos(pontuacao.getPontos().toString());
    	this.setTrofeus(pontuacao.getTrofeus().toString());
    	this.setIdBolao(pontuacao.getId().getBolao().getId().toString());
    	this.setIdCampeonato(pontuacao.getId().getBolao().getCampeonato().getId().toString());
    	this.setEmail(pontuacao.getId().getUsuario().getEmail());
    	this.setRodada("");
	}
    
    public  RankingDTO(TopRodada pontuacao, Integer ranking) {    	
    	this.setRanking(ranking);
    	this.setNome(pontuacao.getId().getUsuario().getNome());
    	this.setUrlPhoto(pontuacao.getId().getUsuario().getPhotoUrl());
    	this.setPontos(pontuacao.getPontos().toString());
    	this.setTrofeus(pontuacao.getTrofeus().toString());
    	this.setIdBolao(pontuacao.getId().getBolao().getId().toString());
    	this.setIdCampeonato(pontuacao.getId().getBolao().getCampeonato().getId().toString());
    	this.setEmail(pontuacao.getId().getUsuario().getEmail());
    	this.setRodada(pontuacao.getId().getRodada().getRodadaDescricao());
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUrlPhoto() {
		return urlPhoto;
	}
	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}
	public String getPontos() {
		return pontos;
	}
	public void setPontos(String pontos) {
		this.pontos = pontos;
	}
	public String getTrofeus() {
		return trofeus;
	}
	public void setTrofeus(String trofeus) {
		this.trofeus = trofeus;
	}
	public Integer getRanking() {
		return ranking;
	}
	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}
	public String getIdBolao() {
		return idBolao;
	}
	public void setIdBolao(String idBolao) {
		this.idBolao = idBolao;
	}
	public String getIdCampeonato() {
		return idCampeonato;
	}
	public void setIdCampeonato(String idCampeonato) {
		this.idCampeonato = idCampeonato;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRodada() {
		return rodada;
	}

	public void setRodada(String rodada) {
		this.rodada = rodada;
	}
	
	
	
	
	
	
	

}
