package service.aplication.enumeration;

public enum PontuacaoBolaoEnum {
	
	PLACAR_EXATO(25),
	VENCEDOR_PARTIDA_GOLS_TIME_VENCEDOR(18),
	ACERTOU_EMPATE_NAO_PLACAR(15),
	VENCEDOR_PARTIDA_GOLS_TIME_PERDEDOR(12),
	APENAS_VENCEDOR_PARTIDA_SEM_ACERTAR_PLACAR(10);
		
	private Integer ponto;	
	
	private PontuacaoBolaoEnum(Integer ponto) { 
		this.ponto = ponto; 
	}

	public Integer getPonto() {
		return ponto;
	}

	public void setPonto(Integer ponto) {
		this.ponto = ponto;
	}	

}
