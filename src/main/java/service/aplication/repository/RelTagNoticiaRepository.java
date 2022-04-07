package service.aplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import service.aplication.model.RelTagNoticia;

public interface RelTagNoticiaRepository extends JpaRepository<RelTagNoticia, Long>{
	
	@Transactional
	@Modifying
	@Query("DELETE FROM RelTagNoticia relTag WHERE relTag.noticia.id =:id")
    void deleteAll(@Param("id") Long id);
		
	
	
}
