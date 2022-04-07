package service.aplication.enumeration;

public enum TipoCalculoEnum {
	
	CALCULO_TOP_RODADA(1L),
	CALCULO_GERAL(2L);
		
	private TipoCalculoEnum(Long id) { 
		this.id = id; 
	}
	
	private Long id;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	

}
