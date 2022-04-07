package service.aplication.model;

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
@Table(name="tag", schema="portalnoticias")
@SequenceGenerator(name = "sq_tb_tag", sequenceName = "portalnoticias.sq_tb_tag", allocationSize = 1)
public class TagNoticia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_tb_tag")
	@Column(name = "id")
	private Long id;
	
	@Column(name="tag")
	private String tag;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tagnoticia")
	private List<RelTagNoticia> relTagNoticia;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<RelTagNoticia> getRelTagNoticia() {
		return relTagNoticia;
	}

	public void setRelTagNoticia(List<RelTagNoticia> relTagNoticia) {
		this.relTagNoticia = relTagNoticia;
	}	

}
