package service.aplication.task;

import java.text.SimpleDateFormat;
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
public class LibertadoresTask {
	
	  private static final Logger LOGGER = LoggerFactory.getLogger(LibertadoresTask.class);
	  private static final Logger logger = LoggerFactory.getLogger(CampeonatoBrasileiroATask.class);
	  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	  private static final String TIME_ZONE = "America/Sao_Paulo";
	  private static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
	
	  @Autowired
	  PesquisaUsuarioRepository pesquisaUsuarioRepository;
	
	  @Autowired
	  BolaoService bolaoService;
	
	  @Autowired
	  Mailer mailer;
	
	  @Scheduled(cron = "0 */20 * ? * *", zone=TIME_ZONE)	  
	  private void executeGravarPontuacaoTopRodada() { 
	  //Executa a tarefa do  campeonato Brasileiro
	  bolaoService.calculaPontuacao(CampeonatoEnum.LIBERTADORES,TipoCalculoEnum.CALCULO_TOP_RODADA);
	  logger.info("LibertadoresTask>executeGravarPontuacaoTopRodada() Data {}",dateFormat);
	  }		
	
	  @Scheduled(cron = "0 */1 * ? * *", zone=TIME_ZONE)	 
	  private void executeGravarPontuacao() { 
	  //Executa a tarefa do  campeonato Brasileiro
	  bolaoService.calculaPontuacao(CampeonatoEnum.LIBERTADORES,TipoCalculoEnum.CALCULO_GERAL);
	  logger.info("LibertadoresTask>executeGravarPontuacao() Data {}",dateFormat);
	  }
	  
	  @Scheduled(cron = "0 */1 * ? * *", zone=TIME_ZONE)	  
	  public void pushNotification() {	  	  
	  bolaoService.pushNotification(CampeonatoEnum.LIBERTADORES);
	  this.gravaLogInfo(String.format("%s: %s", "Segunda", Util.formataDateEmString(new Date(), DD_MM_YYYY_HH_MM_SS)));
	  }
		
	  private void gravaLogInfo(String mensagem) {
		LOGGER.info(mensagem);
	  }

}
