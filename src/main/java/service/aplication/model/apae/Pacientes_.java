package service.aplication.model.apae;

import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pacientes.class)
public class Pacientes_ {
	
	public static volatile SingularAttribute<Pacientes, String> nome;
	public static volatile SingularAttribute<Pacientes, String> numeroPaciente;
	public static volatile SingularAttribute<Pacientes, LocalDateTime> dataExclusao;

}
