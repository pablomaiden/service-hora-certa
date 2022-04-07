package service.aplication.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import service.aplication.enumeration.CampeonatoEnum;
import service.aplication.enumeration.TipoCalculoEnum;
import service.aplication.mail.Mailer;
import service.aplication.repository.PesquisaUsuarioRepository;
import service.aplication.service.BolaoService;
import service.aplication.util.Util;

@Configuration
@EnableScheduling
public class CampeonatoBrasileiroATask {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CampeonatoBrasileiroATask.class);
	private static final String TIME_ZONE = "America/Sao_Paulo";
	private static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
	
	@Autowired
	PesquisaUsuarioRepository pesquisaUsuarioRepository;
	
	@Autowired
	BolaoService bolaoService;
	
	@Autowired
	Mailer mailer;
	
	  @Scheduled(cron = "0 */20 * ? * *", zone=TIME_ZONE)	  
	  public void executeGravarPontuacaoTopRodada() { 
	  //Executa a tarefa do  campeonato Brasileiro
	  bolaoService.calculaPontuacao(CampeonatoEnum.CAMPEONATO_BRASILEIRO_A, TipoCalculoEnum.CALCULO_TOP_RODADA);
	  this.gravaLogInfo(String.format("%s: %s", "Segunda", Util.formataDateEmString(new Date(), DD_MM_YYYY_HH_MM_SS)));
	  }		
	
	  @Scheduled(cron = "0 */1 * ? * *", zone=TIME_ZONE)	 
	  public void executeGravarPontuacao() { 
	  //Executa a tarefa do  campeonato Brasileiro
	  bolaoService.calculaPontuacao(CampeonatoEnum.CAMPEONATO_BRASILEIRO_A,TipoCalculoEnum.CALCULO_GERAL);
	  this.gravaLogInfo(String.format("%s: %s", "Segunda", Util.formataDateEmString(new Date(), DD_MM_YYYY_HH_MM_SS)));
	  }
	  
	  @Scheduled(cron = "0 */20 * ? * *", zone=TIME_ZONE)	  
	  public void executeGravarPlacarCampeonatoBrasileiro() {	  
	  bolaoService.pushNotificationEndGame(bolaoService.gravarPlacarCampeonatoBrasileiro());	  	  
	  this.gravaLogInfo(String.format("%s: %s", "Segunda", Util.formataDateEmString(new Date(), DD_MM_YYYY_HH_MM_SS)));
	  }	  
	  
	  @Scheduled(cron = "0 */1 * ? * *", zone=TIME_ZONE)	  
	  public void pushNotification() {	  	  
	  bolaoService.pushNotification(CampeonatoEnum.CAMPEONATO_BRASILEIRO_A);
	  this.gravaLogInfo(String.format("%s: %s", "Segunda", Util.formataDateEmString(new Date(), DD_MM_YYYY_HH_MM_SS)));
	  }
	  
	  private void gravaLogInfo(String mensagem) {
			LOGGER.info(mensagem);
	  }
	  
	  	  
	  

}
