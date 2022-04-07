package service.aplication.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import service.aplication.model.apae.Pacientes;
import service.aplication.repository.pacientes.PacientesRepositoryQuery;

public interface PacientesRepository extends JpaRepository<Pacientes, Long>, PacientesRepositoryQuery{
		
	@Modifying
	@Transactional
	@Query("UPDATE Pacientes SET dataExclusao = :data where id = :id")
	void deletePaciente(@Param("id") Long id, @Param("data") LocalDateTime data);
		
	@Query("SELECT p FROM Pacientes p inner join fetch p.pessoaFisica  WHERE p.id = :id")
	Pacientes getById(@Param("id") Long id);
	
	
}
