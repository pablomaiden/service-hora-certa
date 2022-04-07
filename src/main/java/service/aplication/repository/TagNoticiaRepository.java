package service.aplication.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import service.aplication.model.TagNoticia;

public interface TagNoticiaRepository extends JpaRepository<TagNoticia, Long>{
		
	@Query("select tag from TagNoticia tag order by tag.tag desc")
    List<TagNoticia> listAllTags();
	
}
