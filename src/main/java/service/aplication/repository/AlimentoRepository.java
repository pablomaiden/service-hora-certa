package service.aplication.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import service.aplication.model.CategoriaAlimentos;
import service.aplication.model.TabelaNutricional;
import service.aplication.repository.alimento.AlimentoRepositoryQuery;

public interface AlimentoRepository extends JpaRepository<TabelaNutricional, Long>, AlimentoRepositoryQuery{
		
	@Query("select u from TabelaNutricional u inner join u.baseDados base where base.id=1 and upper(remove_acento(u.descricaoAlimento)) like %:nomeAlimento%  order by u.descricaoAlimento")
    List<TabelaNutricional> findLikeTabelaNutricional(@Param("nomeAlimento") String nomeAlimento,Pageable pageable);
	
	@Query("select u from TabelaNutricional u where u.id=:id ")
    Optional<TabelaNutricional> findById(@Param("id") Long id);
		
	@SuppressWarnings("deprecation")
	default List<TabelaNutricional> findLikeTabelaNutricional(String nomeAlimento) {
	    return findLikeTabelaNutricional(nomeAlimento, new PageRequest(0,60));
	}
	
	/*
	 * @Query("select u from TabelaNutricional         "
	 * +" u inner join u.baseDados base          "
	 * +"   inner join u.categoriaAlimentos catg "
	 * +" where                                  "
	 * +"   base.id=1                            "
	 * +"   and catg.id = :id                    ") List<TabelaNutricional>
	 * listarTabelaNutricionalPorCategoria(@Param("id") Long id);
	 */
	
	@Query("select u from CategoriaAlimentos u order by u.nome desc")
	List<CategoriaAlimentos> listAllCategoriaAlimentos();
	
	@Modifying
	@Transactional
	@Query("UPDATE TabelaNutricional SET fenilalanina = :fenilalanina where id = :id")
	void update(@Param("fenilalanina") Double fenilalanina, @Param("id") Long id);
	
}
