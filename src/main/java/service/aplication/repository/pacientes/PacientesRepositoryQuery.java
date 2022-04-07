package service.aplication.repository.pacientes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import service.aplication.model.apae.Pacientes;
import service.aplication.repository.filter.PacientesFilter;

public interface PacientesRepositoryQuery {	
	public Page<Pacientes> filtrar(PacientesFilter filter,Pageable pageable);
}
