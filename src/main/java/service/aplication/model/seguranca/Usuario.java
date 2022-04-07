package service.aplication.model.seguranca;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "usuario", schema = "seguranca")
@SequenceGenerator(name = "sq_usuario", sequenceName = "seguranca.sq_usuario", allocationSize = 1)
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_usuario")
	private Long id;	
	
	@Column(name = "nome")
	private String nome;	
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "senha")
	private String senha;
	
	@Column(name = "id_token")
	private String idToken;
	
	@Column(name = "access_token")
	private String accessToken;
	
	@Column(name = "photo_url")
	private String photoUrl;
	
	@Column(name = "uid")
	private String uId;
	
	@Column(name = "token")
	private String token;
	
	@Column(name = "data_update_token")
	private Date dataUpdateToken;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	private List<RelPerfilUsuario> relPerfilUsuarios;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<RelPerfilUsuario> getRelPerfilUsuarios() {
		return relPerfilUsuarios;
	}

	public void setRelPerfilUsuarios(List<RelPerfilUsuario> relPerfilUsuarios) {
		this.relPerfilUsuarios = relPerfilUsuarios;
	}

	public String getIdToken() {
		return idToken;
	}

	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getDataUpdateToken() {
		return dataUpdateToken;
	}

	public void setDataUpdateToken(Date dataUpdateToken) {
		this.dataUpdateToken = dataUpdateToken;
	}
	
	
	

}
