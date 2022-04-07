package service.aplication.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import service.aplication.dto.bolao.AgendaJogosDTO;
import service.aplication.dto.bolao.BolaoStatusDTO;
import service.aplication.dto.bolao.BoloesDTO;
import service.aplication.dto.bolao.CampeonatosDTO;
import service.aplication.dto.bolao.ConvidadoDTO;
import service.aplication.dto.bolao.DebitarDTO;
import service.aplication.dto.bolao.JogosDTO;
import service.aplication.dto.bolao.NewsDTO;
import service.aplication.dto.bolao.NotificationPlacarDTO;
import service.aplication.dto.bolao.NovaNotificacaoDTO;
import service.aplication.dto.bolao.PalpitesDTO;
import service.aplication.dto.bolao.PerfilDTO;
import service.aplication.dto.bolao.RankingDTO;
import service.aplication.dto.bolao.TokenDTO;
import service.aplication.dto.bolao.TrofeuDTO;
import service.aplication.dto.bolao.UsuarioPalpiteDTO;
import service.aplication.dto.bolao.api.ApiFootBallStatusDTO;
import service.aplication.enumeration.CampeonatoEnum;
import service.aplication.enumeration.NotificacaoEnum;
import service.aplication.enumeration.PontuacaoBolaoEnum;
import service.aplication.enumeration.StatusBolaoEnum;
import service.aplication.enumeration.StatusJogoEnum;
import service.aplication.enumeration.StatusRodadaEnum;
import service.aplication.enumeration.TipoCalculoEnum;
import service.aplication.model.bolao.Bolao;
import service.aplication.model.bolao.Campeonato;
import service.aplication.model.bolao.ContaCorrente;
import service.aplication.model.bolao.Convidado;
import service.aplication.model.bolao.Jogos;
import service.aplication.model.bolao.JogosId;
import service.aplication.model.bolao.News;
import service.aplication.model.bolao.Palpites;
import service.aplication.model.bolao.PalpitesId;
import service.aplication.model.bolao.Pontuacao;
import service.aplication.model.bolao.PontuacaoId;
import service.aplication.model.bolao.Publicidade;
//import service.aplication.model.bolao.Publicidade;
import service.aplication.model.bolao.RelBolao;
import service.aplication.model.bolao.RelBolaoId;
import service.aplication.model.bolao.RelNews;
import service.aplication.model.bolao.RelNewsId;
import service.aplication.model.bolao.Rodada;
import service.aplication.model.bolao.Time;
import service.aplication.model.bolao.TopRodada;
import service.aplication.model.seguranca.Usuario;
import service.aplication.repository.BolaoRepository;
import service.aplication.repository.UsuarioRepository;
import service.aplication.util.Util;
import service.aplication.util.UtilCalculosBolao;

@Service
public class BolaoService {
	
	@Autowired
	private BolaoRepository bolaoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/*
	 * public List<Jogos> listarJogosPorCampeonatoEPorData(Long campeonatoId){
	 * return bolaoRepository.listarJogosAcompanharTempoReal(campeonatoId); }
	 */
		
	public List<CampeonatosDTO> listaCampeonatos(){		
		List<Campeonato> listaCampeonatos = bolaoRepository.listaCampeonatos();
		CampeonatosDTO dto;		
		List<CampeonatosDTO> lista = new ArrayList<CampeonatosDTO>();		
		for(Campeonato temp: listaCampeonatos) {			
			dto = new CampeonatosDTO();		
			dto.setId(temp.getId().toString());
			BeanUtils.copyProperties(temp,dto);
			lista.add(dto);
		}		
		return lista;		
	}	
	
	@Transactional
	public void saveInvitation(String email, Long idBolao, String token) {		
		Optional<Usuario> usuario = usuarioRepository.findByEmailAndUId(email,token);	
		
		RelBolao relBolao = new RelBolao();		
		Bolao _bolao      = new Bolao();
		_bolao.setId(idBolao);
			
		relBolao.setId(new RelBolaoId());
		relBolao.getId().setBolao(_bolao);
		relBolao.getId().setUsuario(usuario.get());
		relBolao.setDataCadastro(new Date());
		
		bolaoRepository.saveInvitation(relBolao);	
		
	}
	
	public List<BoloesDTO> listaBolao(){		
		List<Bolao> listaBolao = bolaoRepository.listaBolao();
		BoloesDTO dto;		
		List<BoloesDTO> lista = new ArrayList<BoloesDTO>();		
		for(Bolao temp: listaBolao) {						
			dto = new BoloesDTO(temp,false, bolaoRepository.membrosPorBolao(temp.getId()).intValue());			
			BeanUtils.copyProperties(temp,dto);
			lista.add(dto);
		}
		Collections.sort(lista,Collections.reverseOrder());				
		return lista;		
	}
	
	public List<BoloesDTO> meusBoloes(String email, String token){		
		Usuario usuario = getUsuario(email,token);		
		if(usuario ==null) {
		   return new ArrayList<BoloesDTO>();
		}
		
		List<Bolao> listaBolao = bolaoRepository.meusBoloes(usuario.getId());
		BoloesDTO dto;		
		List<BoloesDTO> lista = new ArrayList<BoloesDTO>();		
		for(Bolao temp: listaBolao) {			
			dto = new BoloesDTO(temp, (usuario.equals(temp.getUsuario())? true : false), bolaoRepository.membrosPorBolao(temp.getId()).intValue());									
			BeanUtils.copyProperties(temp,dto);
			lista.add(dto);
		}		
		return lista;		
	}
	
	public List<BoloesDTO> gerenciarMeusBolaos(String email, String token){		
		Usuario usuario = getUsuario(email,token);		
		if(usuario ==null) {
		   return null;
		}
		
		List<Bolao> listaBolao = bolaoRepository.gerenciarMeusBolaos(usuario.getId());
		BoloesDTO dto;		
		List<BoloesDTO> lista = new ArrayList<BoloesDTO>();		
		for(Bolao temp: listaBolao) {			
			dto = new BoloesDTO(temp, (usuario.equals(temp.getUsuario())? true : false), bolaoRepository.membrosPorBolao(temp.getId()).intValue());									
			BeanUtils.copyProperties(temp,dto);
			lista.add(dto);
		}		
		return lista;		
	}
	
	public BoloesDTO byBolaoId(Long id){				
		Optional<Bolao> bolao = bolaoRepository.findById(id);		
		BoloesDTO dto = new BoloesDTO();;		
		//Setando a quantidade de membros
		dto.setQtdMembros(bolaoRepository.membrosPorBolao(id).intValue());
		dto.setPathImage(bolao.get().getCampeonato().getPathImage());
		BeanUtils.copyProperties(bolao.get(),dto);			
		return dto;		
	}
	
	@Transactional
	public void deixarBolao(String email, Long idBolao) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		Optional<Bolao>   bolao   = bolaoRepository.findById(idBolao);	
		Convidado convidado       = bolaoRepository.getConvidadoById(bolao.get().getId(), usuario.get().getId());
		
		RelBolao relBolao = new RelBolao();
    	RelBolaoId id     = new RelBolaoId();
    	
    	id.setUsuario(usuario.get());
    	id.setBolao(bolao.get());
    	
    	relBolao.setId(id);
    	    			
		bolaoRepository.deixarBolao(relBolao);	
		
		if(bolao.get().isPublicoOrPrivado()) {
		   bolaoRepository.removerConvidado(convidado);		   				
		}
		
	}
	
	@Transactional
	public void criarBolao(BoloesDTO dto) {
		
		Optional<Usuario> usuario = usuarioRepository.findByEmail(dto.getEmailUsuarioLogado());		
		Bolao bolao = new Bolao((dto.getId().equals("00")? null : Long.parseLong(dto.getId())), dto.getNome(),dto.getDescricao(), dto.getPublicoOrPrivado(), Long.parseLong(dto.getIdCampeonato()),usuario.get().getId());    	    			
		bolaoRepository.save(bolao);
						
		if(dto.getId().equals("00")) {			
			//====GRAVANDO A PUBLICIDADE===================
			Publicidade p = new Publicidade();
			p.setBolao(bolao);
			p.setAtual(true);
			p.setPathImage("https://i.pinimg.com/originals/d4/1f/49/d41f49001a22255fb8166ef836736c4a.gif");
			bolaoRepository.gravarPublicidade(p);
			//====FIM======================================
			
			RelBolao relBolao = new RelBolao(usuario.get(),bolao);		
			relBolao.setDataCadastro(new Date()); 			
			bolaoRepository.saveInvitation(relBolao);			
		}	
	}
	
	@Transactional
	public void salvarConvidado(ConvidadoDTO dto) {
		
		Optional<Usuario> usuario  = usuarioRepository.findByEmail(dto.getEmailUsuarioLogado());	
		Optional<Bolao>    bolao   = bolaoRepository.findById(Long.parseLong(dto.getIdBolao()));
		   		
		Convidado convidado = new Convidado(usuario.get(),bolao.get());		
		bolaoRepository.salvarConvidado(convidado);
	
	}
	
	@Transactional
	public void palpitar(PalpitesDTO dto) {	
		Jogos jogo=null;
		JogosId id=null;
		Optional<Usuario> usuario  = usuarioRepository.findByEmail(dto.getUsuarioId());	
				
		Palpites palpites = new Palpites(usuario.get().getId(),
				                         dto.getPlacarTimeA(), 
				                         dto.getPlacarTimeB(), 
				                         dto.getTimeAId(), 
				                         dto.getTimeBId(),
				                         dto.getRodadaId(), 
				                         dto.getBolaoId());
		
		id = new JogosId();
		id.setRodada(palpites.getId().getRodada());
		id.setTimeA(palpites.getId().getTimeA());
		id.setTimeB(palpites.getId().getTimeB());			
		jogo = bolaoRepository.getJogoById(id);
		
		//Verificando se o jogo já começou, se tiver começado não pode palpitar.
		if(jogo.getHoraJogo().after(Util.horaServidor())) {			
			Palpites palpite_ = bolaoRepository.findByIdPalpite(palpites.getId());				
			if(palpite_ == null) {	   		
			   bolaoRepository.palpitar(palpites);			
			}else {			
			   palpite_.setPlacarTimeA(dto.getPlacarTimeA());
			   palpite_.setPlacarTimeB(dto.getPlacarTimeB());				
			   bolaoRepository.palpitar(palpite_);										
			}			
		}		
	}
	
	@Transactional
	public void convidadoAceito(ConvidadoDTO dto) {
		
		Optional<Usuario> usuario  = usuarioRepository.findByEmail(dto.getEmailUsuarioConvite());	
		Optional<Bolao>    bolao   = bolaoRepository.findById(Long.parseLong(dto.getIdBolao()));
		
		RelBolao relBolao = new RelBolao(usuario.get(),bolao.get());		
		relBolao.setDataCadastro(new Date()); 		
			
		bolaoRepository.convidadoAceito(relBolao);		
		Convidado convidado  = new Convidado(usuario.get(),bolao.get());						
		bolaoRepository.atualizarSituacaoConvidadoAceito(convidado);
	}
	
	@Transactional
	public void convidadoNegado(ConvidadoDTO dto){		
		Optional<Usuario> usuario  = usuarioRepository.findByEmail(dto.getEmailUsuarioConvite());	
		Optional<Bolao>    bolao   = bolaoRepository.findById(Long.parseLong(dto.getIdBolao()));
		   		
		Convidado convidado = new Convidado(usuario.get(),bolao.get());		
		bolaoRepository.salvarConvidado(convidado);	
	}
	
	public List<ConvidadoDTO> listarConvidados(String email, String token){			
		Usuario usuario = getUsuario(email,token);		
		if(usuario ==null) {
		   return new ArrayList<ConvidadoDTO>();
		}
		
		List<Convidado> listarConvidados = bolaoRepository.listarConvidados(usuario.getId());
		
		ConvidadoDTO dto;
		List<ConvidadoDTO> lista = new ArrayList<ConvidadoDTO>();
		for(Convidado temp: listarConvidados) {
			dto = new ConvidadoDTO(temp);			
			lista.add(dto);			
		}				
		return lista;		
	}
	
    public List<RankingDTO> listaTopRodada(Long idBolao, Long idCampeonato){
		
		RankingDTO dto;
		List<RankingDTO> lista = new ArrayList<RankingDTO>();	
		List<TopRodada> pontuacoes=null;
				
		Rodada rodadaAnteriorRodadaEmAndamentoJogoEncerrado = bolaoRepository.getRodadaAnteriorJogoEncerrado(idCampeonato);		
		boolean existe = bolaoRepository.existeAlgumJogoEncerrado(rodadaAnteriorRodadaEmAndamentoJogoEncerrado.getId());
		
		if(existe){
		   pontuacoes = bolaoRepository.listaTopRodada(idBolao,rodadaAnteriorRodadaEmAndamentoJogoEncerrado.getId());			
		}else {
		   pontuacoes = bolaoRepository.listaTopRodada(idBolao,bolaoRepository.getRodadaAnterior(idCampeonato).getId());			
		}
		
		Integer ranking=1;
		for(TopRodada pontuacao :pontuacoes) {
			dto = new RankingDTO(pontuacao,ranking++);						
			lista.add(dto);
		}		
		return lista;
	}
    
    public List<RankingDTO> listaUsuariosPorBolao(Long idBolao){
    	
    	RankingDTO dto;
		List<RankingDTO> lista = new ArrayList<RankingDTO>();		
		List<RelBolao> lista_ = bolaoRepository.listarUsuariosPorBolao(idBolao);
		
		for(RelBolao temp : lista_) {
			dto = new RankingDTO();
			dto.setNome(temp.getId().getUsuario().getNome());
			dto.setEmail(temp.getId().getUsuario().getEmail());
			dto.setUrlPhoto(temp.getId().getUsuario().getPhotoUrl());
			dto.setIdCampeonato(temp.getBolao().getCampeonato().getId().toString());
			dto.setIdBolao(temp.getBolao().getId().toString());
			dto.setPontos("");
			dto.setRanking(0);
			dto.setTrofeus("");
			dto.setRodada("");
			lista.add(dto);
		}		
		
		return lista;    	
    }
	
	public List<RankingDTO> listaRankingUsuarios(Long idBolao){
		
		RankingDTO dto;
		List<RankingDTO> lista = new ArrayList<RankingDTO>();
		
		PontuacaoId id = new PontuacaoId();
		Bolao bolao = new Bolao();
		bolao.setId(idBolao);
		id.setBolao(bolao);
		
		List<Pontuacao> pontuacoes = bolaoRepository.listaRankingUsuarios(idBolao);
		
		Integer ranking=1;
		for(Pontuacao pontuacao :pontuacoes) {
			dto = new RankingDTO(pontuacao,ranking++);						
			lista.add(dto);
		}		
		return lista;
	}
	
	public List<JogosDTO> listarJogosPorCampeonatoAndBolao(String email, Long idBolao){		
		JogosDTO dto;
		
		Optional<Usuario> usuario  = usuarioRepository.findByEmail(email);	
		List<JogosDTO> lista       = new ArrayList<JogosDTO>();
		Bolao bolao                = bolaoRepository.getOne(idBolao);
		List<Jogos> listaJogos     = bolaoRepository.listarJogosPorCampeonato(bolao.getCampeonato().getId(),false,true);	
					
		for(Jogos temp:listaJogos) {
			dto = new JogosDTO(temp);			
			Palpites palpite_ = getPalpites(temp,bolao,usuario.get());
			if(palpite_!=null) {
				dto.setPalpiteTimeA(palpite_.getPlacarTimeA().toString());
				dto.setPalpiteTimeB(palpite_.getPlacarTimeB().toString());
				dto.setUtilizouTrofeu(palpite_.isTrofeuUtilizado());
			}else {
				dto.setPalpiteTimeA("-");
				dto.setPalpiteTimeB("-");	
				dto.setUtilizouTrofeu(false);
			}
			dto.setBolaoId(idBolao.toString());			
			dto.setPlacarTimeA("");
			dto.setPlacarTimeB("");
			dto.setPontuacao("0");
			dto.setPublicidadeImagePath("");			
			dto.setMelhoresMomentosUrl(temp.getMelhoresMomentos()==null?"":temp.getMelhoresMomentos());
			//Gerenciado o status do jogo, ENCERRADO, EM ANDAMENTO E AGENDADO
			gerenciarStatusJogo(temp,dto);
			//Fim da verificação de horário
			lista.add(dto);
		}				
		return lista;		
	}
	
	private void gerenciarStatusJogo(Jogos jogo, JogosDTO dto) {
		if(jogo.isJogoEncerrado()) {
		   dto.setStatusJogo(StatusJogoEnum.ENCERRADO.name());
		   dto.setDesabilitarPalpite(true);
		}else if(jogo.getHoraJogo().after(Util.horaServidor())) {
		   dto.setDesabilitarPalpite(false);
		   dto.setStatusJogo(StatusJogoEnum.AGENDADO.name());			
		}else {
		   dto.setStatusJogo(StatusJogoEnum.EM_ANDAMENTO.name());	
		   dto.setDesabilitarPalpite(true);			
		}		
	}
	
	public List<JogosDTO> listarResultadosJogosPorCampeonatoAndBolaoAndUsuario(String email,Long idCampeonato, Long idBolao){					
		Optional<Usuario> usuario  = usuarioRepository.findByEmail(email);	
		List<JogosDTO> lista       = new ArrayList<JogosDTO>();
		List<Jogos> listaJogos     = bolaoRepository.listarResultadoJogosPorCampeonato(idCampeonato,StatusJogoEnum.ENCERRADO.getStatus(),StatusRodadaEnum.ENCERRADO.getStatus());	
		Bolao bolao                = bolaoRepository.getOne(idBolao);
		Publicidade  publicidade   = bolaoRepository.getPublicidade(idBolao);
					
		for(Jogos temp:listaJogos) {			
			lista.add(calcula(temp,bolao,usuario.get(),idBolao,publicidade));
		}				
		return lista;		
	}
	
	public PerfilDTO getUsuarioPerfil(String email) {
		
		PerfilDTO dto = new PerfilDTO();
		
		Optional<Usuario> usuario  = usuarioRepository.findByEmail(email);		
		List<Pontuacao> lista      = bolaoRepository.listaPontuacaoByUsuario(usuario.get().getId());
		
		Integer totalPontos=0;
		Integer totalTrofeus=0;
		
		for(Pontuacao temp : lista) {			
			totalPontos  += temp.getPontos();
			totalTrofeus += temp.getTrofeus();			
		}
		
		dto.setTotalTrofeus(totalTrofeus.toString());
		dto.setTotalPontos(totalPontos.toString());
		
		dto.setTotalBolaos("0");
		dto.setTotalConvidados("0");
		
		return dto;
		
	}
	
    public BolaoStatusDTO verificaStatusBolao(String email, Long idBolao) {
		
	    BolaoStatusDTO dto = new BolaoStatusDTO();		
		Optional<Usuario> usuario  = usuarioRepository.findByEmail(email);		
		Bolao bolao                = bolaoRepository.getBolaoByIdAndUsuario(idBolao, usuario.get().getId());		
		RelBolao relBolao          = bolaoRepository.getBolaoByIdParticipante(idBolao, usuario.get().getId());		
		Bolao bolao_               = bolaoRepository.getBolaoById(idBolao);
		Convidado convidado        = bolaoRepository.getConvidado(idBolao,usuario.get().getId());
		
		if(bolao_!=null && bolao_.getCampeonato().getRodada().length()>3)
		   dto.setToken(" ");
		   dto.setStatus(bolao_.isPublicoOrPrivado());
		   dto.setRodada(bolao_.getCampeonato().getRodada().substring(0,3));
		
		if(bolao_!=null) {
		   dto.setToken(bolao_.getUsuario().getToken()==null? "" :bolao_.getUsuario().getToken());
		   dto.setBolao(bolao_.getNome());
		}		   
		
		if(usuario.isPresent())
		   dto.setNome(usuario.get().getNome());
					
		if(bolao!=null) {
		   dto.setStatusUsuario(StatusBolaoEnum.USUARIO_CRIOU_BOLAO.name());	   		   
		   return dto;		   
		}
		
		if(relBolao!=null) {
		   dto.setStatusUsuario(StatusBolaoEnum.USUARIO_PARTICIPA_DO_BOLAO.name());	
		   return dto;
		}	
		
		if(bolao_.isPublicoOrPrivado()) {
		   if(convidado!=null) {
			  dto.setStatusUsuario(StatusBolaoEnum.BOLAO_PRIVADO_CONVITE_ENVIADO.name());
			  return dto;
		   }else {
			  dto.setStatusUsuario(StatusBolaoEnum.BOLAO_PRIVADO.name());
			  return dto;			  
		   }		   
		}				
		dto.setStatusUsuario(StatusBolaoEnum.BOLAO_PUBLICO.name());			
		return dto;
		
	}

    @Transactional
    public void sinalizarLeituraMensagem(String email, Long idNews, String idRelNews) {    	
    	Optional<Usuario> usuario  = usuarioRepository.findByEmail(email);    	
    	RelNewsId    id = new RelNewsId();    	
    	News news       =  bolaoRepository.getNewsById(idNews);
    	
    	news.setId(idNews);
    	id.setUsuario(usuario.get());
    	id.setNews(news);    	
    	id.setId(idRelNews);   	
    	RelNews relNews_ = bolaoRepository.getRelNews(id);
    	relNews_.setLido(true);   
    	relNews_.setDataLido(Util.getDataAtual());
    	bolaoRepository.atualizarNotificacao(relNews_);    	
    }

    public List<NewsDTO> newsByUsuario(String email){    	
    	Optional<Usuario> usuario  = usuarioRepository.findByEmail(email);
    	List<RelNews> lista = bolaoRepository.getListaNewsByUsuario(usuario.get());
    	
    	List<NewsDTO> lista_ = new ArrayList<NewsDTO>();
    	NewsDTO dto;
    	
    	for(RelNews temp: lista) {
    		dto = new NewsDTO();
    		dto.setId(temp.getId().getNews().getId().toString());
    		dto.setTitulo(temp.getId().getNews().getInformacaoTitulo());
    		dto.setDescricao(temp.getId().getNews().getInformacaoDesc());
    		dto.setDataNotificacao(Util.formatarData(temp.getDataEnvio(),"dd/MM/YY HH:MM:SS"));
    		dto.setIdRelNews(temp.getId().getId());
    		dto.setPathImagem(temp.getId().getNews().getPathImagem());
    		dto.setNotificacaoLida(temp.isLido());
    		lista_.add(dto);
    	}    	
    	return lista_;    	
    }
    
    @Transactional
    public void newsDismiss(String email, String idNotification, String id_){    	
    	Optional<Usuario> usuario  = usuarioRepository.findByEmail(email);    	
    	RelNews relNews = new RelNews();
    	News       news = new News();    	
    	news.setId(Long.parseLong(idNotification));     	
    	RelNewsId id = new RelNewsId(usuario.get(),news,id_);
    	id.setId(id_);
    	relNews.setId(id);    	
    	bolaoRepository.newsDismiss(relNews);    	
    }
    
    @Transactional
    public void novaNotificacao(NovaNotificacaoDTO dto){    	
    	Optional<Usuario> usuario = usuarioRepository.findByEmail(dto.getEmail());    	
    	News news                 = bolaoRepository.getNewsById(NotificacaoEnum.NOVO_CONVITE.getId());    	
    	RelNews relNews           = new RelNews(usuario.get(),news,Util.formatarData(new Date(),"yyyy-MM-dd HH:mm:ss.S"));    	
    	bolaoRepository.salvarNotification(relNews);    	
    }
    
    public List<UsuarioPalpiteDTO> getListaPalpitesUsuariosPorRodadaBolaoETimes(Long idBolao, Long idRodada, Long idTimeA, Long idTimeB){    	
    	List<UsuarioPalpiteDTO> lista = new ArrayList<UsuarioPalpiteDTO>();
    	UsuarioPalpiteDTO dto;    	
    	List<Palpites> lista_ = bolaoRepository.listarPalpitesPorBolaoJogoERodada(idBolao,idRodada,idTimeA,idTimeB);    	
    	for(Palpites temp: lista_) {
    		dto = new UsuarioPalpiteDTO();
    		dto.setNome(temp.getId().getUsuario().getNome());
    		dto.setPhoto(temp.getId().getUsuario().getPhotoUrl());
    		dto.setPlacarTimeA(temp.getPlacarTimeA().toString());
    		dto.setPlacarTimeB(temp.getPlacarTimeB().toString());  
    		
    		dto.setUrlImageTimeA(temp.getId().getTimeA().getUrlImage());
    		dto.setUrlImageTimeB(temp.getId().getTimeB().getUrlImage());
    		
    		dto.setNomeTimeB(temp.getId().getTimeB().getNomeAbreviado());
    		dto.setNomeTimeA(temp.getId().getTimeA().getNomeAbreviado());
    		
    		lista.add(dto);
    	}    	
    	return lista;
    	
    }
	
	public List<JogosDTO> listarResultadosJogosPorCampeonatoAndBolao(String email,Long idCampeonato, Long idBolao){		
		List<Jogos> listaJogos = null;
		List<JogosDTO> lista   = new ArrayList<JogosDTO>();
		
		try {			
			Optional<Usuario> usuario  = usuarioRepository.findByEmail(email);		
			listaJogos                 = bolaoRepository.listarResultadoJogosPorCampeonato(idCampeonato,StatusJogoEnum.ENCERRADO.getStatus(),StatusRodadaEnum.EM_ANDAMENTO.getStatus());	
			Bolao bolao                = bolaoRepository.getOne(idBolao);		
			Publicidade  publicidade   = bolaoRepository.getPublicidade(idBolao);
				
			//Caso a rodada atual não tenha terminando nenhum jogo, será mostrado o jogo anterior
			if(listaJogos!=null && listaJogos.isEmpty()) {
			   listaJogos = bolaoRepository.listarResultadoJogosPorCampeonatoRodadaAnterior(idCampeonato);
			}		
						
			for(Jogos temp:listaJogos) {							
				lista.add(calcula(temp,bolao,usuario.get(),idBolao,publicidade));
			}				
			return lista;
			
		}catch(Exception ex) {
			ex.getStackTrace();			
		}
		return null;
	}
	
	private JogosDTO calcula(Jogos temp, Bolao bolao, Usuario usuario, Long idBolao, Publicidade  publicidade) {		
		JogosDTO dto      = new JogosDTO(temp);			
		Palpites palpite  = getPalpites(temp,bolao,usuario);
				
		if(palpite!=null){
		   dto.setPalpiteTimeA(palpite.getPlacarTimeA().toString());
		   dto.setPalpiteTimeB(palpite.getPlacarTimeB().toString());
		   //=========CALCULANDO A PONTUACAO DO RESULTADO==============================			   			  
		   dto.setPontuacao(String.valueOf(calcularTrofeuUtilizado(
					            usuario,
					            bolao,
					            temp,
					            temp.getId().getRodada(),
					            temp.getId().getTimeA(),
					            temp.getId().getTimeA(),
					            UtilCalculosBolao.calculoPontuacao(temp, palpite))));
			   
			//Sinalizando que foi utilizado ou não um trofeu
			dto.setUtilizouTrofeu(palpite.isTrofeuUtilizado());			   
			//=========FIM=============================================================			
		}else{
			dto.setPalpiteTimeA("-");
			dto.setPalpiteTimeB("-");
			dto.setUtilizouTrofeu(false);
			dto.setPontuacao("0");
		}
		
		//Setando o ID do Bolão
		dto.setBolaoId(idBolao.toString());
		
		//Setando o placar depois que o jogo termina
		dto.setPlacarTimeA(String.valueOf(temp.getPlacarTimeA()));
		dto.setPlacarTimeB(String.valueOf(temp.getPlacarTimeB()));
				
		//Gerenciado o status do jogo, ENCERRADO, EM ANDAMENTO E AGENDADO
		gerenciarStatusJogo(temp,dto);			
		dto.setPublicidadeImagePath(publicidade.getPathImage());		
		dto.setMelhoresMomentosUrl(temp.getMelhoresMomentos()==null?"":temp.getMelhoresMomentos());
		return dto;
	}
	
	@Transactional
	public void calculaPontuacao(CampeonatoEnum campeonatoEnum, TipoCalculoEnum tipoCalculoEnum) {		
		List<Jogos>   listaJogos   = null;
		Usuario usuario_           = null;
		Bolao bolao_               = null;
		List<Long>    idsUsuarios  = null;
		List<Long>    idsBoloes    = null;
				
		if(tipoCalculoEnum.getId().equals(TipoCalculoEnum.CALCULO_TOP_RODADA.getId())) {
			listaJogos  = bolaoRepository.listarResultadoJogosPorCampeonatoTopRodadaExecutorBatch(campeonatoEnum.getId(),StatusJogoEnum.ENCERRADO.getStatus(),StatusRodadaEnum.EM_ANDAMENTO.getStatus());
		}else {
			listaJogos  = bolaoRepository.listarResultadoJogosPorCampeonatoExecutorBatch(campeonatoEnum.getId(),StatusJogoEnum.ENCERRADO.getStatus(),StatusRodadaEnum.ENCERRADO.getStatus());						
		}
						
		if(!listaJogos.isEmpty()) {			
			idsUsuarios  = bolaoRepository.usuariosPorCampeonato_(campeonatoEnum.getId());	
						
			for(Long usuario : idsUsuarios) {
				//==========SETANDO O USUÁRIO===========
				usuario_ = new Usuario();
				usuario_.setId(usuario);
				//==========FIM=========================
				
				//==========PEGANDO OS BOLOES DO USUÁRIO===========
				idsBoloes=bolaoRepository.bolaosPorUsuarioIds(campeonatoEnum.getId(),usuario);
				//==========FIM====================================				
				for(Jogos jogo:listaJogos) {				
					for(Long bolao : idsBoloes) {	
						//===========SETANDO O BOLAO===========
						bolao_ = new Bolao();
						bolao_.setId(bolao);
						//===========FIM========================						
						Palpites palpite_ = getPalpites(jogo,bolao_,usuario_);
						if(palpite_!=null) {
							
							if(tipoCalculoEnum.getId().equals(TipoCalculoEnum.CALCULO_TOP_RODADA.getId())) {
							   //Calculando a pontuacao	quando o usuário tem um palpite gravado				   
								gravarCalcularTopRodada(usuario_,bolao_,jogo,UtilCalculosBolao.calculoPontuacao(jogo,palpite_));								
							}else {
								//Calculando a pontuacao	quando o usuário tem um palpite gravado				   
								gravarCalcularPontuacao(usuario_,bolao_,jogo,UtilCalculosBolao.calculoPontuacao(jogo,palpite_));
							}
						    	
						}else {
							if(tipoCalculoEnum.getId().equals(TipoCalculoEnum.CALCULO_TOP_RODADA.getId())) {
								//Calculando a pontuacao	quando o usuário não tem palpite gravado, dessa forma é colocado um zero
								gravarCalcularTopRodada(usuario_,bolao_,jogo,0);									
							}else {
								//Calculando a pontuacao	quando o usuário não tem palpite gravado, dessa forma é colocado um zero
								gravarCalcularPontuacao(usuario_,bolao_,jogo,0);									
							}
													
						}
					}							
				}			
			}
			
			if(tipoCalculoEnum.getId().equals(TipoCalculoEnum.CALCULO_TOP_RODADA.getId())) {
				//=====APÓS TODOS OS CALCULOS, ENTÃO É FECHADOS OS JOGOS
				for(Jogos jogo:listaJogos) {
					executarFechamentoBatch(jogo);				
				}
				//=====FIM==============================================				
			}else {
				if(!listaJogos.isEmpty() && listaJogos.get(0)!=null)
				   executarFechamentoBatch(listaJogos.get(0).getId().getRodada());				
			}
		}				
	}	
		
	public void executarFechamentoBatch(Rodada rodada) {		
		Rodada rodada_ = bolaoRepository.getByRodadaId(rodada.getId());
		rodada_.setDataExecBatchCalculoPontuacao(new Date());		
		bolaoRepository.executarFechamentoBatch(rodada_);		
	}
	
	public void executarFechamentoBatch(Jogos jogos) {		
		Jogos jogos_ = bolaoRepository.getJogoById(jogos.getId());
		jogos_.setDataExecBatchCalculoPontTopRodada(new Date());			
		bolaoRepository.executarFechamentoBatch(jogos_);		
	}
	
	public void gravarCalcularPontuacao(Usuario usuario, Bolao bolao, Jogos jogo, Integer ponto) {
				
		Pontuacao pontuacao = new Pontuacao(usuario,bolao);		
		Pontuacao pontuacao_ = bolaoRepository.getByOnePontuacao(pontuacao);
		//Se existe é só somar com o que já existe
		if(pontuacao_ != null) {
		   pontuacao.setTrofeus(pontuacao_.getTrofeus());
		   pontuacao.setPontos(calcularTrofeuUtilizado(usuario,bolao,jogo,jogo.getId().getRodada(),jogo.getId().getTimeA(),jogo.getId().getTimeB(),ponto)+pontuacao_.getPontos());
		   if(ponto.equals(PontuacaoBolaoEnum.PLACAR_EXATO.getPonto())) {
			  pontuacao.setTrofeus(1+pontuacao_.getTrofeus());
		   }else {
			  pontuacao.setTrofeus(pontuacao_.getTrofeus());			   
		   }
		   
		}else {
			pontuacao.setTrofeus(0);
			if(ponto.equals(PontuacaoBolaoEnum.PLACAR_EXATO.getPonto())) {
			   pontuacao.setTrofeus(1);
		   }else {
			   pontuacao.setTrofeus(0);			   
		   }
		   pontuacao.setPontos(calcularTrofeuUtilizado(usuario,bolao,jogo,jogo.getId().getRodada(),jogo.getId().getTimeA(),jogo.getId().getTimeB(),ponto));			
		}		
		bolaoRepository.gravarCalculoPontuacao(pontuacao);		
	}
	
	public void gravarCalcularTopRodada(Usuario usuario, Bolao bolao, Jogos jogo, Integer ponto) {	
						
		TopRodada topRodada = new TopRodada(usuario, bolao, jogo.getId().getRodada()); 			
		TopRodada pontuacao_ = bolaoRepository.getByOnePontuacaoTopRodada(topRodada);
		//Se existe é só somar com o que já existe
		if(pontuacao_ != null) {
			topRodada.setTrofeus(pontuacao_.getTrofeus());
			
			//====Executando o calculo quando o trofeu for utilizado ou nao
			topRodada.setPontos(calcularTrofeuUtilizado(usuario,bolao,jogo,jogo.getId().getRodada(),jogo.getId().getTimeA(),jogo.getId().getTimeB(),ponto)+pontuacao_.getPontos());
			//====FIM=====
			
		   if(ponto.equals(PontuacaoBolaoEnum.PLACAR_EXATO.getPonto())) {
			   topRodada.setTrofeus(1+pontuacao_.getTrofeus());
		   }else {
			   topRodada.setTrofeus(pontuacao_.getTrofeus());			   
		   }
		   
		}else {
			topRodada.setTrofeus(0);
			if(ponto.equals(PontuacaoBolaoEnum.PLACAR_EXATO.getPonto())) {
				topRodada.setTrofeus(1);
		   }else {
			   topRodada.setTrofeus(0);			   
		   }
			//====Executando o calculo quando o trofeu for utilizado ou nao
			topRodada.setPontos(calcularTrofeuUtilizado(usuario,bolao,jogo,jogo.getId().getRodada(),jogo.getId().getTimeA(),jogo.getId().getTimeB(),ponto));			
	        //====FIM	
		}		
		bolaoRepository.gravarCalculoPontuacaoTopRodada(topRodada);		
	}
	
	public Integer calcularTrofeuUtilizado(Usuario usuario, Bolao bolao, Jogos jogo,Rodada rodada,Time timeA,Time timeB, Integer ponto) {		
		//Se existe uma utilização do troféu tem que multiplicar por 2x		
		ContaCorrente contaCorrente = bolaoRepository.findByIdContaCorrente(new ContaCorrente(usuario,bolao,rodada.getId(),timeA.getId(),timeB.getId()));		
		if(contaCorrente!=null) {
		   return (ponto*2);		   			
		}
		return ponto;		
	}
	
	private Palpites getPalpites(Jogos jogo, Bolao bolao,Usuario usuario) {
		PalpitesId id = new PalpitesId();
		id.setRodada(jogo.getId().getRodada());
		id.setTimeA(jogo.getId().getTimeA());
		id.setTimeB(jogo.getId().getTimeB());
		id.setUsuario(usuario);
		id.setBolao(bolao);		
		return bolaoRepository.findByIdPalpite(id);	 
	}
	
	private Usuario getUsuario(String email,String token) {		
        //Optional<Usuario> usuario = usuarioRepository.findByEmail(email);        	
        Optional<Usuario> usuario = usuarioRepository.findByEmailAndUId(email,token);
		return usuario.get();
	}
	
	@Transactional
	public List<NotificationPlacarDTO> gravarPlacarCampeonatoBrasileiro() {		
		return getListaJogosParaAgendamento(CampeonatoEnum.CAMPEONATO_BRASILEIRO_A);
	}
	
	@Transactional
	public List<NotificationPlacarDTO> gravarPlacarChampionsLeague() {		
		return getListaJogosParaAgendamento(CampeonatoEnum.CHAMPIONS);
	}
	
	private List<NotificationPlacarDTO> getListaJogosParaAgendamento(CampeonatoEnum campeonatoEnum){	
		List<NotificationPlacarDTO> listaNotification = new ArrayList<NotificationPlacarDTO>();		
		try {
			List<Jogos> lista = bolaoRepository.listarJogosParaAgendamento(campeonatoEnum.getId());				
			for(Jogos temp: lista) {			
				    long minutosRestantes = Util.minutosRestantes(temp.getHoraJogo());
				    if(minutosRestantes < -90) {
				    	NotificationPlacarDTO dto = apiExternalVerificaJogos(temp,campeonatoEnum);
					    if(dto!=null)
					       listaNotification.add(dto);			       		    	
				}								
			}
			
		   }catch (Exception e) {			
		}		
		return listaNotification;
	}
	
	private NotificationPlacarDTO apiExternalVerificaJogos(Jogos jogo, CampeonatoEnum campeonatoEnum) {		
		ApiFootBallStatusDTO resposta = tratarRequisicao(jogo.getId().getTimeA().getCodApiTime());			
		if(   resposta!=null 
		   && resposta.getMatches().get(0)!=null 
		   && !resposta.getMatches().isEmpty()) {
			
			if(resposta.getMatches().get(0).getStatus().equals("FINISHED") && 
			   resposta.getMatches().get(0).getMatchday().equals(String.valueOf(jogo.getId().getRodada().getNumeroRodada()))){			
				
				//============GRAVANDO O PLACAR DO JOGO===================
				gravarPlacarJogo(jogo, 
					   resposta.getMatches().get(0).getScore().getFullTime().getHomeTeam(), 
					   resposta.getMatches().get(0).getScore().getFullTime().getAwayTeam());
				//============FIM=========================================
			   
			   NotificationPlacarDTO dto = new NotificationPlacarDTO();
			   dto.setIdCampeonato(campeonatoEnum.getId());
			   dto.setNomeTimeA(jogo.getId().getTimeA().getNome());
			   dto.setNomeTimeB(jogo.getId().getTimeB().getNome());				   
			   dto.setPlacarTimeA(resposta.getMatches().get(0).getScore().getFullTime().getHomeTeam());
			   dto.setPlacarTimeB(resposta.getMatches().get(0).getScore().getFullTime().getAwayTeam());
			   return dto;
		   } 				
	   }
		return null;		
	}
		
	public void pushNotificationEndGame(List<NotificationPlacarDTO> lista_) {		
		JSONObject msg       = new JSONObject();
		List<String> lista   = new ArrayList<String>();
        try {        	
        	if(lista_!=null && !lista_.isEmpty()) {
        		for(NotificationPlacarDTO temp: lista_){
            		msg.put("title", "Jogo Finalizado");
        			msg.put("body",temp.getNomeTimeA()+" "+temp.getPlacarTimeA()+" x "+temp.getPlacarTimeB()+" "+temp.getNomeTimeB());
        	        msg.put("notificationType", "FLUTTERNOTIFICATIONCLICK");
        	        lista = bolaoRepository.usuariosPorCampeonato(temp.getIdCampeonato());
        	        
        	        for(String send:lista){        		
                		Thread.sleep(1000);
                		if(send!=null)
                		   callToFcmServer(msg,send);        		
                	}
            	}        		
        	}        				
		} catch (JSONException e) {			
			e.printStackTrace();
		}catch (InterruptedException e) {			
			e.printStackTrace();
		}	
	}
		
	private void pushNotificationBeginGame(List<NotificationPlacarDTO> lista_,CampeonatoEnum campeonatoEnum) {		
		JSONObject msg       = new JSONObject();
		List<String> lista   = bolaoRepository.usuariosPorCampeonato(campeonatoEnum.getId());
        try {        	
        	if(lista_!=null && !lista_.isEmpty()) {
        		for(NotificationPlacarDTO temp: lista_){
            		msg.put("title", "O Jogo começa em 10 Min.");            		           		         		
        			msg.put("body",temp.getNomeTimeA()+" 0 x 0 "+temp.getNomeTimeB());        	        
        	               	        
        	        if(temp.getUrlImageTimeA()!=null && !temp.getUrlImageTimeA().isEmpty()) 
             		   msg.put("image",temp.getUrlImageTimeA());            			
             		        	                	        
        	        for(String send:lista){        		
                		Thread.sleep(100);
                		if(send!=null)
                		   callToFcmServer(msg,send);        		
                	}
            	}        		
        	}        				
		} catch (JSONException e) {			
			e.printStackTrace();
		}catch (InterruptedException e) {			
			e.printStackTrace();
		}	
	}
	
	@Bean
    public static RestTemplate getRestTemplate(){
        return new RestTemplate();
    }	
	
	private String getPostResponse(String codApiTime){
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("X-Auth-Token","36d62e83a82440118661d5c9859b8915");       
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        return getRestTemplate().exchange("http://api.football-data.org/v2/teams/"+codApiTime+"/matches?status=FINISHED&limit=1",HttpMethod.GET,entity,String.class).getBody();
    }
	
	private static String callToFcmServer(JSONObject message, String receiverFcmKey) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "key=" + "AAAAsmpA0OI:APA91bFQcKcGBsoOLPmIdSyXTIuO3Qdr7QRXWYOHZRCRBqENr8ZTfK-oEoOjyrUwkLMEqsp_aNoeFrndbLRg5YYrsMFlnu5PC9kr5wIFZQBKe4Tg4SC5ST_-HKP5wGRWL7A2s5m869Lc");
        httpHeaders.set("Content-Type", "application/json;charset=UTF-8");

        JSONObject json = new JSONObject();
        json.put("data",message);
        json.put("priority","high");        
        json.put("status","done");
        json.put("id","1125");        
        json.put("notification", message);
        json.put("sound", "default");
        json.put("to",receiverFcmKey);
        System.out.println("Sending :" + json.toString());

        HttpEntity<String> httpEntity = new HttpEntity<>(json.toString(), httpHeaders);
        return restTemplate.postForObject("https://fcm.googleapis.com/fcm/send", httpEntity, String.class);
    }
	
	private ApiFootBallStatusDTO tratarRequisicao(String idTime) {
		if(idTime!=null) {
			Gson gson   = new Gson();	
		    String json = getPostResponse(idTime);	   	    
		    return gson.fromJson(json, ApiFootBallStatusDTO.class);				
		}
		return null;
	}
	
	private void gravarPlacarJogo(Jogos jogo, String homeTeam, String awayTeam) {		
		//Fechando o jogo
		jogo.setJogoEncerrado(true);		
		//Casa
		jogo.setPlacarTimeA(Integer.parseInt(homeTeam));		
		//Visitante
		jogo.setPlacarTimeB(Integer.parseInt(awayTeam));		
		bolaoRepository.gravarPlacar(jogo);		
	}
	
	
	public List<NotificationPlacarDTO> informarInicioJogo(CampeonatoEnum campeonatoEnum) {
 		List<NotificationPlacarDTO> listaNotification = new ArrayList<NotificationPlacarDTO>();
		List<Jogos> lista=null;
		NotificationPlacarDTO dto;		
		try {			
			lista = bolaoRepository.listarJogosParaAgendamento(campeonatoEnum.getId());			
			//Verificando qual jogo começou
			for(Jogos temp: lista) {				
				//Verificando se o jogo já começou, se tiver começado não pode palpitar.									
				long minutosRestantes = Util.minutosRestantes(temp.getHoraJogo());
			    
				if(minutosRestantes >=10 && minutosRestantes<=10) {
				   dto = new NotificationPlacarDTO();
				   dto.setIdCampeonato(campeonatoEnum.getId());
				   dto.setUrlImageTimeA(temp.getId().getTimeA().getUrlImage());
				   dto.setUrlImageTimeB(temp.getId().getTimeB().getUrlImage());
				   dto.setNomeTimeA(temp.getId().getTimeA().getNome());
				   dto.setNomeTimeB(temp.getId().getTimeB().getNome());	
				   listaNotification.add(dto);							
				}							
			}			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listaNotification;		
	}
	
	@Transactional
	public void atualizarToken(TokenDTO dto) {		
		Optional<Usuario> usuario = usuarioRepository.findByEmail(dto.getEmail());		
		usuario.get().setToken(dto.getToken());	
		usuario.get().setDataUpdateToken(Util.getDataAtual());
		usuarioRepository.save(usuario.get());
	}
	
	@Transactional
	public void debitarTrofeu(DebitarDTO dto) {		
		Bolao bolao               = bolaoRepository.getBolaoById(dto.getIdBolao());		
		Optional<Usuario> usuario = usuarioRepository.findByEmail(dto.getEmail());
		Pontuacao pontuacao       = bolaoRepository.getByOnePontuacao(new Pontuacao(usuario.get(),bolao));		
		Rodada rodada             = bolaoRepository.getByRodadaId(dto.getIdRodada());
		PalpitesId palpites       = new PalpitesId(usuario.get().getId(),dto.getTimeA(),dto.getTimeB(),rodada.getId(),bolao.getId());
				
		if(pontuacao.getTrofeus()>=1){
		   pontuacao.setTrofeus(pontuacao.getTrofeus()-1);
		   bolaoRepository.atualizarSaldoTrofeu(pontuacao);		   
		   bolaoRepository.debitarContaCorrente(new ContaCorrente(usuario.get(),bolao,rodada,dto.getTimeA(),dto.getTimeB(),1));			   
		   //Sinalizando o uso do trofeu		   
		   bolaoRepository.atualizarTrofeuNoPalpite(palpites);		   
		}		
	}
	
	public TrofeuDTO getTotalTrofeuPorUsuarioEBolao(String email, Long idBolao) {
		 TrofeuDTO dto = new TrofeuDTO();
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		Pontuacao pontuacao       = bolaoRepository.getTotalTrofeu(usuario.get().getId(), idBolao);
		
		if(pontuacao!=null && pontuacao.getTrofeus()!=0) {		  
		   dto.setTrofeu(pontuacao.getTrofeus());			
		}else {
		   dto.setTrofeu(0);			
		}		
		return dto;		
	}
	
	
	public List<AgendaJogosDTO> agendaJogos(){		
		return bolaoRepository.agendaJogos();		
	}
	
	public TrofeuDTO verificaTrofeuNoJogo(String email, Long idBolao, Long idRodada, Long timeA, Long timeB) {
		
		Bolao bolao               = bolaoRepository.getBolaoById(idBolao);		
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
		Rodada rodada             = bolaoRepository.getByRodadaId(idRodada);
		
		ContaCorrente cCorrente = new ContaCorrente(usuario.get(),bolao,rodada.getId(),timeA,timeB);		
		ContaCorrente _cCorrente = bolaoRepository.findByIdContaCorrente(cCorrente);
		
		TrofeuDTO dto = new TrofeuDTO();
		
		if(_cCorrente!=null) {			
		   dto.setUtilizouNoJogo(true);		   
		}else {
		   dto.setUtilizouNoJogo(false);	
		}
		return dto;
	}
	
	public void pushNotification(CampeonatoEnum campeonatoEnum) {
		pushNotificationBeginGame(informarInicioJogo(campeonatoEnum),campeonatoEnum);		
	}	
	
	
	public Integer resultadoTrofeu(){
		Optional<Usuario> usuario = usuarioRepository.findByEmail("pablomaiden@gmail.com");		
		Optional<Bolao> bolao     = bolaoRepository.findById(60L);		
		Rodada rodada             = bolaoRepository.getByRodadaId(10L);
		
		Time timeA = new Time(18L);
		Time timeB = new Time(9L);
		
		JogosId id = new JogosId();
		
		id.setRodada(rodada);
		id.setTimeA(timeA);
		id.setTimeB(timeB);
		
		Jogos jogo = bolaoRepository.getJogoById(id);
		
		Integer resultado = calcularTrofeuUtilizado(usuario.get(),bolao.get(),jogo,rodada,timeA,timeB,25);
		return resultado;
	}
	
	public static void main(String[] args) throws JSONException, InterruptedException {	
		
		long minutosRestantes = Util.minutosRestantes(new Date());
		System.out.print(minutosRestantes);
		
		//sendNotificationAll(1L,"FLA","FLU","3","1");
		
		/*
		 * JSONObject msg = new JSONObject(); msg.put("title", "Jogo Finalizado");
		 * msg.put("body", "FLU 3 x 2 FLA"); msg.put("notificationType", "Test");
		 * callToFcmServer(msg,
		 * "exYlqvGgQLasBukLa7lEE4:APA91bGJYLPdun68iODYfAlLRRpbMzpEOF6JUvWLRs2mp3EDvEGjUPe7DGJ2X7oMIz5U21mBzBJi_PypeHO-22kAVwow12FQAFztpT_qBTVkFuFKtEmstu8Lxp-ljLFY-jBkB4biGczv"
		 * );
		 */		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
