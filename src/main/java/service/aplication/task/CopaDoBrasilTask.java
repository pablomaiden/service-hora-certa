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
public class CopaDoBrasilTask {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CopaDoBrasilTask.class);
	private static final Logger logger = LoggerFactory.getLogger(CopaDoBrasilTask.class);
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
	public void executeGravarPontuacaoTopRodada() { 
	//Executa a tarefa do  campeonato Brasileiro
	bolaoService.calculaPontuacao(CampeonatoEnum.COPA_DO_BRASIL,TipoCalculoEnum.CALCULO_TOP_RODADA);
	logger.info("CopaDoBrasilTask>notificationInicioPartidaChampions() Data {}",dateFormat); 
	}		
	
	@Scheduled(cron = "0 */1 * ? * *", zone=TIME_ZONE)	 
	public void executeGravarPontuacao() { 
	//Executa a tarefa do  campeonato Brasileiro
	bolaoService.calculaPontuacao(CampeonatoEnum.COPA_DO_BRASIL,TipoCalculoEnum.CALCULO_GERAL);
	logger.info("CopaDoBrasilTask>notificationInicioPartidaChampions() Data {}",dateFormat); 
	}
	
	@Scheduled(cron = "0 */1 * ? * *", zone=TIME_ZONE)	  
	public void pushNotification() {	  	  
	bolaoService.pushNotification(CampeonatoEnum.COPA_DO_BRASIL);
	this.gravaLogInfo(String.format("%s: %s", "Segunda", Util.formataDateEmString(new Date(), DD_MM_YYYY_HH_MM_SS)));
	}
	
	private void gravaLogInfo(String mensagem) {
		LOGGER.info(mensagem);
	}

}
