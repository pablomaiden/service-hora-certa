package service.aplication.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class UsuarioDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5967972067137532250L;
	
	@JsonProperty("id")
	private String id;

	@JsonProperty("nome")
	private String nome;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("senha")
	private String senha;
	
	@JsonProperty("idToken")
	private String idToken;
	
	@JsonProperty("accessToken")
	private String accessToken;
	
	@JsonProperty("photoUrl")
	private String photoUrl;
	
	@JsonProperty("uId")
	private String uId;
	
	@JsonProperty("token")
	private String token;
	
	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
	
	
	
	

	
	
	

}
