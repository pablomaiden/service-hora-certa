package service.aplication.dto.bolao;

import java.io.Serializable;

public class TokenDTO implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5487247188524577383L;
	private String email;
	private String token;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	

}
