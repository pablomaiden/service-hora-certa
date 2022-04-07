package service.aplication.model.bolao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import service.aplication.model.seguranca.Usuario;

@Entity
@Table(name = "conta_corrente", schema = "bolao")
public class ContaCorrente {
	
	@EmbeddedId
	ContaCorrenteId id;
	
	@Column(name = "debito", nullable = false)
	private Integer debito;
	
	@Column(name = "data_debito")
	private Date horaJogo;
	
	public ContaCorrente() {
		
	}
	
    public ContaCorrente(Usuario usuario, Bolao bolao,Rodada rodada,Long timeA,Long timeB, Integer debito) {
    	ContaCorrenteId id = new ContaCorrenteId();
    	id.setBolao(bolao);
    	id.setUsuario(usuario);
    	id.setRodada(rodada);
    	id.setTimeA(new Time(timeA));
    	id.setTimeB(new Time(timeB));
    	this.setId(id);
    	this.setHoraJogo(new Date());
    	this.setDebito(debito);
	}	
    
    public ContaCorrente(Usuario usuario, Bolao bolao, Long idRodada, Long timeA,Long timeB) {
    	ContaCorrenteId id = new ContaCorrenteId();
    	
    	id.setBolao(bolao);
    	id.setUsuario(usuario);
    	
    	Rodada rodada = new Rodada();
    	rodada.setId(idRodada);    	
    	id.setRodada(rodada);
    	
    	id.setTimeA(new Time(timeA));
    	id.setTimeB(new Time(timeB));
    	
    	this.setId(id);
	}	

	public ContaCorrenteId getId() {
		return id;
	}

	public void setId(ContaCorrenteId id) {
		this.id = id;
	}

	public Integer getDebito() {
		return debito;
	}

	public void setDebito(Integer debito) {
		this.debito = debito;
	}

	public Date getHoraJogo() {
		return horaJogo;
	}

	public void setHoraJogo(Date horaJogo) {
		this.horaJogo = horaJogo;
	}
	
	

}
