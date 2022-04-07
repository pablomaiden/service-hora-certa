package service.aplication.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import service.aplication.dto.TagNoticiasDTO;
import service.aplication.model.Noticia;
import service.aplication.model.RelTagNoticia;
import service.aplication.model.TagNoticia;
import service.aplication.repository.RelTagNoticiaRepository;
import service.aplication.repository.TagNoticiaRepository;

@Service
public class TagNoticiaService {
	
	@Autowired
	private TagNoticiaRepository tagNoticiaRepository;
	
	@Autowired
	private RelTagNoticiaRepository relTagNoticiaRepository;
	
	public List<TagNoticia> listAllTag(){
		return tagNoticiaRepository.listAllTags();
	}
	
	public void salvarTagNoticia(List<TagNoticiasDTO> tags, Noticia noticia) {
		
		RelTagNoticia reltagNoticia=null;
		TagNoticia tagNoticia=null;
		
		//List<RelTagNoticia> lista = new ArrayList<RelTagNoticia>();
		
		relTagNoticiaRepository.deleteAll(noticia.getId());		
		
		for(TagNoticiasDTO temp: tags) {
			reltagNoticia = new RelTagNoticia();
			tagNoticia    = new TagNoticia();
			tagNoticia.setId(temp.getId());
			
			reltagNoticia.setNoticia(noticia);
			reltagNoticia.setTagnoticia(tagNoticia);
			relTagNoticiaRepository.save(reltagNoticia);	
		}
		//relTagNoticiaRepository.deleteInBatch(lista);
		//relTagNoticiaRepository.saveAll(lista);
		
	}

}
