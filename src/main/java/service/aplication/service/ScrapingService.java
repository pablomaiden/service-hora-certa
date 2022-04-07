package service.aplication.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.aplication.dto.bolao.PartidaGoogleDTO;
import service.aplication.enumeration.CampeonatoEnum;
import service.aplication.enumeration.StatusPartidasEnum;
import service.aplication.model.bolao.Jogos;
import service.aplication.util.ScrapingUtil;

@Service
public class ScrapingService {
	
	@Autowired
	private ScrapingUtil scrapingUtil;
	
	@Autowired
	private BolaoService bolaoService;
		
	public void verificaPartidaPeriodo() {
		/*
		 * List<Jogos> jogos =
		 * bolaoService.listarJogosPorCampeonatoEPorData(CampeonatoEnum.
		 * CAMPEONATO_PAULISTA.getId()); if (jogos!=null && !jogos.isEmpty()){
		 * //List<Partida> partidas = partidaService.listarPartidasPeriodo();
		 * jogos.forEach(jogo -> { String urlPartida =
		 * scrapingUtil.montaUrlGoogle(jogo.getId().getTimeA().getNome(),jogo.getId().
		 * getTimeB().getNome()); PartidaGoogleDTO partidaGoogle =
		 * scrapingUtil.partidaGoogle(urlPartida);
		 * 
		 * if (partidaGoogle.getStatusPartida() !=
		 * StatusPartidasEnum.PARTIDA_NAO_INICIADA) {
		 * //partidaService.atualizaPartida(partida, partidaGoogle); } }); }
		 */
	}

}
