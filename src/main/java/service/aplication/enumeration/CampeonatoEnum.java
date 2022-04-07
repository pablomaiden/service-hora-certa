package service.aplication.enumeration;

public enum CampeonatoEnum {
	
	CAMPEONATO_BRASILEIRO_A(1L),
	LIBERTADORES(3L),
	COPA_DO_BRASIL(2L),
	CHAMPIONS(4L),
	CAMPEONATO_BRASILEIRO_B(6L),
	CAMPEONATO_PAULISTA(5L);
	
	private CampeonatoEnum(Long id) { 
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
