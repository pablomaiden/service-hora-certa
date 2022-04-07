package service.aplication.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import service.aplication.model.PesquisaUsuario;
import service.aplication.model.TabelaNutricional;

public interface ServicePesquisaUsuario extends CrudRepository<PesquisaUsuario, Long> {
	
	@Query("select u from TabelaNutricional u inner join u.baseDados base where base.id=1 and upper(u.descricaoAlimento) like %:nomeAlimento%  order by u.descricaoAlimento")
    List<TabelaNutricional> findLikeTabelaNutricional(@Param("nomeAlimento") String nomeAlimento,Pageable pageable);
			
	
    
}
