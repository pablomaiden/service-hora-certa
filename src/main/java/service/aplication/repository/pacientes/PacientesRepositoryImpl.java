package service.aplication.repository.pacientes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import service.aplication.model.apae.Pacientes;
import service.aplication.model.apae.Pacientes_;
import service.aplication.repository.filter.PacientesFilter;

public class PacientesRepositoryImpl implements PacientesRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Pacientes> filtrar(PacientesFilter filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Pacientes> criteria = builder.createQuery(Pacientes.class);
		Root<Pacientes> root = criteria.from(Pacientes.class);
						
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		
		//criteria.orderBy(builder.asc(root.get(Pacientes_.nome)));
		
		TypedQuery<Pacientes> query = manager.createQuery(criteria);		
		adicionarRestricoesDePaginacao(query, pageable);		
		
		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}

	private Predicate[] criarRestricoes(PacientesFilter filter, CriteriaBuilder builder,Root<Pacientes> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		root.join("pessoaFisica",JoinType.INNER);
		//Path<Integer> pessoaFisica = root.join("pessoaFisica").<Integer> get("id");
		//Predicate idBaseDados = builder.equal(baseDados,1);
        //predicates.add(idBaseDados);
		
        if(filter.getNome()!=null) {
           //predicates.add(builder.like(builder.lower(root.get(Pacientes_.nome)), "%"+filter.getNome().toLowerCase()+"%"));        	
        }
        
        if(filter.getNumeroPaciente()!=null) {
           predicates.add(builder.equal(root.get(Pacientes_.numeroPaciente),filter.getNumeroPaciente()));        	
         }
        
        predicates.add(builder.isNull(root.get(Pacientes_.dataExclusao)));
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
				
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(PacientesFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Pacientes> root = criteria.from(Pacientes.class);
		
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
