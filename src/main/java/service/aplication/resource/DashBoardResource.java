package service.aplication.resource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.aplication.dto.NoticiaDTO;
import service.aplication.dto.PortalNoticiasDTO;
import service.aplication.dto.TagNoticiasDTO;
import service.aplication.model.Noticia;
import service.aplication.model.RelTagNoticia;
import service.aplication.model.TagNoticia;
import service.aplication.repository.NoticiasRepository;
import service.aplication.repository.filter.NoticiaFilter;
import service.aplication.service.NoticiasService;
import service.aplication.service.TagNoticiaService;
import service.aplication.storage.S3;

@RestController
@RequestMapping(value="/dashboard")
public class DashBoardResource extends Abstract{
		
	@Autowired
	private NoticiasRepository portalNoticiasRepository;
	
	@Autowired
	private NoticiasService noticiasService;	
	
	@Autowired
	private TagNoticiaService tagNoticiaService;
	
	@Autowired
	private S3 s3;
		
	@PostMapping   
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_NOTICIAS') and #oauth2.hasScope('write')")
	public ResponseEntity<PortalNoticiasDTO>salvar(@Valid @RequestBody PortalNoticiasDTO portalNoticiasDTO) {		
		Noticia portalNoticias;	
		try{			
			portalNoticias = new Noticia();			
			BeanUtils.copyProperties(portalNoticiasDTO,portalNoticias);
			portalNoticias.setDataNoticia(LocalDateTime.now());
			
			if (StringUtils.hasText(portalNoticiasDTO.getImagePath())) {
				s3.salvar(portalNoticiasDTO.getImagePath());
			}
						
			portalNoticias = portalNoticiasRepository.save(portalNoticias);			
			//salvando as tags das noticias
			tagNoticiaService.salvarTagNoticia(portalNoticiasDTO.getTags(),portalNoticias);			
			
			BeanUtils.copyProperties(portalNoticias,portalNoticiasDTO);
			
			portalNoticiasDTO.setMensagemSucesso("Noticia gravada com sucesso!");
			return new ResponseEntity<PortalNoticiasDTO>(portalNoticiasDTO,httpHeaders(),HttpStatus.CREATED);	
		}catch (Exception e) {				
			portalNoticiasDTO.setMensagemErro(e.getMessage());
			return new ResponseEntity<PortalNoticiasDTO>(portalNoticiasDTO,httpHeaders(), HttpStatus.BAD_REQUEST);
		}		   
    }
		
	@GetMapping 
	@PreAuthorize("hasAuthority('ROLE_LISTAR_NOTICIAS') and #oauth2.hasScope('read')")
    public List<PortalNoticiasDTO> noticias() {		
		List<PortalNoticiasDTO> dtos = new ArrayList<PortalNoticiasDTO>();
		PortalNoticiasDTO dto;
		try{			
			List<Noticia> noticias = portalNoticiasRepository.listAllOrderDataNoticia();			
			for(Noticia temp: noticias){
				dto = new PortalNoticiasDTO();				
				BeanUtils.copyProperties(temp,dto);
				//dto.setDataNoticia(Util.formatterDate(temp.getDataNoticia()));
				dtos.add(dto);
			}			
            return dtos;			
		}catch (Exception e) {
			System.out.print("Erro:"+e.getStackTrace());			
		}
		return null;
		
    }
	
	@GetMapping("/pesquisar")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_NOTICIAS') and #oauth2.hasScope('read')")
	public Page<NoticiaDTO> pesquisar(NoticiaFilter noticiaFilter,Pageable pageable) {
		
		//Page<NoticiaDTO> lista = (Page<NoticiaDTO>) new ArrayList<NoticiaDTO>();
		List<NoticiaDTO> lista = new ArrayList<NoticiaDTO>();
		
		Page<Noticia> noticias = portalNoticiasRepository.filtrar(noticiaFilter,pageable);
						
		for(Noticia temp: noticias) {
			temp.setImagePath("https://s3.amazonaws.com/dashboard-sistemas-arquivos-geral/"+temp.getImagePath());
		}
		
		for(Noticia temp: noticias) {
			NoticiaDTO dto = new NoticiaDTO();
			BeanUtils.copyProperties(temp,dto);
			lista.add(dto);
		}
		
		
		Page<NoticiaDTO> lst = new PageImpl<>(lista);
		return lst;
	}
	
	
	@DeleteMapping("/{codigo}")	
	@PreAuthorize("hasAuthority('ROLE_EXCLUIR_NOTICIAS') and #oauth2.hasScope('write')")
    public ResponseEntity<PortalNoticiasDTO>delete(@PathVariable Long codigo) {
		PortalNoticiasDTO noticia = new PortalNoticiasDTO();
		try{
			//=======Removendo a imagem do S3 da Amazon AWS========
			  Noticia noticia_ = portalNoticiasRepository.findById(codigo).get();
			  s3.remover(noticia_.getImagePath());
			//========================FIM==========================
			
			portalNoticiasRepository.deleteById(codigo);
			noticia.setMensagemSucesso("Notícia deletada com sucesso!");
			return new ResponseEntity<PortalNoticiasDTO>(noticia,httpHeaders(),HttpStatus.OK);
		}catch (Exception e) {
			noticia.setMensagemErro(e.getMessage());
			return new ResponseEntity<PortalNoticiasDTO>(noticia,httpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}	
	
	@PutMapping
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_NOTICIAS') and #oauth2.hasScope('write')")
    public ResponseEntity<PortalNoticiasDTO>update(@Valid @RequestBody PortalNoticiasDTO noticia) {
		try{
			
			if (StringUtils.isEmpty(noticia.getImagePath()) && StringUtils.hasText(noticia.getImagePath())) {
				s3.remover(noticia.getImagePath());
			} else if (StringUtils.hasText(noticia.getImagePath()) && !noticia.getImagePath().equals(noticia.getImagePath())) {
				s3.substituir(noticia.getImagePath(),noticia.getImagePath());
			}			
			
			Noticia noticia_ = noticiasService.atualizar(noticia.getId(),noticia);
			
			//salvando as tags das noticias
			tagNoticiaService.salvarTagNoticia(noticia.getTags(),noticia_);
			
			BeanUtils.copyProperties(noticia_,noticia);
			noticia.setMensagemSucesso("Notícia atualizada com sucesso!");
			return new ResponseEntity<PortalNoticiasDTO>(noticia,httpHeaders(),HttpStatus.OK);	
		}catch (Exception e) {
			noticia.setMensagemErro(e.getMessage());
			return new ResponseEntity<PortalNoticiasDTO>(noticia,httpHeaders(), HttpStatus.BAD_REQUEST);			
		}		
	}
	
	@GetMapping("/allTags")
	@PreAuthorize("hasAuthority('ROLE_LISTAR_TAGS') and #oauth2.hasScope('read')")
    public ResponseEntity<List<TagNoticiasDTO>>listAllTags() {
		List<TagNoticiasDTO> tagNoticiasDTO = new ArrayList<TagNoticiasDTO>();
		TagNoticiasDTO tagNoticiasDTO_=null;
		try{			
			List<TagNoticia> listaTags = tagNoticiaService.listAllTag();			
			for(TagNoticia temp: listaTags) {
				tagNoticiasDTO_=new TagNoticiasDTO();
				BeanUtils.copyProperties(temp,tagNoticiasDTO_);
				tagNoticiasDTO.add(tagNoticiasDTO_);
			}
			return new ResponseEntity<List<TagNoticiasDTO>>(tagNoticiasDTO,httpHeaders(),HttpStatus.OK);	
		}catch (Exception e) {
			return new ResponseEntity<List<TagNoticiasDTO>>(tagNoticiasDTO,httpHeaders(), HttpStatus.BAD_REQUEST);			
		}		
	}
	
	@GetMapping("/getById/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ATUALIZAR_NOTICIAS') and #oauth2.hasScope('read')")
    public ResponseEntity<PortalNoticiasDTO>getById(@PathVariable Long codigo) {
		PortalNoticiasDTO noticiaDTO = new PortalNoticiasDTO();
		TagNoticiasDTO tagNoticiasDTO=null;
		try{			
			Noticia noticia_ = noticiasService.getById(codigo);
			noticia_.setImagePath("https://s3.amazonaws.com/dashboard-sistemas-arquivos-geral/"+noticia_.getImagePath());
			BeanUtils.copyProperties(noticia_,noticiaDTO);
			noticiaDTO.setTags(new ArrayList<TagNoticiasDTO>());
			
			if(noticia_.getRelTagNoticia()!=null) {				
				for(RelTagNoticia temp: noticia_.getRelTagNoticia()) {
					tagNoticiasDTO = new TagNoticiasDTO();										
					BeanUtils.copyProperties(temp.getTagnoticia(),tagNoticiasDTO);					
					noticiaDTO.getTags().add(tagNoticiasDTO);					
				}				
			}			
			noticiaDTO.setMensagemSucesso("Noticia obtida com sucesso!");
			return new ResponseEntity<PortalNoticiasDTO>(noticiaDTO,httpHeaders(),HttpStatus.OK);	
		}catch (Exception e) {
			noticiaDTO.setMensagemErro(e.getMessage());
			return new ResponseEntity<PortalNoticiasDTO>(noticiaDTO,httpHeaders(), HttpStatus.BAD_REQUEST);			
		}		
	}

}
