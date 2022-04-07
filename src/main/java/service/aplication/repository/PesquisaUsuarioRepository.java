package service.aplication.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import service.aplication.model.PesquisaUsuario;

public interface PesquisaUsuarioRepository extends JpaRepository<PesquisaUsuario, Long>{
	
	@Query("select pesqUser from PesquisaUsuario pesqUser where to_char(pesqUser.dataCadastro,'dd/MM/YYYY')=:data ")
    List<PesquisaUsuario> listarPesquisasDeUsuarioDoDia(@Param("data") String data);
		
}
