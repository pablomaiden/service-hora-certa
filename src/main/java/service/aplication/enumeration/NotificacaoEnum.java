package service.aplication.enumeration;

public enum NotificacaoEnum {	
	NOVO_CONVITE(1L);			
	private Long id;
	
	private NotificacaoEnum(Long id) { 
		this.id = id; 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

}
