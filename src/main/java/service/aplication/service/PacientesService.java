package service.aplication.service;

import java.text.ParseException;
import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import service.aplication.dto.PacientesDTO;
import service.aplication.model.apae.Pacientes;
import service.aplication.model.geral.PessoaFisica;
import service.aplication.repository.PacientesRepository;
import service.aplication.repository.PessoaFisicaRepository;
import service.aplication.repository.filter.PacientesFilter;

@Service
public class PacientesService {
	
	@Autowired
	private PacientesRepository pacientesRepository;
	
	@Autowired
	private PessoaFisicaRepository pessoaFisicaRepository;
		
	public Page<Pacientes> filtrar(PacientesFilter filter, Pageable pageable){
		return pacientesRepository.filtrar(filter, pageable);				
	}
	
	public void save(PacientesDTO pacientesDTO) {		
		Pacientes pacientes       = new Pacientes();
		PessoaFisica pessoaFisica = new PessoaFisica();
		ModelMapper modelMapper   = new ModelMapper();
		
		pacientes    = modelMapper.map(pacientesDTO, Pacientes.class);	
		pessoaFisica = modelMapper.map(pacientesDTO, PessoaFisica.class);	
		pacientes.setPessoaFisica(pessoaFisica);		
		//pessoaFisicaRepository.save(pessoaFisica);
		pacientesRepository.save(pacientes);
	}
	
	
	public void update(PacientesDTO pacientesDTO) {		
		Pacientes pacientes       = new Pacientes();
				
		pacientes    = pacientesRepository.getById(pacientesDTO.getId());		
		pacientes.setNumeroPaciente(pacientesDTO.getNumeroPaciente());
		pacientes.setResultadoExame(pacientesDTO.getResultadoExame());
		pacientes.getPessoaFisica().setDataNascimento(pacientesDTO.getDataNascimento());
		pacientes.getPessoaFisica().setNome(pacientesDTO.getNome());
		pacientes.getPessoaFisica().setFoto(pacientesDTO.getFoto());		
		pacientesRepository.save(pacientes);		
	}
	
	public PacientesDTO byIdPaciente(Long id) throws ParseException {
		PacientesDTO dto=null;
		ModelMapper modelMapper   = new ModelMapper();
		Pacientes pacientes       = pacientesRepository.getById(id);		
		dto                       = modelMapper.map(pacientes,PacientesDTO.class);
		dto                       = modelMapper.map(pacientes.getPessoaFisica(),PacientesDTO.class);
		
		dto.setId(pacientes.getId());
		dto.setNumeroPaciente(pacientes.getNumeroPaciente());
		dto.setResultadoExame(pacientes.getResultadoExame());
		if(pacientes.getPessoaFisica().getFoto()!=null)
		   dto.setFoto("https://s3.amazonaws.com/dashboard-sistemas-arquivos-geral/"+pacientes.getPessoaFisica().getFoto());
		return dto;					
	}
	
	public void deletePaciente(Long id) {			
		pacientesRepository.deletePaciente(id,LocalDateTime.now());						
	}

}
