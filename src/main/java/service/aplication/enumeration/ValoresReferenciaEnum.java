package service.aplication.enumeration;

public enum ValoresReferenciaEnum {
		
	CARBOIDRATO(300D),
	PROTEINA(75D),
	GORDURAS_TOTAIS(55D),
	GORDURAS_SATURADAS(22D),
	GORDURAS_TRANS(00D),
	FIBRA_ALIMENTAR(25D),
	SODIO(2400D);
	
	private Double id;	
	
	private ValoresReferenciaEnum(Double id) { 
		this.id = id; 
	}
	
	public static Double getValorReferencia(Double id){
		return id;		
	}
	
	public Double getId() {
		return id;
	}
	public void setId(Double id) {
		this.id = id;
	}	
	
}
