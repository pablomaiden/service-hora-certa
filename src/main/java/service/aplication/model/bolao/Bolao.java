package service.aplication.model.bolao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import service.aplication.model.seguranca.Usuario;

@Entity
@Table(name = "bolao", schema = "bolao")
@SequenceGenerator(name = "sq_bolao", sequenceName = "bolao.sq_bolao", allocationSize = 1)
public class Bolao {
	
	
	public Bolao(){
		
	}
	
	public Bolao(Long id, String nome, String descricao, boolean privacidade, Long idCampeonato, Long idUsuario) {	
		this.id=id;
		this.nome=nome;
		this.descricao=descricao;
		this.publicoOrPrivado=privacidade;
				
		//Setando o Campeonato
		Campeonato campeonato = new Campeonato();
		campeonato.setId(idCampeonato);
		this.setCampeonato(campeonato);
		
		//Setando o usuario
		Usuario usuario = new Usuario();
		usuario.setId(idUsuario);
		this.setUsuario(usuario);		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_bolao")
	private Long id;	
	
	@Column(name = "nome")
	private String nome;	
	
	@Column(name = "descricao")
	private String descricao;
		
	@Column(name = "privacidade")
	private boolean publicoOrPrivado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_fk", nullable = false)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "campeonato_fk", nullable = false)
	private Campeonato campeonato;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bolao")
	private List<RelBolao> relBolao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isPublicoOrPrivado() {
		return publicoOrPrivado;
	}

	public void setPublicoOrPrivado(boolean publicoOrPrivado) {
		this.publicoOrPrivado = publicoOrPrivado;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public List<RelBolao> getRelBolao() {
		return relBolao;
	}

	public void setRelBolao(List<RelBolao> relBolao) {
		this.relBolao = relBolao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	

}
