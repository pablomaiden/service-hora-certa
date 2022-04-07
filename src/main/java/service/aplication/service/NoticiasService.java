package service.aplication.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import service.aplication.dto.PortalNoticiasDTO;
import service.aplication.model.Noticia;
import service.aplication.repository.NoticiasRepository;

@Service
public class NoticiasService {
	
	@Autowired
	private NoticiasRepository noticiasRepository;
	
	public Noticia atualizar(Long codigo, PortalNoticiasDTO noticia) {
		Optional<Noticia> noticia_ = noticiasRepository.findById(codigo);
		if (!noticia_.isPresent()) {
			 throw new EmptyResultDataAccessException(1);
		}		
		BeanUtils.copyProperties(noticia, noticia_.get(), "id");
		return noticiasRepository.save(noticia_.get());
	}
	
	public Noticia getById(Long id) {
		return noticiasRepository.findById(id).get();
	}

}
