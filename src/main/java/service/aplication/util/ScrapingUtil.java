package service.aplication.util;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import service.aplication.dto.bolao.PartidaGoogleDTO;
import service.aplication.enumeration.StatusPartidasEnum;


@Service
public class ScrapingUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ScrapingUtil.class);
	private static final String BASE_URL_GOOGLE="http://google.com.br/search?q=";
	private static final String COMPLEMENTO_GOOGLE="&hl=pt-BR";
	
	private static final String TEMPO_PARTIDA="span[class=liveresults-sports-immersive__game-minute]";
	private static final String NOME_TIME_CASA="div[class=imso_mh__first-tn-ed imso_mh__tnal-cont imso-tnol]";
	private static final String NOME_TIME_VISITANTE="div[class=imso_mh__second-tn-ed imso_mh__tnal-cont imso-tnol]";
		
	public String montaUrlGoogle(String nomeEquipeCasa, String nomeEquipeVisitante) {
		try {
			String equipeCasa      = nomeEquipeCasa.replace(" ", "+").replace("-", "+");
			String equipeVisitante = nomeEquipeVisitante.replace(" ", "+").replace("-", "+");
			return BASE_URL_GOOGLE + equipeCasa + "+x+" + equipeVisitante + COMPLEMENTO_GOOGLE;
		} catch (Exception e) {
			LOGGER.error("ERRO: {}", e.getMessage());
		}
		return null;
	}
	
	
	public PartidaGoogleDTO partidaGoogle(String url) {
		PartidaGoogleDTO dto = new PartidaGoogleDTO();		
		Document document = null;		
		try {
			document = Jsoup.connect(url).get();
			String title = document.title();
			LOGGER.info("Titulo da Página: [{}]",title);	
			
			//========================VERIFICANDO STATUS DA PARTIDA - NÃO INICIADA, INICIADA E EM ANDAMENTO============
			StatusPartidasEnum statusPartida = obterStatusPartida(document);
			LOGGER.info("Status Partida: {}",statusPartida.name());	
			dto.setStatusPartida(statusPartida);
			//========================FIM STATUS PARTIDA===============================================================
			
			if(statusPartida!= StatusPartidasEnum.PARTIDA_NAO_INICIADA) {
			   String tempoPartida = tempoPartida(document);
			   LOGGER.info(tempoPartida);	
			   dto.setTempoPartida(tempoPartida);
			  			   
			   String placarTimeCasa = obterPlacarTimeCasa(document);
			   LOGGER.info(placarTimeCasa);
			   dto.setGolsEquipeCasa(placarTimeCasa);
				
			   String placarTimeVisitante = obterPlacarTimeVisitante(document);
			   LOGGER.info(placarTimeVisitante);
			   dto.setGolsEquipeVisitante(placarTimeVisitante);			   
			}			
			
			String nomeEquipeCasa = obterNomeTime(document,NOME_TIME_CASA);
			LOGGER.info(nomeEquipeCasa);
			dto.setNomeEquipeCasa(nomeEquipeCasa);
			
			String nomeEquipeVisitante = obterNomeTime(document,NOME_TIME_VISITANTE);
			LOGGER.info(nomeEquipeVisitante);
			dto.setNomeEquipeVisitante(nomeEquipeVisitante);
			
			String urlLogoEquipeCasa = obterLogoTimeCasa(document);
			LOGGER.info(urlLogoEquipeCasa);
			dto.setUrlLogoEquipeCasa(urlLogoEquipeCasa);
			
			String urlLogoEquipeVisitante = obterLogoTimeVisitante(document);
			LOGGER.info(urlLogoEquipeVisitante);
			dto.setUrlLogoEquipeVisitante(urlLogoEquipeVisitante);
			
			LOGGER.info("Saída do Objeto DTO:{}",dto);
			
			
		} catch (IOException e) {
			LOGGER.error("Error ao tentar conectar no Google Jsoup -> {}",e.getMessage());
		}		
		return dto;		
	}
	
	private static String obterLogoTimeVisitante(Document document) {		
		Element element = document.selectFirst("div[class=imso_mh__second-tn-ed imso_mh__tnal-cont imso-tnol]");
		String logoEquipeVisitante = element.select("img[class=imso_btl__mh-logo]").attr("src");		
		return logoEquipeVisitante;
	}
	
	private static String obterLogoTimeCasa(Document document) {		
		Element element = document.selectFirst("div[class=imso_mh__first-tn-ed imso_mh__tnal-cont imso-tnol]");
		String logoEquipeCasa = element.select("img[class=imso_btl__mh-logo]").attr("src");	
		return logoEquipeCasa;
	}
	
	private String obterNomeTime(Document document, String nomeTime) {		
		Element element = document.selectFirst(nomeTime);
		String nomeTime_ = element.select("span").text();		
		return nomeTime_;		
	}
	
	private static String obterPlacarTimeVisitante(Document document) {		
		return document.selectFirst("div[class=imso_mh__r-tm-sc imso_mh__scr-it imso-light-font]").text();		
	}
	
	private static String obterPlacarTimeCasa(Document document) {		
		return document.selectFirst("div[class=imso_mh__l-tm-sc imso_mh__scr-it imso-light-font]").text();	
	}
	
	private static String tempoPartida(Document document) {
		boolean isTempoPartida = document.select(TEMPO_PARTIDA).isEmpty();
		String tempoPartida="00";
		
		if(!isTempoPartida) {
		   tempoPartida= document.select(TEMPO_PARTIDA).first().text();			
		}		
		return tempoPartida;		
	}
	
	private static StatusPartidasEnum obterStatusPartida(Document document) {		
		StatusPartidasEnum statusPartida = StatusPartidasEnum.PARTIDA_NAO_INICIADA;		
		boolean isPartidaIniciada;
		
		isPartidaIniciada=document.select("div[class=imso_mh__lv-m-stts-cont]").isEmpty();	
		if(!isPartidaIniciada) {
		   //tempoPartida = document.select("div[class=imso_mh__lv-m-stts-cont]").first().text();	
		   statusPartida=StatusPartidasEnum.PARTIDA_EM_ANDAMENTO;	   		   
		}		
		
		isPartidaIniciada = document.select("span[class=imso_mh__ft-mtch imso-medium-font imso_mh__ft-mtchc]").isEmpty();		
		if(!isPartidaIniciada) {
		   //tempoPartida = document.select("span[class=imso_mh__ft-mtch imso-medium-font imso_mh__ft-mtchc]").first().text();
		   statusPartida=StatusPartidasEnum.PARTIDA_ENCERRADA;
		}		
		return statusPartida;		
	}
	
	
	
	

}
