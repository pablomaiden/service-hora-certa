package service.aplication.repository.alimento;

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
import org.springframework.util.StringUtils;
import service.aplication.model.TabelaNutricional;
import service.aplication.model.TabelaNutricional_;
import service.aplication.repository.filter.TabelaNutricionalFilter;

public class AlimentoRepositoryImpl implements AlimentoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<TabelaNutricional> filtrar(TabelaNutricionalFilter filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<TabelaNutricional> criteria = builder.createQuery(TabelaNutricional.class);
		Root<TabelaNutricional> root = criteria.from(TabelaNutricional.class);
						
		Predicate[] predicates = criarRestricoes(filter, builder, root);
		criteria.where(predicates);
		
		criteria.orderBy(builder.asc(root.get(TabelaNutricional_.descricaoAlimento)));
		
		TypedQuery<TabelaNutricional> query = manager.createQuery(criteria);		
		adicionarRestricoesDePaginacao(query, pageable);		
		
		return new PageImpl<>(query.getResultList(), pageable, total(filter));
	}

	private Predicate[] criarRestricoes(TabelaNutricionalFilter filter, CriteriaBuilder builder,Root<TabelaNutricional> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		root.join("baseDados",JoinType.INNER);
		Path<Integer> baseDados = root.join("baseDados").<Integer> get("id");
		Path<Integer> categoriaAlimentos = root.join("categoriaAlimentos").<Integer> get("id");
		
		Predicate idBaseDados = builder.equal(baseDados,1);
        predicates.add(idBaseDados);
        
        if(filter.getCategoriaAlimento()!=null) {
           Predicate idcategoriaAlimentos = builder.equal(categoriaAlimentos,filter.getCategoriaAlimento());
           predicates.add(idcategoriaAlimentos);        	
        }        
		
		if (!StringUtils.isEmpty(filter.getDescricaoAlimento())) {
			predicates.add(builder.like(builder.lower(root.get(TabelaNutricional_.descricaoAlimento)), "%" + filter.getDescricaoAlimento().toLowerCase() + "%"));
		}
		
		//predicates.add(root.fetch( "b", JoinType.INNER ));
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
				
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(TabelaNutricionalFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<TabelaNutricional> root = criteria.from(TabelaNutricional.class);
		
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
