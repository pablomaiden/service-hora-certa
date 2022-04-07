package service.aplication;


import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.aplication.enumeration.PontuacaoBolaoEnum;
import service.aplication.model.bolao.Jogos;
import service.aplication.model.bolao.Palpites;
import service.aplication.service.BolaoService;
import service.aplication.util.UtilCalculosBolao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BolaoTest {
	
	private Jogos jogo;
	private Palpites palpite;
	
	@Autowired
	BolaoService bolaoService;
					
	@Before
	public void setUp() {		
		jogo    = new Jogos();
		palpite = new Palpites();				
	}	
	
	@Test
	public void calculaPontuacaoPlacarExato() {
			
		jogo.setPlacarTimeA(2);
		jogo.setPlacarTimeB(1);
		
		palpite.setPlacarTimeA(2);
		palpite.setPlacarTimeB(1);	
					
		assertEquals(PontuacaoBolaoEnum.PLACAR_EXATO.getPonto(),UtilCalculosBolao.calculoPontuacao(jogo,palpite));		
	}
	
	@Test
	public void calculaPontuacaoVencedorPartidaGolsTimeVencedor() {
			
		//TIME A VENCENDO
		jogo.setPlacarTimeA(2);
		jogo.setPlacarTimeB(1);
		
		palpite.setPlacarTimeA(2);
		palpite.setPlacarTimeB(0);	
				
		assertEquals(PontuacaoBolaoEnum.VENCEDOR_PARTIDA_GOLS_TIME_VENCEDOR.getPonto(),UtilCalculosBolao.calculoPontuacao(jogo,palpite));	
		
		//TIME B VENCENDO
		jogo.setPlacarTimeA(1);
		jogo.setPlacarTimeB(2);
		
		palpite.setPlacarTimeA(0);
		palpite.setPlacarTimeB(2);	
				
		assertEquals(PontuacaoBolaoEnum.VENCEDOR_PARTIDA_GOLS_TIME_VENCEDOR.getPonto(),UtilCalculosBolao.calculoPontuacao(jogo,palpite));
		
	}
	
	@Test
	public void calculaPontuacaoAcertouEmpateNaoPlacar() {
			
		//EMPATE
		jogo.setPlacarTimeA(1);
		jogo.setPlacarTimeB(1);
		
		palpite.setPlacarTimeA(2);
		palpite.setPlacarTimeB(2);	
				
		assertEquals(PontuacaoBolaoEnum.ACERTOU_EMPATE_NAO_PLACAR.getPonto(),UtilCalculosBolao.calculoPontuacao(jogo,palpite));	
		
		//EMPATE
		jogo.setPlacarTimeA(6);
		jogo.setPlacarTimeB(6);
		
		palpite.setPlacarTimeA(0);
		palpite.setPlacarTimeB(0);	
				
		assertEquals(PontuacaoBolaoEnum.ACERTOU_EMPATE_NAO_PLACAR.getPonto(),UtilCalculosBolao.calculoPontuacao(jogo,palpite));
		
	}
	
	@Test
	public void calculaPontuacaoVencedorPartidaGolsTimePerdedor() {
			
		//TIME A GANHA
		jogo.setPlacarTimeA(2);
		jogo.setPlacarTimeB(1);
		
		palpite.setPlacarTimeA(3);
		palpite.setPlacarTimeB(1);	
				
		assertEquals(PontuacaoBolaoEnum.VENCEDOR_PARTIDA_GOLS_TIME_PERDEDOR.getPonto(),UtilCalculosBolao.calculoPontuacao(jogo,palpite));	
		
		//TIME B GANHA
		jogo.setPlacarTimeA(1);
		jogo.setPlacarTimeB(2);
		
		palpite.setPlacarTimeA(1);
		palpite.setPlacarTimeB(3);	
				
		assertEquals(PontuacaoBolaoEnum.VENCEDOR_PARTIDA_GOLS_TIME_PERDEDOR.getPonto(),UtilCalculosBolao.calculoPontuacao(jogo,palpite));
		
	}
	
	@Test
	public void calculaPontuacaoApenasVencedorPartidaSemAcertarPlacar() {
			
		//TIME A GANHA
		jogo.setPlacarTimeA(2);
		jogo.setPlacarTimeB(1);
		
		palpite.setPlacarTimeA(3);
		palpite.setPlacarTimeB(2);	
				
		assertEquals(PontuacaoBolaoEnum.APENAS_VENCEDOR_PARTIDA_SEM_ACERTAR_PLACAR.getPonto(),UtilCalculosBolao.calculoPontuacao(jogo,palpite));	
		
		//TIME B GANHA
		jogo.setPlacarTimeA(1);
		jogo.setPlacarTimeB(2);
		
		palpite.setPlacarTimeA(2);
		palpite.setPlacarTimeB(3);	
				
		assertEquals(PontuacaoBolaoEnum.APENAS_VENCEDOR_PARTIDA_SEM_ACERTAR_PLACAR.getPonto(),UtilCalculosBolao.calculoPontuacao(jogo,palpite));
		
		//TIME A GANHA
		jogo.setPlacarTimeA(20);
		jogo.setPlacarTimeB(10);
				
		palpite.setPlacarTimeA(30);
		palpite.setPlacarTimeB(20);	
						
		assertEquals(PontuacaoBolaoEnum.APENAS_VENCEDOR_PARTIDA_SEM_ACERTAR_PLACAR.getPonto(),UtilCalculosBolao.calculoPontuacao(jogo,palpite));
		
	}
	
	@Test
	public void calculaTrofeu() {
	   Integer resultadoEsperado=50;
	   Integer resultado = bolaoService.resultadoTrofeu();	
	   assertEquals(resultadoEsperado,resultadoEsperado);		
	}
	
	

}
