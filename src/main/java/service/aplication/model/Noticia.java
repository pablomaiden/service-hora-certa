package service.aplication.model;

import java.time.LocalDateTime;
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
import javax.persistence.Transient;

@Entity
@Table(name="noticia", schema="portalnoticias")
@SequenceGenerator(name = "sq_tb_noticia", sequenceName = "portalnoticias.sq_tb_noticia", allocationSize = 1)
public class Noticia {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tb_noticia")
	@Column(name = "id")
	private Long id;
	
	@Column(name = "titulo")
	private String titulo;
	
	@Column(name = "resumo")
	private String resumo;
	
	@Column(name = "noticia")
	private String noticia;
	
	@Column(name = "autor")
	private String autor;
	
	@Column(name = "image_path")
	private String imagePath;
	
	@Column(name = "data_noticia")
	private LocalDateTime dataNoticia;
	
	@Column(name="publicar")
	private Boolean publicar;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "noticia")
	private List<RelTagNoticia> relTagNoticia;
	
	@Transient
	private String imageUrl;
	
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
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public List<RelTagNoticia> getRelTagNoticia() {
		return relTagNoticia;
	}
	public void setRelTagNoticia(List<RelTagNoticia> relTagNoticia) {
		this.relTagNoticia = relTagNoticia;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Noticia other = (Noticia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
