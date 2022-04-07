package service.aplication.repository.filter;

public class TabelaNutricionalFilter {
	
	private Long id;
	private String descricaoAlimento;
	private Long categoriaAlimento;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricaoAlimento() {
		return descricaoAlimento;
	}
	
	public void setDescricaoAlimento(String descricaoAlimento) {
		this.descricaoAlimento = descricaoAlimento;
	}
	
	public Long getCategoriaAlimento() {
		return categoriaAlimento;
	}
	
	public void setCategoriaAlimento(Long categoriaAlimento) {
		this.categoriaAlimento = categoriaAlimento;
	}	
	

}
