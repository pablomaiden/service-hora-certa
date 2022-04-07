package service.aplication.repository.alimento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import service.aplication.model.TabelaNutricional;
import service.aplication.repository.filter.TabelaNutricionalFilter;

public interface AlimentoRepositoryQuery {	
	public Page<TabelaNutricional> filtrar(TabelaNutricionalFilter filter,Pageable pageable);
}
