package service.aplication.resource;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import service.aplication.dto.Anexo;
import service.aplication.storage.S3;

@RestController
@RequestMapping(value="/file")
public class FileResource extends Abstract{
	
	@Autowired
	private S3 s3;
		
	@PostMapping("/upload")	
	public Anexo uploadAnexo(@RequestParam MultipartFile anexo) throws IOException {
		String nome = s3.salvarTemporariamente(anexo);
		return new Anexo(nome, s3.configurarUrl(nome));
	}
}
