package service.aplication.model.bolao;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import service.aplication.model.seguranca.Usuario;

@Embeddable
public class ConvidadoId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2813681230321412756L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_fk", nullable = false)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bolao_fk", nullable = false)
	private Bolao bolao;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Bolao getBolao() {
		return bolao;
	}

	public void setBolao(Bolao bolao) {
		this.bolao = bolao;
	}
	
	

}
