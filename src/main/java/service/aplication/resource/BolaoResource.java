package service.aplication.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.aplication.dto.UsuarioDTO;
import service.aplication.dto.bolao.AgendaJogosDTO;
import service.aplication.dto.bolao.BolaoStatusDTO;
import service.aplication.dto.bolao.BoloesDTO;
import service.aplication.dto.bolao.CampeonatosDTO;
import service.aplication.dto.bolao.ConvidadoDTO;
import service.aplication.dto.bolao.DebitarDTO;
import service.aplication.dto.bolao.JogosDTO;
import service.aplication.dto.bolao.NewsDTO;
import service.aplication.dto.bolao.NovaNotificacaoDTO;
import service.aplication.dto.bolao.PalpitesDTO;
import service.aplication.dto.bolao.PerfilDTO;
import service.aplication.dto.bolao.RankingDTO;
import service.aplication.dto.bolao.TokenDTO;
import service.aplication.dto.bolao.TrofeuDTO;
import service.aplication.dto.bolao.UsuarioPalpiteDTO;
import service.aplication.service.BolaoService;
import service.aplication.service.UsuarioService;

@RestController
@RequestMapping(value="/bolao")
public class BolaoResource extends Abstract{
	
	@Autowired
	BolaoService bolaoService;
	
	@Autowired
	UsuarioService usuarioService;
		
	@GetMapping(value= "/boloes")
    public ResponseEntity<List<BoloesDTO>> boloes() {		
		List<BoloesDTO> lista=null;
		try{			
			lista = bolaoService.listaBolao();			
			return new ResponseEntity<List<BoloesDTO>>(lista,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<List<BoloesDTO>>(lista,httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@GetMapping(value= "/listarcampeonatos")
    public ResponseEntity<List<CampeonatosDTO>> listarCampeonatos() {		
		try{					
			return new ResponseEntity<List<CampeonatosDTO>>(bolaoService.listaCampeonatos(),httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<List<CampeonatosDTO>>(null,httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@GetMapping(value= "/meusboloes/{email}/{token}")
    public ResponseEntity<List<BoloesDTO>> meusboloes(@PathVariable String email, @PathVariable String token) {		
		List<BoloesDTO> lista=null;
		try{			
			lista = bolaoService.meusBoloes(email,token);			
			return new ResponseEntity<List<BoloesDTO>>(lista,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<List<BoloesDTO>>(lista,httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@GetMapping(value= "/gerenciarmeusbolaos/{email}/{token}")
    public ResponseEntity<List<BoloesDTO>> gerenciarmeusbolaos(@PathVariable String email, @PathVariable String token) {		
		List<BoloesDTO> lista=null;
		try{			
			lista = bolaoService.gerenciarMeusBolaos(email,token);			
			return new ResponseEntity<List<BoloesDTO>>(lista,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<List<BoloesDTO>>(lista,httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@GetMapping(value= "/participarPublico/{email}/{idbolao}/{token}")
    public ResponseEntity<BoloesDTO> participarBolaoPublico(@PathVariable String email, @PathVariable Long idbolao, @PathVariable String token) {		
		try{			
			bolaoService.saveInvitation(email,idbolao,token);			
			return new ResponseEntity<BoloesDTO>(httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<BoloesDTO>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@GetMapping(value= "/byBolaoId/{id}")
    public ResponseEntity<BoloesDTO> byBolaoId(@PathVariable Long id) {	
		BoloesDTO dto;
		try{
			dto = new BoloesDTO();
			dto = bolaoService.byBolaoId(id);			
			return new ResponseEntity<BoloesDTO>(dto,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<BoloesDTO>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
		
	@PostMapping(value="/saveUserAuthentication")
    public ResponseEntity<UsuarioDTO> saveUserAuthentication(@RequestBody UsuarioDTO dto) {			
		try{			
			usuarioService.saveUserAuthentication(dto);			
			return new ResponseEntity<UsuarioDTO>(dto,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<UsuarioDTO>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@PostMapping(value="/criarBolao")
    public ResponseEntity<BoloesDTO> criarBolao(@RequestBody BoloesDTO dto) {			
		try{			
			bolaoService.criarBolao(dto);			
			return new ResponseEntity<BoloesDTO>(dto,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<BoloesDTO>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@PostMapping(value="/salvarConvidado")
    public ResponseEntity<ConvidadoDTO> salvarConvidado(@RequestBody ConvidadoDTO dto) {			
		try{			
			bolaoService.salvarConvidado(dto);		
			return new ResponseEntity<ConvidadoDTO>(dto,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<ConvidadoDTO>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@PostMapping(value="/convidadoAceito")
    public ResponseEntity<ConvidadoDTO> convidadoAceito(@RequestBody ConvidadoDTO dto) {			
		try{			
			bolaoService.convidadoAceito(dto);		
			return new ResponseEntity<ConvidadoDTO>(dto,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<ConvidadoDTO>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@PostMapping(value="/convidadoNegado")
    public ResponseEntity<ConvidadoDTO> convidadoNegado(@RequestBody ConvidadoDTO dto) {			
		try{			
			bolaoService.convidadoAceito(dto);		
			return new ResponseEntity<ConvidadoDTO>(dto,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<ConvidadoDTO>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@PostMapping(value="/palpitar")
    public ResponseEntity<PalpitesDTO> palpitar(@RequestBody PalpitesDTO dto) {			
		try{			
			bolaoService.palpitar(dto);		
			return new ResponseEntity<PalpitesDTO>(dto,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<PalpitesDTO>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@PostMapping(value="/novaNotificacao")
    public ResponseEntity<NovaNotificacaoDTO> novaNotificacao(@RequestBody NovaNotificacaoDTO dto) {			
		try{			
			bolaoService.novaNotificacao(dto);
			return new ResponseEntity<NovaNotificacaoDTO>(dto,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<NovaNotificacaoDTO>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@GetMapping(value= "/deixarBolao/{email}/{id}")
    public ResponseEntity<BoloesDTO> deixarBolao(@PathVariable String email, @PathVariable Long id) {	
		BoloesDTO dto;
		try{
			dto = new BoloesDTO();
			bolaoService.deixarBolao(email, id);			
			return new ResponseEntity<BoloesDTO>(dto,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<BoloesDTO>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@GetMapping(value= "/listarConvidados/{email}/{token}")
    public ResponseEntity<List<ConvidadoDTO>> listarConvidados(@PathVariable String email, @PathVariable String token) {	
		List<ConvidadoDTO> lista;
		try{
			lista = bolaoService.listarConvidados(email,token);						
			return new ResponseEntity<List<ConvidadoDTO>>(lista,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<List<ConvidadoDTO>>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@GetMapping(value= "/listarJogosPorCampeonato/{email}/{idBolao}")
    public ResponseEntity<List<JogosDTO>> listarJogosPorCampeonato(@PathVariable String email, @PathVariable Long idBolao) {	
		List<JogosDTO> lista;
		try{
			lista = bolaoService.listarJogosPorCampeonatoAndBolao(email,idBolao);						
			return new ResponseEntity<List<JogosDTO>>(lista,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<List<JogosDTO>>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }	
	
	@GetMapping(value= "/listarResultadosJogosPorCampeonato/{email}/{idCampeoanto}/{idBolao}")
    public ResponseEntity<List<JogosDTO>> listarResultadosJogosPorCampeonato(@PathVariable String email, @PathVariable Long idCampeoanto, @PathVariable Long idBolao) {	
		List<JogosDTO> lista;
		try{
			lista = bolaoService.listarResultadosJogosPorCampeonatoAndBolao(email,idCampeoanto,idBolao);						
			return new ResponseEntity<List<JogosDTO>>(lista,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<List<JogosDTO>>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@GetMapping(value= "/listarResultadosJogosPorCampeonatoAndUsuario/{email}/{idCampeoanto}/{idBolao}")
    public ResponseEntity<List<JogosDTO>> listarResultadosJogosPorCampeonatoAndUsuario(@PathVariable String email, @PathVariable Long idCampeoanto, @PathVariable Long idBolao) {	
		List<JogosDTO> lista;
		try{
			lista = bolaoService.listarResultadosJogosPorCampeonatoAndBolaoAndUsuario(email,idCampeoanto,idBolao);						
			return new ResponseEntity<List<JogosDTO>>(lista,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<List<JogosDTO>>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@GetMapping(value= "/listarTopRodadaRanking/{idBolao}/{idCampeonato}")
    public ResponseEntity<List<RankingDTO>> listarTopRodadaRanking(@PathVariable Long idBolao, @PathVariable Long idCampeonato) {	
		List<RankingDTO> lista;
		try{
			lista = bolaoService.listaTopRodada(idBolao,idCampeonato);						
			return new ResponseEntity<List<RankingDTO>>(lista,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<List<RankingDTO>>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@GetMapping(value= "/listarRanking/{idBolao}")
    public ResponseEntity<List<RankingDTO>> listarRanking(@PathVariable Long idBolao) {	
		List<RankingDTO> lista;
		try{
			lista = bolaoService.listaRankingUsuarios(idBolao);						
			return new ResponseEntity<List<RankingDTO>>(lista,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<List<RankingDTO>>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@GetMapping(value= "/listarUsuariosPorBolao/{idBolao}")
    public ResponseEntity<List<RankingDTO>> listarUsuariosPorBolao(@PathVariable Long idBolao) {	
		List<RankingDTO> lista;
		try{
			lista = bolaoService.listaUsuariosPorBolao(idBolao);				
			return new ResponseEntity<List<RankingDTO>>(lista,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<List<RankingDTO>>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@GetMapping(value= "/getPerfilUsuario/{email}")
    public ResponseEntity<PerfilDTO> getPerfilUsuario(@PathVariable String email) {	
		PerfilDTO dto = new PerfilDTO();
		try{
			dto = bolaoService.getUsuarioPerfil(email);				
			return new ResponseEntity<PerfilDTO>(dto,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<PerfilDTO>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }	
	
	@GetMapping(value= "/verificaStatusBolao/{email}/{idBolao}")
    public ResponseEntity<BolaoStatusDTO> verificaStatusBolao(@PathVariable String email, @PathVariable Long idBolao) {	
		BolaoStatusDTO dto = new BolaoStatusDTO();
		try{
			dto = bolaoService.verificaStatusBolao(email,idBolao);				
			return new ResponseEntity<BolaoStatusDTO>(dto,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<BolaoStatusDTO>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }	
	
	@GetMapping(value= "/news/{email}")
    public ResponseEntity<List<NewsDTO>> news(@PathVariable String email) {	
		List<NewsDTO> dtos;
		try{
			dtos = bolaoService.newsByUsuario(email);				
			return new ResponseEntity<List<NewsDTO>>(dtos,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<List<NewsDTO>>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }	
	
	@GetMapping(value= "/sinalizarLeituraNotificacao/{email}/{idnews}/{idrelnews}")
    public ResponseEntity<List<NewsDTO>> sinalizarLeituraNotificacao(@PathVariable String email,@PathVariable Long idnews,@PathVariable String idrelnews) {	
		List<NewsDTO> dtos = null;
		try{
			bolaoService.sinalizarLeituraMensagem(email, idnews, idrelnews);				
			return new ResponseEntity<List<NewsDTO>>(dtos,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<List<NewsDTO>>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }	
	
	@GetMapping(value= "/listaPalpitesUsuariosPorRodadaBolaoETimes/{idBolao}/{idRodada}/{idTimeA}/{idTimeB}")
    public ResponseEntity<List<UsuarioPalpiteDTO>> getListaPalpitesUsuariosPorRodadaBolaoETimes(@PathVariable Long idBolao, @PathVariable Long idRodada, @PathVariable Long idTimeA, @PathVariable Long idTimeB) {	
		List<UsuarioPalpiteDTO> dtos;
		try{
			dtos = bolaoService.getListaPalpitesUsuariosPorRodadaBolaoETimes(idBolao, idRodada, idTimeA, idTimeB);			
			return new ResponseEntity<List<UsuarioPalpiteDTO>>(dtos,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<List<UsuarioPalpiteDTO>>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@DeleteMapping(value = "/newsdismiss/{email}/{idnotification}/{id}")
    public ResponseEntity<NewsDTO> newsdismiss(@PathVariable String email, @PathVariable String idnotification, @PathVariable String id) {	
		NewsDTO dto = new NewsDTO();
		try{
			bolaoService.newsDismiss(email, idnotification,id);			
			return new ResponseEntity<NewsDTO>(dto,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<NewsDTO>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
		
	@PostMapping(value="/debitar/")
    public ResponseEntity<PalpitesDTO> debitar(@RequestBody DebitarDTO dto) {			
		try{			
			bolaoService.debitarTrofeu(dto);
			return new ResponseEntity<PalpitesDTO>(null,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<PalpitesDTO>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@PostMapping(value="/atualizarToken/")
    public ResponseEntity<TokenDTO> atualizarToken(@RequestBody TokenDTO dto) {			
		try{			
			bolaoService.atualizarToken(dto);
			return new ResponseEntity<TokenDTO>(null,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<TokenDTO>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@GetMapping(value="/getTotalTrofeu/{email}/{idBolao}")
    public ResponseEntity<TrofeuDTO> debitar(@PathVariable String email, @PathVariable Long idBolao) {			
		try{			
			TrofeuDTO dto = bolaoService.getTotalTrofeuPorUsuarioEBolao(email, idBolao);
			return new ResponseEntity<TrofeuDTO>(dto,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<TrofeuDTO>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@GetMapping(value="/agendaJogos")
    public ResponseEntity<List<AgendaJogosDTO>> agendaJogos() {			
		try{			
			List<AgendaJogosDTO> dto = bolaoService.agendaJogos();
			return new ResponseEntity<List<AgendaJogosDTO>>(dto,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<List<AgendaJogosDTO>>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }
	
	@GetMapping(value="/verificarTrofeuDebitado/{email}/{idBolao}/{idRodada}/{timeIdA}/{timeIdB}")
    public ResponseEntity<TrofeuDTO> verificarTrofeu(@PathVariable String email, 
    		                                         @PathVariable Long idBolao, 
    		                                         @PathVariable Long idRodada, 
    		                                         @PathVariable Long timeIdA,
    		                                         @PathVariable Long timeIdB) {			
		try{			
			TrofeuDTO dto = bolaoService.verificaTrofeuNoJogo(email,idBolao, idRodada, timeIdA,timeIdB);
			return new ResponseEntity<TrofeuDTO>(dto,httpHeaders(),HttpStatus.OK);		
		}catch (Exception e) {			
			return new ResponseEntity<TrofeuDTO>(httpHeaders(),HttpStatus.BAD_REQUEST);
		}		
    }



}
