package service.aplication.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import service.aplication.model.Noticia;
import service.aplication.repository.noticia.NoticiasRepositoryQuery;

public interface NoticiasRepository extends JpaRepository<Noticia, Long>, NoticiasRepositoryQuery{
		
	@Query("select noticia from Noticia noticia where noticia.publicar is true order by noticia.dataNoticia desc ")
    List<Noticia> listAllOrderDataNoticia();
	
	@Query("select noticia from Noticia noticia where noticia.publicar is true  order by noticia.dataNoticia desc")
    List<Noticia> noticiaDestaque();
	
	@Query("select noticia from Noticia noticia where noticia.publicar is true  order by noticia.dataNoticia desc")
	List<Noticia> noticiaRelacionadas();
	
	@Query("SELECT p FROM Noticia p WHERE p.id = :id")
	Noticia getById(@Param("id") Long id);

}
