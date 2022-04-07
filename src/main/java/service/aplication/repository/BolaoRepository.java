package service.aplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import service.aplication.model.bolao.Bolao;
import service.aplication.model.bolao.Campeonato;
import service.aplication.model.bolao.Convidado;
import service.aplication.model.bolao.Jogos;
import service.aplication.model.bolao.JogosId;
import service.aplication.model.bolao.Palpites;
import service.aplication.model.bolao.Pontuacao;
import service.aplication.model.bolao.Publicidade;
import service.aplication.model.bolao.RelBolao;
import service.aplication.model.bolao.RelNews;
import service.aplication.model.bolao.Rodada;
import service.aplication.model.bolao.TopRodada;
import service.aplication.model.seguranca.Usuario;
import service.aplication.repository.bolao.BolaoRepositoryQuery;

public interface BolaoRepository extends JpaRepository<Bolao, Long>, BolaoRepositoryQuery {
	
	@Query(" select campeonato from Campeonato campeonato ")
    List<Campeonato> listaCampeonatos();
	
	@Query(" select bolao from Bolao bolao inner join fetch bolao.campeonato campeonato where campeonato.id =:idCampeonato  ")
    List<Bolao> listaBolao(@Param("idCampeonato") Long idCampeonato);
	
	@Query(" select bolao from Bolao bolao inner join fetch bolao.campeonato campeonato")
    List<Bolao> listaBolao();
		
	@Query(" select bolao from Bolao bolao inner join fetch bolao.usuario usuario  where bolao.id =:idBolao and usuario.id =:idUsuario")
    Bolao getBolaoByIdAndUsuario(@Param("idBolao") Long idBolao, @Param("idUsuario") Long idUsuario);
	
	@Query(" select bolao from Bolao bolao inner join fetch bolao.usuario usuario inner join fetch bolao.campeonato campeonato  where bolao.id =:idBolao ")
    Bolao getBolaoById(@Param("idBolao") Long idBolao);
	
	@Query(" select relBolao from RelBolao relBolao inner join fetch relBolao.id id inner join fetch id.usuario usuario "
		+  " inner join fetch id.bolao bolao where usuario.id =:idUsuario and bolao.id =:idBolao ")
	RelBolao getBolaoByIdParticipante(@Param("idBolao") Long idBolao, @Param("idUsuario") Long idUsuario);
	
	@Query(" select bolao from Bolao bolao inner join fetch bolao.relBolao rel where rel.id.usuario.id = :id ")
    List<Bolao> meusBoloes(@Param("id") Long idUsuario);
	
	@Query(" select bolao from Bolao bolao inner join fetch bolao.usuario usuario where usuario.id = :id ")
    List<Bolao> gerenciarMeusBolaos(@Param("id") Long idUsuario);
	
	@Query("    select convidado from Convidado convidado inner join fetch convidado.id id "
			+ " inner join fetch id.bolao bolao "
			+ " where bolao.usuario.id =:idUsuario and convidado.aceito=false and convidado.dataAceite is null and convidado.dataNegada is null ")
    List<Convidado> listarConvidados(@Param("idUsuario") Long idUsuario);
	
	@Query(   " select convidado from Convidado convidado "
			+ " inner join fetch convidado.id id    "
			+ " inner join fetch id.bolao bolao     "
			+ " inner join fetch id.usuario usuario "
			+ " where "
			+ "     usuario.id =:idUsuario "
			+ " and bolao.id =:idBolao     "
			+ " and convidado.aceito=false "
			+ " and convidado.dataAceite is null "
			+ " and convidado.dataNegada is null ")
    Convidado getConvidado(@Param("idBolao") Long idBolao, @Param("idUsuario") Long idUsuario);
	
	@Query(   " select convidado from Convidado convidado "
			+ " inner join fetch convidado.id id    "
			+ " inner join fetch id.bolao bolao     "
			+ " inner join fetch id.usuario usuario "
			+ " where "
			+ "     usuario.id =:idUsuario "
			+ " and bolao.id   =:idBolao   ")
    Convidado getConvidadoById(@Param("idBolao") Long idBolao, @Param("idUsuario") Long idUsuario);
	
	@Query(" select jogos from Jogos jogos inner join fetch jogos.id id inner join fetch id.rodada rodada where rodada.campeonato.id =:idCampeonato "
		  +" and rodada.rodadaEncerrada=:encerrada and rodada.rodadaAtual=:rodadaAtual order by jogos.horaJogo asc")
    List<Jogos> listarJogosPorCampeonato(@Param("idCampeonato") Long idCampeonato, @Param("encerrada") boolean encerrada, @Param("rodadaAtual") boolean rodadaAtual);
	
	@Query(" select jogos from Jogos jogos inner join fetch jogos.id id inner join fetch id.rodada rodada where rodada.campeonato.id =:idCampeonato and jogos.jogoEncerrado =:jogoEncerrado "
		  +" and rodada.rodadaEncerrada=:encerrada order by jogos.horaJogo asc")
    List<Jogos> listarResultadoJogosPorCampeonato(@Param("idCampeonato") Long idCampeonato, @Param("jogoEncerrado") boolean jogoEncerrado, @Param("encerrada") boolean encerrada);

	@Query("  select jogos from Jogos jogos "
			+" inner join fetch jogos.id id "
			+" inner join fetch id.rodada rodada "
			+" where "
			+"      rodada.id=(select max(rodada_.id) from Rodada rodada_ where rodada_.campeonato.id =:idCampeonato and rodada_.rodadaEncerrada is true and rodada_.rodadaAtual is false) ")
	    List<Jogos> listarResultadoJogosPorCampeonatoRodadaAnterior(@Param("idCampeonato") Long idCampeonato);

	
	@Query(" select jogos from Jogos jogos inner join fetch jogos.id id inner join fetch id.rodada rodada where rodada.campeonato.id =:idCampeonato and jogos.jogoEncerrado =:jogoEncerrado "
			  +" and rodada.rodadaAtual=true and rodada.rodadaEncerrada=:encerrada and rodada.dataExecBatchCalculoPontuacao is null order by jogos.horaJogo asc")
	    List<Jogos> listarResultadoJogosPorCampeonatoExecutorBatch(@Param("idCampeonato") Long idCampeonato, @Param("jogoEncerrado") boolean jogoEncerrado, @Param("encerrada") boolean encerrada);

	@Query(" select jogos from Jogos jogos "
		  +" inner join fetch jogos.id id  "
		  +" inner join fetch id.rodada rodada "
		  +"  where "
		  +"      rodada.campeonato.id =:idCampeonato "
		  +"  and jogos.jogoEncerrado =:jogoEncerrado "
		  +"  and rodada.rodadaEncerrada =:rodadaEncerrada "
		  +"  and jogos.dataExecBatchCalculoPontTopRodada is null "
		  +"  and rodada.dataExecBatchCalculoPontuacao is null order by jogos.horaJogo asc ")
	    List<Jogos> listarResultadoJogosPorCampeonatoTopRodadaExecutorBatch(@Param("idCampeonato") Long idCampeonato, @Param("jogoEncerrado") boolean jogoEncerrado, @Param("rodadaEncerrada") boolean rodadaEncerrada);

	
	@Query("    select topRodada from TopRodada topRodada "
			+ " inner join fetch topRodada.id id    "
			+ " inner join fetch id.usuario usuario "
			+ " inner join fetch id.bolao bolao     "
			+ " inner join fetch id.rodada rodada   "
			+ " where bolao.id =:idBolao and rodada.id =:idRodada  order by topRodada.pontos desc, topRodada.trofeus desc  ")
	    List<TopRodada> listaTopRodada(@Param("idBolao") Long idBolao, @Param("idRodada") Long idRodada);
	
	@Query("    select rodada from Rodada rodada "
		  +"    inner join fetch rodada.campeonato campeonato "
		  +"    where "
		  +"    campeonato.id =:idCampeonato and rodada.rodadaAtual is true ")
	    Rodada getRodada(@Param("idCampeonato") Long idCampeonato);
	
	@Query("    select rodada from Rodada rodada "
			  +"    inner join fetch rodada.campeonato campeonato "
			  +"    where "
			  +"    rodada.id=(select max(rodada_.id) from Rodada rodada_ where rodada_.campeonato.id =:idCampeonato and rodada_.rodadaEncerrada is true and rodada_.rodadaAtual is false) ")
		    Rodada getRodadaAnterior(@Param("idCampeonato") Long idCampeonato);
	
		
	@Query("    select rodada from Rodada rodada "
			  +"    inner join fetch rodada.campeonato campeonato "
			  +"    where "
			  +"    rodada.id=(select max(rodada_.id) from Rodada rodada_ where rodada_.campeonato.id =:idCampeonato and rodada_.rodadaEncerrada is false and rodada_.rodadaAtual is true) ")
		    Rodada getRodadaAnteriorJogoEncerrado(@Param("idCampeonato") Long idCampeonato);
	
	@Query("    select pontuacoes from Pontuacao pontuacoes "
			+ " inner join fetch pontuacoes.id id "
			+ " inner join fetch id.usuario usuario "
			+ " inner join fetch id.bolao bolao "
			+ " where bolao.id =:idBolao order by pontuacoes.pontos desc, pontuacoes.trofeus desc  ")
	    List<Pontuacao> listaRankingUsuarios(@Param("idBolao") Long idBolao);
	
	@Query("    select pontuacoes from Pontuacao pontuacoes "
			+ " inner join fetch pontuacoes.id id "
			+ " inner join fetch id.usuario usuario "
			+ " inner join fetch id.bolao bolao "
			+ " where usuario.id =:idUsuario ")
	    List<Pontuacao> listaPontuacaoByUsuario(@Param("idUsuario") Long idUsuario);
	
	@Query(" select jogo from Jogos jogo where jogo.id =:idJogo ")
	Jogos getJogoById(@Param("idJogo") JogosId idJogo);
	
	@Query(" select publicidade from Publicidade publicidade where publicidade.bolao.id =:idBolao and publicidade.atual is true ")
	Publicidade getPublicidade(@Param("idBolao") Long idBolao);
	
	@Query(" select news from RelNews news inner join fetch news.id id where id.usuario =:usuario   ")
    List<RelNews> getListaNewsByUsuario(@Param("usuario") Usuario usuario);
	
	@Query(" select jogos from Jogos jogos "
		 + " inner join fetch jogos.id id "
		 + " inner join fetch id.timeA timeA "
		 + " inner join fetch id.timeB timeB "
		 + " inner join fetch id.rodada rodada "		 
		 + " where rodada.campeonato.id =:idCampeonato "
		 +" and rodada.rodadaAtual=true and jogos.jogoEncerrado=false  order by jogos.horaJogo asc")
	    List<Jogos> listarJogosParaAgendamento(@Param("idCampeonato") Long idCampeonato);
	
	
	/*
	 * @Query(" select jogos from Jogos jogos " + " inner join fetch jogos.id id " +
	 * " inner join fetch id.timeA timeA " + " inner join fetch id.timeB timeB " +
	 * " inner join fetch id.rodada rodada " + " where " +
	 * "      rodada.campeonato.id =:idCampeonato " +
	 * "  and rodada.rodadaAtual=true    " + "  and jogos.jogoEncerrado=false  " +
	 * "  and jogos.horaJogo BETWEEN now()::timestamp - (interval '3 hours') AND now()::timestamp "
	 * + "  order by jogos.horaJogo asc ") List<Jogos>
	 * listarJogosAcompanharTempoReal(@Param("idCampeonato") Long idCampeonato);
	 */
	
	
	@Query(" select relBolao from  RelBolao relBolao inner join fetch relBolao.id id inner join fetch id.bolao bolao  where  bolao.id =:idBolao")
		List<RelBolao> listarUsuariosPorBolao(@Param("idBolao") Long idBolao);
	
	@Query(" select relBolao  from  RelBolao relBolao "
		+  " inner join fetch relBolao.id id inner join fetch id.bolao bolao  "
		+  " inner join fetch bolao.campeonato campeonato  "
		+  " inner join fetch bolao.usuario usuario  "
		+  " where  campeonato.id =:idCampeonato ")
	List<RelBolao> listarUsuariosPorCampeonato(@Param("idCampeonato") Long idCampeonato);
	
	@Query("  select palpites from Palpites palpites "
		  +"  inner join fetch palpites.id id        "
		  +"  inner join fetch id.bolao bolao        "
		  +"  inner join fetch id.rodada rodada      "
		  +"  inner join fetch id.timeA timeA        "
		  +"  inner join fetch id.timeB timeB        "
		  +" where     bolao.id  =:idBolao "
		  +"       and rodada.id =:idRodada "
		  +"       and timeA.id  =:idTimeA "
		  +"       and timeB.id  =:idTimeB ")
	List<Palpites> listarPalpitesPorBolaoJogoERodada(@Param("idBolao") Long idBolao, @Param("idRodada") Long idRodada, @Param("idTimeA") Long idTimeA, @Param("idTimeB") Long idTimeB);
	
	
	
	@Query("    select pontuacoes from Pontuacao pontuacoes "
			+ " inner join fetch pontuacoes.id id "
			+ " inner join fetch id.usuario usuario "
			+ " inner join fetch id.bolao bolao "
			+ " where bolao.id =:idBolao and usuario.id =:idUsuario")
	Pontuacao getTotalTrofeu(@Param("idUsuario") Long idUsuario, @Param("idBolao") Long idBolao);
			

}
