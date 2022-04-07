package service.aplication.repository.filter;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class NoticiaFilter {
	
	private String noticia;
	private String titulo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dataInicio;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dataFim;
	
	public String getNoticia() {
		return noticia;
	}
	
	public void setNoticia(String noticia) {
		this.noticia = noticia;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}	

}
