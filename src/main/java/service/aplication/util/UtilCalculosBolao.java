package service.aplication.util;

import service.aplication.enumeration.PontuacaoBolaoEnum;
import service.aplication.model.bolao.Jogos;
import service.aplication.model.bolao.Palpites;

public class UtilCalculosBolao {
	
	public static void main(String[] args) {
				
		Jogos jogo       = new Jogos();
		Palpites palpite = new Palpites();
		
		jogo.setPlacarTimeA(2);
		jogo.setPlacarTimeB(2);
		
		palpite.setPlacarTimeA(5);
		palpite.setPlacarTimeB(5);
				
		Integer total = calculoPontuacao(jogo,palpite);
		
		System.out.println("Acertou o empate 15 Pontos");
		System.out.println("Pontuação Obtida:"+total);
		
		//==============================
		
		Jogos jogo1       = new Jogos();
		Palpites palpite1 = new Palpites();
		
		jogo1.setPlacarTimeA(3);
		jogo1.setPlacarTimeB(1);
		
		palpite1.setPlacarTimeA(3);
		palpite1.setPlacarTimeB(1);
				
		Integer total1 = calculoPontuacao(jogo1,palpite1);
		System.out.println("===================================");
		System.out.println("Placar Exato 25 Pontos");
		System.out.println("Pontuação Obtida:"+total1);
		
		//==============================
		
		Jogos jogo2       = new Jogos();
		Palpites palpite2 = new Palpites();
			
		jogo2.setPlacarTimeA(3);
		jogo2.setPlacarTimeB(1);
				
		palpite2.setPlacarTimeA(3);
		palpite2.setPlacarTimeB(2);
						
		Integer total2 = calculoPontuacao(jogo2,palpite2);
		System.out.println("===================================");
		System.out.println("Vencedor da partida com acerto de gols do time vencedor 18 pontos");
		System.out.println("Pontuação Obtida:"+total2);
		
		//==============================
		
		Jogos jogo3       = new Jogos();
		Palpites palpite3 = new Palpites();
					
		jogo3.setPlacarTimeA(1);
		jogo3.setPlacarTimeB(2);
						
		palpite3.setPlacarTimeA(1);
		palpite3.setPlacarTimeB(3);
							
		Integer total3 = calculoPontuacao(jogo3,palpite3);
		System.out.println("===================================");
		System.out.println("Vencedor da partida e gols do time perdedor 12 pontos");
		System.out.println("Pontuação Obtida:"+total3);
		
		
		//==============================
		
		Jogos jogo4       = new Jogos();
		Palpites palpite4 = new Palpites();
							
		jogo4.setPlacarTimeA(6);
		jogo4.setPlacarTimeB(5);
							
		palpite4.setPlacarTimeA(7);
		palpite4.setPlacarTimeB(6);
									
		Integer total4 = calculoPontuacao(jogo4,palpite4);
		System.out.println("===================================");
		System.out.println("Apenas vencedor da partida 10 pontos");
		System.out.println("Pontuação Obtida:"+total4);
		
	}
		
	public static Integer calculoPontuacao(Jogos jogo, Palpites palpite) {		
					
		if(resultadoPlacarCheio(jogo, palpite)) {
		   return PontuacaoBolaoEnum.PLACAR_EXATO.getPonto();
		}
		
		if(resultadoVencedorPartidaGolsTimeVencedor(jogo,palpite)) {
		   return PontuacaoBolaoEnum.VENCEDOR_PARTIDA_GOLS_TIME_VENCEDOR.getPonto();			
		}
		
		if(resultadoEmpatePartidaSemAcertarPlacar(jogo,palpite)) {
			return PontuacaoBolaoEnum.ACERTOU_EMPATE_NAO_PLACAR.getPonto();
		}
		
		if(resultadoVencedorPartidaGolsTimePerdedor(jogo, palpite)) {
		    return PontuacaoBolaoEnum.VENCEDOR_PARTIDA_GOLS_TIME_PERDEDOR.getPonto();
		}
		
		if(resultadoApenasVencedorSemAcertarOsGols(jogo,palpite)) {
			return PontuacaoBolaoEnum.APENAS_VENCEDOR_PARTIDA_SEM_ACERTAR_PLACAR.getPonto();
		}		
		return 0;		
	}
	
	private static boolean resultadoPlacarCheio(Jogos jogo, Palpites palpite) {
		if(    palpite.getPlacarTimeA().equals(jogo.getPlacarTimeA()) 
				&& palpite.getPlacarTimeB().equals(jogo.getPlacarTimeB())) {
				return true;			
		}
		return false;		
	}
	
	private static boolean resultadoVencedorPartidaGolsTimeVencedor(Jogos jogo, Palpites palpite) {
		
		//Time A Venceu
		if(jogo.getPlacarTimeA() > jogo.getPlacarTimeB()) {
		   if(palpite.getPlacarTimeA() > palpite.getPlacarTimeB()) {
			   if(palpite.getPlacarTimeA().equals(jogo.getPlacarTimeA())) {
				  return true;			   
			   }			   
		   }
		   
		}
		
		//Time B Venceu
		if(jogo.getPlacarTimeB() > jogo.getPlacarTimeA()) {
			if(palpite.getPlacarTimeB() > palpite.getPlacarTimeA()) {
				if(palpite.getPlacarTimeB().equals(jogo.getPlacarTimeB())) {
				   return true;			   
				}				
			}								
		}		
		return false;		
	}
	
	private static boolean resultadoEmpatePartidaSemAcertarPlacar(Jogos jogo, Palpites palpite) {		
		//Houve empate
		if(jogo.getPlacarTimeA() == jogo.getPlacarTimeB()) {
			if(palpite.getPlacarTimeA().equals(palpite.getPlacarTimeB()) 
			   && (!palpite.getPlacarTimeA().equals(jogo.getPlacarTimeA()))
			   && (!palpite.getPlacarTimeB().equals(jogo.getPlacarTimeB()))) {
				return true;
			}			
		}
		return false;		
	}
	
	private static boolean resultadoApenasVencedorSemAcertarOsGols(Jogos jogo, Palpites palpite) {
		
		if(jogo.getPlacarTimeA() > jogo.getPlacarTimeB()) {
			if(palpite.getPlacarTimeA() > palpite.getPlacarTimeB()) {
				if(!palpite.getPlacarTimeA().equals(jogo.getPlacarTimeA()) && !palpite.getPlacarTimeB().equals(jogo.getPlacarTimeB())) {
					return true;
				}				
			}
		}
		
		if(jogo.getPlacarTimeB() > jogo.getPlacarTimeA()) {
			if(palpite.getPlacarTimeB() > palpite.getPlacarTimeA()) {
				if(!palpite.getPlacarTimeA().equals(jogo.getPlacarTimeA()) && !palpite.getPlacarTimeB().equals(jogo.getPlacarTimeB())) {
					return true;
				}				
			}
		}
		
		
		return false;
	}
	
	
	private static boolean resultadoVencedorPartidaGolsTimePerdedor(Jogos jogo, Palpites palpite) {		
		if((jogo.getPlacarTimeA() > jogo.getPlacarTimeB()) && (palpite.getPlacarTimeA() > palpite.getPlacarTimeB())) {
			if(!palpite.getPlacarTimeA().equals(jogo.getPlacarTimeA()) && palpite.getPlacarTimeB().equals(jogo.getPlacarTimeB())) {
				return true;				
			}
		}
		
		if((jogo.getPlacarTimeB() > jogo.getPlacarTimeA()) && (palpite.getPlacarTimeB() > palpite.getPlacarTimeA())) {
			if(!palpite.getPlacarTimeB().equals(jogo.getPlacarTimeB()) && palpite.getPlacarTimeA().equals(jogo.getPlacarTimeA())) {
				return true;				
			}
		}	
		
		return false;
	}
	

}
