package service.aplication.resource;

import org.springframework.http.HttpHeaders;

public class Abstract {
	
	public HttpHeaders httpHeaders(){
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "application/json");
		return responseHeaders;
	}	

}
