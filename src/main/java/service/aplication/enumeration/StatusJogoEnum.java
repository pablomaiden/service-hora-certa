package service.aplication.enumeration;

public enum StatusJogoEnum {
	
	ENCERRADO(true),
	EM_ANDAMENTO(false),
	AGENDADO(true);
		
	private boolean status;
	
	private StatusJogoEnum(boolean status) { 
		this.status = status; 
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}	

}
