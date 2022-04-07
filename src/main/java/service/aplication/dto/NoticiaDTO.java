package service.aplication.dto;

import java.time.LocalDateTime;

public class NoticiaDTO {	
	
	private Long id;	
	private String titulo;	
	private String resumo;	
	private String noticia;	
	private String autor;	
	private String imagePath;	
	private LocalDateTime dataNoticia;	
	private Boolean publicar;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getResumo() {
		return resumo;
	}
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	public String getNoticia() {
		return noticia;
	}
	public void setNoticia(String noticia) {
		this.noticia = noticia;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public LocalDateTime getDataNoticia() {
		return dataNoticia;
	}
	public void setDataNoticia(LocalDateTime dataNoticia) {
		this.dataNoticia = dataNoticia;
	}
	public Boolean getPublicar() {
		return publicar;
	}
	public void setPublicar(Boolean publicar) {
		this.publicar = publicar;
	}
	
	

}
