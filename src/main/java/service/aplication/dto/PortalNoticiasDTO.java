package service.aplication.dto;

import javax.validation.constraints.Size;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PortalNoticiasDTO extends AbstractDTO {
	
	private static final long serialVersionUID = -5372165146956141090L;
		
	@JsonProperty("id")
	private Long id;
	
	@NotEmpty(message="Título é obrigatório")
	@Size(min=50, max=200, message="O tamanho do Título deve estar entre 10 e 100 caracteres")
	@JsonProperty("titulo")
	private String titulo;
	
	@NotEmpty(message="Resumo é obrigatório")
	@Size(min=100, max=300, message="O tamanho do Resumo deve estar entre 100 e 300 caracteres")
	@JsonProperty("resumo")
	private String resumo;
	
	@NotEmpty(message="Notícia é obrigatório")
	@Size(min=100,message="A Notícia deve ter pelo menos o minimo de 100 caracteres")
	@JsonProperty("noticia")
	private String noticia;
	
	@NotEmpty(message="Autor é obrigatório")
	@Size(min=5, max=60, message="O tamanho do Autor deve estar entre 10 e 60 caracteres")
	@JsonProperty("autor")
	private String autor;
	
	@JsonProperty("imagePath")
	private String imagePath;
	
	@JsonProperty("dataNoticia")
	private String dataNoticia;
		
	@JsonProperty("publicar")
	private boolean publicar;
	
	@JsonProperty("anexo")
	private String anexo;
	
	@JsonProperty("tags")
	private List<TagNoticiasDTO> tags;
	
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

	public String getDataNoticia() {
		return dataNoticia;
	}

	public void setDataNoticia(String dataNoticia) {
		this.dataNoticia = dataNoticia;
	}

	public boolean getPublicar() {
		return publicar;
	}

	public void setPublicar(boolean publicar) {
		this.publicar = publicar;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	public List<TagNoticiasDTO> getTags() {
		return tags;
	}

	public void setTags(List<TagNoticiasDTO> tags) {
		this.tags = tags;
	}
	
	

}
