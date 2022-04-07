package service.aplication.dto.bolao;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import service.aplication.enumeration.StatusPartidasEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidaGoogleDTO implements Serializable{
	
	private static final long serialVersionUID = 1563648995036816037L;
	
	private StatusPartidasEnum statusPartida;
	private String tempoPartida;
	
	private String nomeEquipeCasa;
	private String urlLogoEquipeCasa;
	private Integer placarEquipeCasa;
	private String golsEquipeCasa;
	private Integer placarEstendidoEquipeCasa;
	
	private String nomeEquipeVisitante;
	private String urlLogoEquipeVisitante;
	private Integer placarEquipeVisitante;
	private String golsEquipeVisitante;
	private Integer placarEstendidoEquipeVisitante;
	
}
