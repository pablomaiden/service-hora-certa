package service.aplication.dto.bolao;

import java.io.Serializable;

import service.aplication.model.bolao.Jogos;
import service.aplication.util.Util;

public class JogosDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1467447855108033508L;

	private String timeAId;
	private String timeA;
	private String urlImageTimeA;
	private String palpiteTimeA;
	private String nomeAbreviadoTimeA;
	private String placarTimeA;

	private String timeBId;
	private String timeB;
	private String urlImageTimeB;
	private String palpiteTimeB;
	private String nomeAbreviadoTimeB;
	private String placarTimeB;

	private String horaJogo;
	private String dataJogo;
	private String rodada;
	private String rodadaId;
	private String bolaoId;

	private boolean ganhouTrofeu;
	private String pontuacao;
	private boolean desabilitarPalpite;
	private String statusJogo;
	private String publicidadeImagePath;
	private String melhoresMomentosUrl;
	private boolean utilizouTrofeu;

	public JogosDTO() {

	}

	public JogosDTO(Jogos jogo) {

		this.setTimeAId(jogo.getId().getTimeA().getId().toString());
		this.setTimeA(jogo.getId().getTimeA().getNome());
		this.setUrlImageTimeA(jogo.getId().getTimeA().getUrlImage());
		this.setNomeAbreviadoTimeA(jogo.getId().getTimeA().getNomeAbreviado());

		this.setTimeBId(jogo.getId().getTimeB().getId().toString());
		this.setTimeB(jogo.getId().getTimeB().getNome());
		this.setUrlImageTimeB(jogo.getId().getTimeB().getUrlImage());
		this.setNomeAbreviadoTimeB(jogo.getId().getTimeB().getNomeAbreviado());

		this.setDataJogo(Util.formatarData(jogo.getHoraJogo(), "dd/MM"));
		this.setHoraJogo(Util.formatarData(jogo.getHoraJogo(), "HH:mm"));
		this.setRodada(jogo.getId().getRodada().getRodadaDescricao());
		this.setRodadaId(jogo.getId().getRodada().getId().toString());

		this.setPalpiteTimeA("0");
		this.setPalpiteTimeB("0");
	}

	public String getTimeA() {
		return timeA;
	}

	public void setTimeA(String timeA) {
		this.timeA = timeA;
	}

	public String getUrlImageTimeA() {
		return urlImageTimeA;
	}

	public void setUrlImageTimeA(String urlImageTimeA) {
		this.urlImageTimeA = urlImageTimeA;
	}

	public String getPalpiteTimeA() {
		return palpiteTimeA;
	}

	public void setPalpiteTimeA(String palpiteTimeA) {
		this.palpiteTimeA = palpiteTimeA;
	}

	public String getTimeB() {
		return timeB;
	}

	public void setTimeB(String timeB) {
		this.timeB = timeB;
	}

	public String getUrlImageTimeB() {
		return urlImageTimeB;
	}

	public void setUrlImageTimeB(String urlImageTimeB) {
		this.urlImageTimeB = urlImageTimeB;
	}

	public String getPalpiteTimeB() {
		return palpiteTimeB;
	}

	public void setPalpiteTimeB(String palpiteTimeB) {
		this.palpiteTimeB = palpiteTimeB;
	}

	public String getHoraJogo() {
		return horaJogo;
	}

	public void setHoraJogo(String horaJogo) {
		this.horaJogo = horaJogo;
	}

	public String getDataJogo() {
		return dataJogo;
	}

	public void setDataJogo(String dataJogo) {
		this.dataJogo = dataJogo;
	}

	public String getNomeAbreviadoTimeA() {
		return nomeAbreviadoTimeA;
	}

	public void setNomeAbreviadoTimeA(String nomeAbreviadoTimeA) {
		this.nomeAbreviadoTimeA = nomeAbreviadoTimeA;
	}

	public String getNomeAbreviadoTimeB() {
		return nomeAbreviadoTimeB;
	}

	public void setNomeAbreviadoTimeB(String nomeAbreviadoTimeB) {
		this.nomeAbreviadoTimeB = nomeAbreviadoTimeB;
	}

	public String getRodada() {
		return rodada;
	}

	public void setRodada(String rodada) {
		this.rodada = rodada;
	}

	public String getTimeAId() {
		return timeAId;
	}

	public void setTimeAId(String timeAId) {
		this.timeAId = timeAId;
	}

	public String getTimeBId() {
		return timeBId;
	}

	public void setTimeBId(String timeBId) {
		this.timeBId = timeBId;
	}

	public String getRodadaId() {
		return rodadaId;
	}

	public void setRodadaId(String rodadaId) {
		this.rodadaId = rodadaId;
	}

	public String getBolaoId() {
		return bolaoId;
	}

	public void setBolaoId(String bolaoId) {
		this.bolaoId = bolaoId;
	}

	public String getPlacarTimeA() {
		return placarTimeA;
	}

	public void setPlacarTimeA(String placarTimeA) {
		this.placarTimeA = placarTimeA;
	}

	public String getPlacarTimeB() {
		return placarTimeB;
	}

	public void setPlacarTimeB(String placarTimeB) {
		this.placarTimeB = placarTimeB;
	}

	public boolean isGanhouTrofeu() {
		return ganhouTrofeu;
	}

	public void setGanhouTrofeu(boolean ganhouTrofeu) {
		this.ganhouTrofeu = ganhouTrofeu;
	}

	public String getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(String pontuacao) {
		this.pontuacao = pontuacao;
	}

	public boolean isDesabilitarPalpite() {
		return desabilitarPalpite;
	}

	public void setDesabilitarPalpite(boolean desabilitarPalpite) {
		this.desabilitarPalpite = desabilitarPalpite;
	}

	public String getStatusJogo() {
		return statusJogo;
	}

	public void setStatusJogo(String statusJogo) {
		this.statusJogo = statusJogo;
	}

	public String getPublicidadeImagePath() {
		return publicidadeImagePath;
	}

	public void setPublicidadeImagePath(String publicidadeImagePath) {
		this.publicidadeImagePath = publicidadeImagePath;
	}

	public String getMelhoresMomentosUrl() {
		return melhoresMomentosUrl;
	}

	public void setMelhoresMomentosUrl(String melhoresMomentosUrl) {
		this.melhoresMomentosUrl = melhoresMomentosUrl;
	}

	public boolean isUtilizouTrofeu() {
		return utilizouTrofeu;
	}

	public void setUtilizouTrofeu(boolean utilizouTrofeu) {
		this.utilizouTrofeu = utilizouTrofeu;
	}	

}
