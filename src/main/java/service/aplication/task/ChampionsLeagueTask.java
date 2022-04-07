package service.aplication.task;

import java.text.SimpleDateFormat;

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


@Configuration
@EnableScheduling
public class ChampionsLeagueTask {
	
	private static final Logger logger = LoggerFactory.getLogger(ChampionsLeagueTask.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private static final String TIME_ZONE = "America/Sao_Paulo";
	
	@Autowired
	PesquisaUsuarioRepository pesquisaUsuarioRepository;
	
	@Autowired
	BolaoService bolaoService;
	
	@Autowired
	Mailer mailer;
		
	@Scheduled(cron = "0 */25 * ? * *", zone=TIME_ZONE)	  
	public void executeGravarPlacarChampionsLeague() {	  
	bolaoService.pushNotificationEndGame(bolaoService.gravarPlacarChampionsLeague());
	logger.info("executeGravarPlacarChampionsLeague() Data {}",dateFormat);
	}	
	
	@Scheduled(cron = "0 */1 * ? * *", zone=TIME_ZONE)	  
	public void executeGravarPontuacao() { 
	//Executa a tarefa do  campeonato Brasileiro
	bolaoService.calculaPontuacao(CampeonatoEnum.CHAMPIONS,TipoCalculoEnum.CALCULO_GERAL);
	logger.info("executeGravarPontuacao() Data {}",dateFormat);
	}
	
	@Scheduled(cron = "0 */20 * ? * *", zone=TIME_ZONE)	  
	public void executeGravarPontuacaoTopRodada() { 
	//Executa a tarefa do  campeonato Brasileiro
	bolaoService.calculaPontuacao(CampeonatoEnum.CHAMPIONS,TipoCalculoEnum.CALCULO_TOP_RODADA);
	logger.info("executeGravarPontuacaoTopRodada() Data {}",dateFormat);
	}	
	
	@Scheduled(cron = "0 */1 * ? * *", zone=TIME_ZONE)	  
	public void pushNotification() {	  	  
	bolaoService.pushNotification(CampeonatoEnum.CHAMPIONS);
	logger.info("executeGravarPontuacao() Data {}",dateFormat);
	}

}
