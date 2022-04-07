package service.aplication.enumeration;

public enum StatusRodadaEnum {
	
	ENCERRADO(true),
	EM_ANDAMENTO(false);
		
	private boolean status;
	
	private StatusRodadaEnum(boolean status) { 
		this.status = status; 
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}	

}
