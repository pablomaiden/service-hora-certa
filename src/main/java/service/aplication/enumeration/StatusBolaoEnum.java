package service.aplication.enumeration;

public enum StatusBolaoEnum {
	
	USUARIO_CRIOU_BOLAO(true),
	USUARIO_PARTICIPA_DO_BOLAO(false),
	BOLAO_PRIVADO_CONVITE_ENVIADO(true),
	BOLAO_PRIVADO(true),
	BOLAO_PUBLICO(true);	
		
	private boolean status;
	
	private StatusBolaoEnum(boolean status) { 
		this.status = status; 
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}	
	

}
