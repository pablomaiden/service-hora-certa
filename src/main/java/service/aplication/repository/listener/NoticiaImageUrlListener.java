package service.aplication.repository.listener;

import javax.persistence.PostLoad;
import org.springframework.util.StringUtils;
import service.aplication.model.Noticia;
import service.aplication.storage.S3;
import service.aplication.Application;

public class NoticiaImageUrlListener {
	
	@PostLoad
	public void postLoad(Noticia noticia) {
		if (StringUtils.hasText(noticia.getImagePath())) {
			S3 s3 = Application.getBean(S3.class);
			noticia.setImageUrl(s3.configurarUrl(noticia.getImagePath()));
		}
	}

}
