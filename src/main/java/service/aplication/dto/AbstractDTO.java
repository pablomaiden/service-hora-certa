package service.aplication.dto;

import javax.persistence.Column;

public class AbstractDTO implements java.io.Serializable{
		
	private static final long serialVersionUID = -5310254372566144904L;
	
	@Column(name = "mensagemSucesso")
	private String mensagemSucesso;
	
	@Column(name = "mensagemErro")
	private String mensagemErro;
	
	public String getMensagemSucesso() {
		return mensagemSucesso;
	}
	
	public void setMensagemSucesso(String mensagemSucesso) {
		this.mensagemSucesso = mensagemSucesso;
	}
	
	public String getMensagemErro() {
		return mensagemErro;
	}
	
	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
	

}
