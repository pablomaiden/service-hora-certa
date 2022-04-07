package service.aplication.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import service.aplication.model.TabelaNutricional;


public interface ServiceTabelaNutricional extends CrudRepository<TabelaNutricional, Long> {
	
	@Query("select u from TabelaNutricional u inner join u.baseDados base where base.id=1 and upper(remove_acento(u.descricaoAlimento)) like %:nomeAlimento%  order by u.descricaoAlimento")
    List<TabelaNutricional> findLikeTabelaNutricional(@Param("nomeAlimento") String nomeAlimento);
	
	@Query("select u from TabelaNutricional u where u.id=:id ")
    Optional<TabelaNutricional> findById(@Param("id") Long id);
		
	
    
}
