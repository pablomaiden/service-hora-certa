package service.aplication.resource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.aplication.dto.PacientesDTO;
import service.aplication.dto.TabelaNutricionalAPAEDTO;
import service.aplication.model.TabelaNutricional;
import service.aplication.model.apae.Pacientes;
import service.aplication.repository.AlimentoRepository;
import service.aplication.repository.filter.PacientesFilter;
import service.aplication.repository.filter.TabelaNutricionalFilter;
import service.aplication.service.PacientesService;
import service.aplication.storage.S3;

@RestController
@RequestMapping(value="/dashboardapae")
public class DashBoardAPAEResource extends Abstract {
	
	@Autowired
	AlimentoRepository informacaoNutricionalRepository;
	
	@Autowired
	PacientesService pacientesService;
	
	@Autowired
	private S3 s3;
	
	@PutMapping   
	//@PreAuthorize("hasAuthority('ROLE_UPDATE_TABELA_NUTRICIONAL_APAE') and #oauth2.hasScope('write')")
	public ResponseEntity<TabelaNutricionalAPAEDTO>update(@RequestBody TabelaNutricionalAPAEDTO tbNutricional) {		
		TabelaNutricionalAPAEDTO dto = new TabelaNutricionalAPAEDTO();		
		try {
			informacaoNutricionalRepository.update(tbNutricional.getFenilalanina(),tbNutricional.getId());
		return new ResponseEntity<TabelaNutricionalAPAEDTO>(dto,httpHeaders(),HttpStatus.CREATED);	
	}catch (Exception e) {				
		dto.setMensagemErro(e.getMessage());
		return new ResponseEntity<TabelaNutricionalAPAEDTO>(dto,httpHeaders(), HttpStatus.BAD_REQUEST);
	   }		
	}
	
	@GetMapping("/pesquisar")
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TABELA_NUTRICIONAL_APAE') and #oauth2.hasScope('read')")
    public Page<TabelaNutricional>pesquisar(TabelaNutricionalFilter filter,Pageable pageable) {
		Page<TabelaNutricional> lista = informacaoNutricionalRepository.filtrar(filter, pageable);
		return lista;
	}
	
	@GetMapping("/getById/{codigo}")
	//@PreAuthorize("hasAuthority('ROLE_UPDATE_TABELA_NUTRICIONAL_APAE') and #oauth2.hasScope('read')")
    public ResponseEntity<TabelaNutricionalAPAEDTO>getById(@PathVariable Long codigo) {		
		TabelaNutricionalAPAEDTO tabelaNutricionalAPAEDTO = new TabelaNutricionalAPAEDTO();		
		try{			
			TabelaNutricional tabelaNutricional_ = informacaoNutricionalRepository.getOne(codigo);			
			ModelMapper modelMapper = new ModelMapper();
			tabelaNutricionalAPAEDTO = modelMapper.map(tabelaNutricional_, TabelaNutricionalAPAEDTO.class);					
			tabelaNutricionalAPAEDTO.setMensagemSucesso("Noticia obtida com sucesso!");
			return new ResponseEntity<TabelaNutricionalAPAEDTO>(tabelaNutricionalAPAEDTO,httpHeaders(),HttpStatus.OK);	
		}catch (Exception e) {
			tabelaNutricionalAPAEDTO.setMensagemErro(e.getMessage());
			return new ResponseEntity<TabelaNutricionalAPAEDTO>(tabelaNutricionalAPAEDTO,httpHeaders(), HttpStatus.BAD_REQUEST);			
		}		
	}
	
	@PostMapping("/salvarPacientes")
	public ResponseEntity<PacientesDTO>salvarPacientes(@RequestBody PacientesDTO pacientesDTO){		
		try{
			
			String foto = pacientesDTO.getFoto().replaceAll("https://s3.amazonaws.com/dashboard-sistemas-arquivos-geral/","");
			
			if (StringUtils.hasText(foto)) {
				s3.salvar(pacientesDTO.getFoto());
			}
			
			pacientesService.save(pacientesDTO);
			return new ResponseEntity<PacientesDTO>(pacientesDTO,httpHeaders(),HttpStatus.OK);	
		}catch (Exception e) {			
			return new ResponseEntity<PacientesDTO>(pacientesDTO,httpHeaders(), HttpStatus.BAD_REQUEST);			
		}		
	}
	
	@PutMapping("/updatePacientes")
	public ResponseEntity<PacientesDTO>updatePacientes(@RequestBody PacientesDTO pacientesDTO){		
		try{			
			String foto = pacientesDTO.getFoto().replaceAll("https://s3.amazonaws.com/dashboard-sistemas-arquivos-geral/","");
			
			if (StringUtils.hasText(foto)) {
				s3.salvar(pacientesDTO.getFoto());
			}
			
			pacientesService.update(pacientesDTO);
			return new ResponseEntity<PacientesDTO>(pacientesDTO,httpHeaders(),HttpStatus.OK);	
		}catch (Exception e) {			
			return new ResponseEntity<PacientesDTO>(pacientesDTO,httpHeaders(), HttpStatus.BAD_REQUEST);			
		}		
	}
	
	@GetMapping("/pesquisarPacientes")
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TABELA_NUTRICIONAL_APAE') and #oauth2.hasScope('read')")
    public Page<Pacientes>pesquisarPacientes(PacientesFilter filter,Pageable pageable) {
		Page<Pacientes> lista = pacientesService.filtrar(filter, pageable);
		return lista;
	}
	
	@GetMapping("/byIdPaciente/{id}")
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TABELA_NUTRICIONAL_APAE') and #oauth2.hasScope('read')")
    public ResponseEntity<PacientesDTO>byIdPaciente(@PathVariable Long id) {		
		try {
			return new ResponseEntity<PacientesDTO>(pacientesService.byIdPaciente(id),httpHeaders(),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<PacientesDTO>(httpHeaders(), HttpStatus.BAD_REQUEST);			
		}		
	}
	
	
	@DeleteMapping("/deletePaciente/{id}")
	//@PreAuthorize("hasAuthority('ROLE_PESQUISAR_TABELA_NUTRICIONAL_APAE') and #oauth2.hasScope('read')")
    public ResponseEntity<PacientesDTO>deletePaciente(@PathVariable Long id){
		PacientesDTO dto = new PacientesDTO();
		try {
			pacientesService.deletePaciente(id);
			return new ResponseEntity<PacientesDTO>(dto,httpHeaders(),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<PacientesDTO>(httpHeaders(), HttpStatus.BAD_REQUEST);			
		}		
	}
	
}

