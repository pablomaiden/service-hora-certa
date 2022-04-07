package service.aplication.repository.noticia;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import service.aplication.model.Noticia;
import service.aplication.model.Noticia_;
import service.aplication.repository.filter.NoticiaFilter;

public class NoticiasRepositoryImpl implements NoticiasRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Noticia> filtrar(NoticiaFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Noticia> criteria = builder.createQuery(Noticia.class);
		Root<Noticia> root = criteria.from(Noticia.class);
		
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.orderBy(builder.asc(root.get(Noticia_.dataNoticia)));
		
		TypedQuery<Noticia> query = manager.createQuery(criteria);		
		adicionarRestricoesDePaginacao(query, pageable);
		
		
		
		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
	}

	private Predicate[] criarRestricoes(NoticiaFilter noticiaFilter, CriteriaBuilder builder,
			Root<Noticia> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(noticiaFilter.getTitulo())) {
			predicates.add(builder.like(builder.lower(root.get(Noticia_.titulo)), "%" + noticiaFilter.getTitulo().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(noticiaFilter.getDataInicio())) {			
			predicates.add(builder.greaterThan(root.get(Noticia_.dataNoticia),noticiaFilter.getDataInicio()));
		}
		
		if (!StringUtils.isEmpty(noticiaFilter.getDataFim())) {			
			predicates.add(builder.lessThanOrEqualTo(root.get(Noticia_.dataNoticia),noticiaFilter.getDataFim()));
		}		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
				
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(NoticiaFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Noticia> root = criteria.from(Noticia.class);
		
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
	
	
	public List<Noticia> noticiaRelacionadasPorTag(Long id){		
		if(id==2100) {
			return optionTwo();			
		}else if(id==2200){
			return optionThre();			
		}else if(id==2300){
			return optionFour();			
		}else {
			return optionOne();
		}
	}
		
	private List<Noticia> optionOne(){
		List<Noticia> noticias_ = manager.createQuery(" from Noticia noticia where noticia.id in :id ",Noticia.class).setParameter("id",getIdsNoticiasRelacionadas()).getResultList();						
		return noticias_;
	}
	
    private List<Noticia> optionTwo(){
    	    List<Noticia> dtos  = new ArrayList<Noticia>();   
    	    
			dtos.addAll(manager.createQuery(" select noticia from Noticia noticia where noticia.id not in :ids and noticia.id not in :id "
					                      + " order by noticia.dataNoticia desc ",Noticia.class)
					           .setParameter("ids",getIdsNoticiasRelacionadas())
					           .setParameter("id",getIdUltimaNoticia())
					           .setMaxResults(4).getResultList());	
		    return dtos;
	}
    
    private List<Noticia> optionThre(){
	    List<Noticia> dtos  = new ArrayList<Noticia>();    	
		dtos.addAll(manager.createQuery(" select noticia from Noticia noticia order by noticia.dataNoticia asc",Noticia.class).setMaxResults(4).getResultList());	
	dtos.remove(0);
	return dtos;
   }
    
    private List<Noticia> optionFour(){
	    List<Noticia> dtos  = new ArrayList<Noticia>();    	
		dtos.addAll(manager.createQuery(" select noticia from Noticia noticia                "
				                      + " inner join fetch noticia.relTagNoticia relTag      "
				                      + " inner join fetch relTag.tagnoticia tagNot          "
				                      + " where tagNot.id=7 order by noticia.dataNoticia asc ",Noticia.class).setMaxResults(4).getResultList());
	return dtos;
    }    
    
    @SuppressWarnings("unchecked")
    private List<Long> getIdsNoticiasRelacionadas(){    	
    	List<BigInteger> ids = new ArrayList<>();
		List<Long> ids_      = new ArrayList<>();				
		ids = manager.createNativeQuery("select (noticia.id) from portalnoticias.noticia noticia" + 
				" inner join portalnoticias.rel_tag reltag on noticia.id=reltag.noticia_fk" + 
				" where" + 
				" noticia.id <> (select (noticia.id) as id from portalnoticias.noticia "+ 
				" order by noticia.data_noticia desc limit 1) "+ 
				" and reltag.tag_fk in(select (retag.tag_fk) from portalnoticias.rel_tag retag "+ 
				" where " + 
				" retag.noticia_fk in(select (noticia.id) as id from portalnoticias.noticia "+ 
				" order by noticia.data_noticia desc limit 1)) "+ 
				" order by noticia.id desc limit 4").getResultList();		
		for(BigInteger id_: ids) {
			ids_.add(new Long(id_.longValue()));
		}    	
    	return ids_;
    }
    
    @SuppressWarnings("unchecked")
    private Long getIdUltimaNoticia(){    				
    	List<BigInteger> ids = new ArrayList<>(); 
    	Long id_ = null;
    	ids = manager.createNativeQuery("select (noticia.id) as id from portalnoticias.noticia order by noticia.data_noticia desc limit 1 ").getResultList();
    	
    	for(BigInteger id : ids) {
    		id_ = new Long(id.longValue());    		
    	}
    	return id_;
    }

}
