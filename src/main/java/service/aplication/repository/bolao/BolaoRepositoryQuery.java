package service.aplication.repository.bolao;

import java.util.List;

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

public interface BolaoRepositoryQuery {		
	 public Long membrosPorBolao(Long idBolao);
	 public void saveInvitation(RelBolao relBolao);	
	 public void gravarPublicidade(Publicidade publicidade);
	 public void deixarBolao(RelBolao relBolao);
	 public void removerConvidado(Convidado convidado);
	 public void salvarConvidado(Convidado convidado);
	 public void convidadoAceito(RelBolao relBolao);	
	 public void gravarPlacar(Jogos jogo);
	 public void atualizarSituacaoConvidadoAceito(Convidado convidado);
	 public void atualizarSituacaoConvidadoNegado(Convidado convidado);	 
	 public void palpitar(Palpites palpites);
	 public Palpites findByIdPalpite(PalpitesId id);
	 public void gravarCalculoPontuacao(Pontuacao pontuacao);
	 public void gravarCalculoPontuacaoTopRodada(TopRodada topRodada);
	 public TopRodada getByOnePontuacaoTopRodada(TopRodada topRodada);
	 public Pontuacao getByOnePontuacao(Pontuacao pontuacao);
	 public Rodada getByRodadaId(Long id);
	 public void executarFechamentoBatch(Rodada rodada);
	 public void executarFechamentoBatch(Jogos jogo);
	 public void newsDismiss(RelNews relNews);
	 public RelNews getRelNews(RelNewsId id);
	 public News getNewsById(Long id);
	 public void salvarNotification(RelNews relNews);
	 public List<String> usuariosPorCampeonato(Long idCampeonato);
	 public List<Long> usuariosPorCampeonato_(Long idCampeonato);  
	 public void atualizarNotificacao(RelNews relNews);
	 public boolean existeAlgumJogoEncerrado(Long idRodada);
	 public List<Long> bolaosPorUsuarioIds(Long idCampeonato, Long idUsuario);
	 public void debitarContaCorrente(ContaCorrente contaCorrente);
	 public void atualizarSaldoTrofeu(Pontuacao pontuacao);
	 public ContaCorrente findByIdContaCorrente(ContaCorrente id);
	 public void atualizarTrofeuNoPalpite(PalpitesId id);
	 public List<AgendaJogosDTO> agendaJogos();
	
}
