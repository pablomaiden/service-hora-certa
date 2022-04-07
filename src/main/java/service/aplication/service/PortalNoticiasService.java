package service.aplication.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import service.aplication.model.Noticia;

public interface PortalNoticiasService extends CrudRepository<Noticia,Long> {
	
	@Query("select noticia from Noticia noticia order by noticia.dataNoticia desc ")
    List<Noticia> lista();
	
	@Query("select noticia from Noticia noticia order by noticia.dataNoticia desc")
    List<Noticia> noticiaDestaque();
	
	Optional<Noticia> findById(Long id);	
	
	    
}
