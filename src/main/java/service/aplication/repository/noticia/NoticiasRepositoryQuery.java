package service.aplication.repository.noticia;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import service.aplication.model.Noticia;
import service.aplication.repository.filter.NoticiaFilter;

public interface NoticiasRepositoryQuery {	
	public Page<Noticia> filtrar(NoticiaFilter noticiaFilter,Pageable pageable);
	public List<Noticia> noticiaRelacionadasPorTag(Long id);
}
