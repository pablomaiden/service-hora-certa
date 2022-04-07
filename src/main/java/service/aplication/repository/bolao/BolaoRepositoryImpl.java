package service.aplication.repository.bolao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import service.aplication.dto.bolao.AgendaJogosDTO;
import service.aplication.model.bolao.ContaCorrente;
import service.aplication.model.bolao.Convidado;
import service.aplication.model.bolao.Jogos;
import service.aplication.model.bolao.News;
import service.aplication.model.bolao.Palpites;
import service.aplication.model.bolao.PalpitesId;
import service.aplication.model.bolao.Pontuacao;
import service.aplication.model.bolao.Publicidade;
import service.aplication.model.bolao.RelBolao;
import service.aplication.model.bolao.RelNews;
import service.aplication.model.bolao.RelNewsId;
import service.aplication.model.bolao.Rodada;
import service.aplication.model.bolao.TopRodada;

public class BolaoRepositoryImpl implements BolaoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	//@PrePersist
    // public void onPrePrist(final Post toSave){
    //    toSave.setVersion(1L);
    //}
	
	@PostPersist
	@PostUpdate
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void sincronizarDadosEstasticosDuranteJogo() {
		
	}
	
	public void saveInvitation(RelBolao relBolao) {		
		manager.persist(relBolao);		
	}
	
	@Override
	public void gravarPublicidade(Publicidade publicidade) {		
		manager.persist(publicidade);		
	}
	
	@Override
	public void gravarCalculoPontuacao(Pontuacao pontuacao) {		
		Pontuacao pontuacao_ =  manager.find(Pontuacao.class,pontuacao.getId());		
		if(pontuacao_ == null) {
			manager.persist(pontuacao);				
		}else {
			manager.merge(pontuacao);	
		}			
	}
	
	@Override
	public void gravarCalculoPontuacaoTopRodada(TopRodada topRodada) {		
		TopRodada pontuacao_ =  manager.find(TopRodada.class,topRodada.getId());		
		if(pontuacao_ == null) {
			manager.persist(topRodada);				
		}else {
			manager.merge(topRodada);	
		}			
	}
	
	@Override
	public Pontuacao getByOnePontuacao(Pontuacao pontuacao) {		
		return  manager.find(Pontuacao.class,pontuacao.getId());					
	}
	
	@Override
	public TopRodada getByOnePontuacaoTopRodada(TopRodada topRodada) {		
		return  manager.find(TopRodada.class,topRodada.getId());					
	}
	
	@Override
	public Rodada getByRodadaId(Long id) {		
		return  manager.find(Rodada.class,id);					
	}
	
	@Override
	public void executarFechamentoBatch(Rodada rodada) {		
		 manager.merge(rodada);				
	}
	
	@Override
	public void executarFechamentoBatch(Jogos jogo) {		
		 manager.merge(jogo);				
	}
	
	public void salvarConvidado(Convidado convidado) {		
		manager.persist(convidado);		
	}
	
	public void convidadoAceito(RelBolao relBolao) {		
		manager.persist(relBolao);		
	}
	
	@Override
	public void gravarPlacar(Jogos jogo) {		
		manager.merge(jogo);		
	}
	
	@Override
	public Palpites findByIdPalpite(PalpitesId id) {
		return manager.find(Palpites.class,id);		
	}
	
	@Override
	public void palpitar(Palpites palpites) {			
		//Verificando se existe um palpite
		Palpites palpite_ = manager.find(Palpites.class,palpites.getId());
		
		if(palpite_ == null) {
		   manager.persist(palpites);			
		}else {		   	
		   manager.merge(palpites);	
		}				
	}
	
	@Override
	public void atualizarSituacaoConvidadoAceito(Convidado convidado) {		
		Convidado convidado_ = manager.find(Convidado.class, convidado.getId());		
		convidado_.setAceito(true);
		convidado_.setDataAceite(new Date());
		convidado_.setDataNegada(null);
		manager.merge(convidado_);
	}
	
	@Override
	public void atualizarSituacaoConvidadoNegado(Convidado convidado) {	
		convidado.setAceito(false);
		convidado.setDataAceite(null);
		convidado.setDataNegada(new Date());
		manager.merge(convidado);
	}
		
    @SuppressWarnings("unchecked")
	public Long membrosPorBolao(Long idBolao){    	
    	List<Object> result;
    	Long id=0L;
    	result = (List<Object>) manager.createNativeQuery(" "
				+ " select count(b.bolao_fk) from bolao.bolao a inner join bolao.rel_bolao b on a.id=b.bolao_fk " + 
				  " where b.bolao_fk = ?1" + 
				  " group by b.bolao_fk ").setParameter("1",idBolao).getResultList();    	
    	
    	for(Object temp : result) {
    		id = Long.parseLong(temp+"");    		
    	}     	
    	return id;
    }  
    
    
    @SuppressWarnings("unchecked")
	public List<String> usuariosPorCampeonato(Long idCampeonato){    	
    	List<Object[]> result;
    	List<String> retornoToken = new ArrayList<String>();
    	result = (List<Object[]>) manager.createNativeQuery(" "
				+ " select distinct relbolao.usuario_fk,usuario.token from bolao.rel_bolao relbolao " + 
				"   inner join bolao.bolao bolao on relbolao.bolao_fk=bolao.id " + 
				"   inner join seguranca.usuario usuario on relbolao.usuario_fk=usuario.id " + 
				"   where " + 
				"   bolao.campeonato_fk = ?1" + 
				"   order by relbolao.usuario_fk asc ").setParameter("1",idCampeonato).getResultList();    	
    	
    	for(Object[] temp : result) {
    		retornoToken.add(temp[1].toString());    				
    	}     	
    	return retornoToken;
    }
    
    @SuppressWarnings("unchecked")
	public List<Long> usuariosPorCampeonato_(Long idCampeonato){    	
    	List<Object[]> result;
    	List<Long> retornoIds = new ArrayList<Long>();
    	result = (List<Object[]>) manager.createNativeQuery(" "
				+ " select distinct (relbolao.usuario_fk), relbolao.usuario_fk as usuario  from bolao.rel_bolao relbolao " + 
				"   inner join bolao.bolao bolao on relbolao.bolao_fk=bolao.id " + 
				"   inner join seguranca.usuario usuario on relbolao.usuario_fk=usuario.id " + 
				"   where " + 
				"   bolao.campeonato_fk = ?1" + 
				"   order by relbolao.usuario_fk asc ").setParameter("1",idCampeonato).getResultList();    	
    	
    	for(Object[] temp : result) {
    		retornoIds.add(Long.parseLong(temp[0].toString()));    				
    	}     	
    	return retornoIds;
    }    
    
    @SuppressWarnings("unchecked")
	public List<Long> bolaosPorUsuarioIds(Long idCampeonato, Long idUsuario){    	
    	List<Object[]> result;
    	List<Long> retornoIds = new ArrayList<Long>();
    	result = (List<Object[]>) manager.createNativeQuery(" "
    			+"     select distinct(relbolao.bolao_fk), relbolao.bolao_fk as bolao from bolao.rel_bolao relbolao "
    			+"     inner join bolao.bolao bolao on bolao.id=relbolao.bolao_fk" 
    			+"     where" 
    			+"     bolao.campeonato_fk=?1" 
    			+"     and relbolao.usuario_fk=?2")
    			.setParameter("1",idCampeonato)
    			.setParameter("2", idUsuario).getResultList();    	
    	
    	for(Object[] temp : result) {
    		retornoIds.add(Long.parseLong(temp[0].toString()));    				
    	}     	
    	return retornoIds;
    }
    
	public boolean existeAlgumJogoEncerrado(Long idRodada){    	
    	int existe = manager.createNativeQuery(" select * from bolao.jogos jogo where jogo.rodada_fk = ?1 and jogo.jogo_encerrado is true ")
    			.setParameter("1",idRodada).getResultList().size();    	   	
    	return existe>0;
    }
    
    public void deixarBolao(RelBolao bolao) {     	
    	RelBolao bolao_ = manager.find(RelBolao.class, bolao.getId());    			 	
    	manager.remove(bolao_);    	
    }
    
    public void removerConvidado(Convidado convidado) {     	    			 	
    	manager.remove(convidado);    	
    }
    
    public void newsDismiss(RelNews relNews) {      	
    	RelNews news = manager.find(RelNews.class, relNews.getId());   	
    	manager.remove(news);    	
    }
    
    public RelNews getRelNews(RelNewsId id) {
    	return manager.find(RelNews.class,id);    	
    }
    
    @Override
    public void salvarNotification(RelNews relNews) {    	   	
    	manager.persist(relNews); 	
    }
    
    @Override
    public void atualizarNotificacao(RelNews relNews) {    	   	
    	manager.merge(relNews);	
    }
    
    @Override
    public News getNewsById(Long id) {      	
    	return manager.find(News.class,id);     	
    }
    
    @Override
    public ContaCorrente findByIdContaCorrente(ContaCorrente id) {      	
    	return manager.find(ContaCorrente.class,id.getId()); 	
    }
    
    @Override
    public void debitarContaCorrente(ContaCorrente contaCorrente) {      	
    	manager.persist(contaCorrente);     	
    }
    
    @Override
    public void atualizarSaldoTrofeu(Pontuacao pontuacao) {      	
    	manager.merge(pontuacao);	
    }
    
    @Override
    public void atualizarTrofeuNoPalpite(PalpitesId id) {      	
    	manager.createNativeQuery(" update bolao.palpites set trofeu_utilizado=true "
    			+ " where time_a_fk  = ?1 "
    			+ " and   time_b_fk  = ?2 "
    			+ " and   rodada_fk  = ?3 "
    			+ " and   bolao_fk   = ?4 "
    			+ " and   usuario_fk = ?5 ")
    	.setParameter("1", id.getTimeA().getId())
    	.setParameter("2", id.getTimeB().getId())
    	.setParameter("3", id.getRodada().getId())
    	.setParameter("4", id.getBolao().getId())
    	.setParameter("5", id.getUsuario().getId())
    	.executeUpdate();    	
    }
    
    @SuppressWarnings("unchecked")
    public List<AgendaJogosDTO> agendaJogos(){    
    	   	
    	Query query = manager.createNativeQuery(
    			  " select "
    			  + "  time_a.nome as timeA," + 
    			  "    time_a.url_image as escudo_time_a, " + 
    			  "    time_b.nome as timeB, " + 
    			  "    time_b.url_image as escudo_time_b, " + 
    			  "    to_char(jogo.hora_jogo,'DD/MM HH24:MI') as hora  "
    			  +" from  " + 
    			  "   bolao.jogos jogo inner join bolao.times time_a on time_a.id=jogo.time_a_fk   " + 
    			  "                    inner join bolao.times time_b on time_b.id=jogo.time_b_fk   " + 
    			  " where " + 
    			  " jogo.hora_jogo >= current_date - interval '0 days' and jogo.hora_jogo < current_date + interval '4 days' " + 
    			  " order by jogo.hora_jogo asc ");
    	
    	List<Object[]> result = query.getResultList();    	
    	List<AgendaJogosDTO> agenda = new ArrayList<AgendaJogosDTO>();	
    	
    	for(Object[] temp : result) {    		
    		agenda.add(new AgendaJogosDTO(
    				temp[0].toString(),
    				temp[1].toString(), 
    				
    				temp[2].toString(),
    				temp[3].toString(),
    				
    				temp[4].toString()));    		  				
    	}    	
    	return agenda;
    }
    

}
