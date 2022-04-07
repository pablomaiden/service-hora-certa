package service.aplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="rel_tag", schema="portalnoticias")
@SequenceGenerator(name = "sq_tb_reltag", sequenceName = "portalnoticias.sq_tb_reltag", allocationSize = 1)
public class RelTagNoticia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tb_reltag")
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "noticia_fk", nullable = false)
	private Noticia noticia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tag_fk", nullable = false)
	private TagNoticia tagnoticia;

	public Noticia getNoticia() {
		return noticia;
	}

	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}

	public TagNoticia getTagnoticia() {
		return tagnoticia;
	}

	public void setTagnoticia(TagNoticia tagnoticia) {
		this.tagnoticia = tagnoticia;
	}	

}
