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
import service.aplication.service.ScrapingService;
import service.aplication.util.Util;

@Configuration
@EnableScheduling
public class CampeonatoPaulistaTask {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CampeonatoPaulistaTask.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private static final String TIME_ZONE = "America/Sao_Paulo";
	private static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
	
	@Autowired
	PesquisaUsuarioRepository pesquisaUsuarioRepository;
	
	@Autowired
	private ScrapingService scrapingService;
	
	@Autowired
	BolaoService bolaoService;
	
	@Autowired
	Mailer mailer;	
	
	@Scheduled(cron = "0/30 * 19-23 * * TUE", zone = TIME_ZONE)
	public void taskPartidaTercaFeira() {
		inicializaAgendamento("taskPartidaTercaFeira()");
	}
	
	@Scheduled(cron = "0/30 * 19-23 * * WED", zone = TIME_ZONE)
	public void taskPartidaQuartaFeira() {
		inicializaAgendamento("taskPartidaQuartaFeira()");
	}
	
	@Scheduled(cron = "0/30 * 19-23 * * THU", zone = TIME_ZONE)
	public void taskPartidaQuintaFeira() {
		inicializaAgendamento("taskPartidaQuintaFeira()");
	}
	
	@Scheduled(cron = "0/30 * 10-22 * * SAT", zone = TIME_ZONE)
	public void taskPartidaSexta() {
		inicializaAgendamento("taskPartidaSexta()");
	}
	
	@Scheduled(cron = "0/30 * 10-22 * * SUN", zone = TIME_ZONE)
	public void taskPartidaSabado() {
		inicializaAgendamento("taskPartidaSabado()");
	}
	
	@Scheduled(cron = "0/30 * 10-22 * * MON", zone = TIME_ZONE)
	public void taskPartidaDomingo() {
		inicializaAgendamento("taskPartidaDomingo()");
	}
	
	private void inicializaAgendamento(String diaSemana) {
		this.gravaLogInfo(String.format("%s: %s", diaSemana, Util.formataDateEmString(new Date(), DD_MM_YYYY_HH_MM_SS)));		
		scrapingService.verificaPartidaPeriodo();
	}
	
	private void gravaLogInfo(String mensagem) {
		LOGGER.info(mensagem);
	}
	
	@Scheduled(cron = "0 */1 * ? * *", zone=TIME_ZONE)	  
	public void executeGravarPontuacaoTopRodada() { 
	//Executa a tarefa do  campeonato Brasileiro
	bolaoService.calculaPontuacao(CampeonatoEnum.CAMPEONATO_PAULISTA,TipoCalculoEnum.CALCULO_TOP_RODADA);
	LOGGER.info("CampeonatoPaulista>notificationInicioPartida() Data {}",dateFormat); 
	}		
	
	@Scheduled(cron = "0 */1 * ? * *", zone=TIME_ZONE)	 
	public void executeGravarPontuacao() { 
	//Executa a tarefa do  campeonato Brasileiro
	bolaoService.calculaPontuacao(CampeonatoEnum.CAMPEONATO_PAULISTA,TipoCalculoEnum.CALCULO_GERAL);
	LOGGER.info("CampeonatoPaulista>notificationInicioPartida() Data {}",dateFormat); 
	}
		
	@Scheduled(cron = "0 */1 * ? * *", zone=TIME_ZONE)	  
	public void pushNotification() {	  	  
	bolaoService.pushNotification(CampeonatoEnum.CAMPEONATO_PAULISTA);
	this.gravaLogInfo(String.format("%s: %s", "Segunda", Util.formataDateEmString(new Date(), DD_MM_YYYY_HH_MM_SS)));
	}

}
