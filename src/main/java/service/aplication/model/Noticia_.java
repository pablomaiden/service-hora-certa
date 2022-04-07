package service.aplication.model;

import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Noticia.class)
public abstract class Noticia_ {

	public static volatile SingularAttribute<Noticia, Long> codigo;
	public static volatile SingularAttribute<Noticia, String> titulo;
	public static volatile SingularAttribute<Noticia, String> noticia;
	public static volatile SingularAttribute<Noticia, LocalDateTime> dataNoticia;		

}