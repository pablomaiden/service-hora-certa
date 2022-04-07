package service.aplication.dto.bolao;

import java.io.Serializable;

import service.aplication.model.bolao.Convidado;
import service.aplication.util.Util;

public class ConvidadoDTO implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1843060882200129851L;
	private String idBolao;
	private String emailUsuarioLogado;
	private String dataSolicitacao;
	private boolean aceito;
	
	private String nomeBolaoConvite;
	private String nomeUsuarioConvite;
	private String emailUsuarioConvite;
	private String photoUrl;
	
	public ConvidadoDTO() {
		
	}
	
    public ConvidadoDTO(Convidado convidado) {    	
    	this.setIdBolao(convidado.getId().getBolao().getId().toString());
    	this.setAceito(convidado.isAceito());	
		
    	this.setNomeBolaoConvite(convidado.getId().getBolao().getNome());
    	this.setNomeUsuarioConvite(convidado.getId().getUsuario().getNome());
    	this.setPhotoUrl(convidado.getId().getUsuario().getPhotoUrl());
    	this.setEmailUsuarioConvite(convidado.getId().getUsuario().getEmail());
    	this.setDataSolicitacao(Util.formatarData(convidado.getDataSolicitacao(),"dd/MM/YYYY"));
    	this.setEmailUsuarioLogado("");		
	}
	
	public String getIdBolao() {
		return idBolao;
	}
	public void setIdBolao(String idBolao) {
		this.idBolao = idBolao;
	}	
	
	public String getEmailUsuarioLogado() {
		return emailUsuarioLogado;
	}
	public void setEmailUsuarioLogado(String emailUsuarioLogado) {
		this.emailUsuarioLogado = emailUsuarioLogado;
	}
	public String getDataSolicitacao() {
		return dataSolicitacao;
	}
	public void setDataSolicitacao(String dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	public boolean isAceito() {
		return aceito;
	}
	public void setAceito(boolean aceito) {
		this.aceito = aceito;
	}
	public String getNomeBolaoConvite() {
		return nomeBolaoConvite;
	}
	public void setNomeBolaoConvite(String nomeBolaoConvite) {
		this.nomeBolaoConvite = nomeBolaoConvite;
	}
	public String getNomeUsuarioConvite() {
		return nomeUsuarioConvite;
	}
	public void setNomeUsuarioConvite(String nomeUsuarioConvite) {
		this.nomeUsuarioConvite = nomeUsuarioConvite;
	}
	public String getEmailUsuarioConvite() {
		return emailUsuarioConvite;
	}
	public void setEmailUsuarioConvite(String emailUsuarioConvite) {
		this.emailUsuarioConvite = emailUsuarioConvite;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	
	

}
