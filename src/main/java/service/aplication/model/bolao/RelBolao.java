package service.aplication.model.bolao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import service.aplication.model.seguranca.Usuario;

@Entity
@Table(name = "rel_bolao", schema = "bolao")
@SequenceGenerator(name = "sq_relperfil", sequenceName = "seguranca.sq_reperfil", allocationSize = 1)
public class RelBolao {
	
	@EmbeddedId
	RelBolaoId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bolao_fk", insertable=false, updatable=false)
	private Bolao bolao;
	
	@Column(name = "data_cadastro", nullable = false)
	private Date dataCadastro;
	
	public RelBolao() {		
				
	}
	
	public RelBolao(Usuario usuario, Bolao bolao) {		
		RelBolaoId id_ = new RelBolaoId();
		id_.setBolao(bolao);
		id_.setUsuario(usuario);		
		this.setId(id_);		
	}
	
	public RelBolaoId getId() {
		return id;
	}

	public void setId(RelBolaoId id) {
		this.id = id;
	}

	public Bolao getBolao() {
		return bolao;
	}

	public void setBolao(Bolao bolao) {
		this.bolao = bolao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}	

}
