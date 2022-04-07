package service.aplication.resource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import service.aplication.dto.InformacaoNutricionalDTO;
import service.aplication.dto.PesquisaTabelaNutricionalDTO;
import service.aplication.dto.TabelaMedidasDTO;
import service.aplication.enumeration.ValoresReferenciaEnum;
import service.aplication.model.PesquisaUsuario;
import service.aplication.model.TabelaMedidas;
import service.aplication.model.TabelaNutricional;
import service.aplication.repository.AlimentoRepository;
import service.aplication.repository.PesquisaUsuarioRepository;
import service.aplication.util.CalculosAlimentares;

@RestController
@RequestMapping(value="/informacaonutricional")
public class InformacaoNutricionalResource {
		
	@Autowired
	AlimentoRepository informacaoNutricionalRepository;
	
	@Autowired
	PesquisaUsuarioRepository pesquisaUsuarioRepository;
		
	@RequestMapping(value= "/consultarTabelaAlimentos/alimento/{alimento}",headers = "Accept=application/json", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
    public List<PesquisaTabelaNutricionalDTO> consultarTabelaAlimentos(@PathVariable("alimento") String alimento) {			
		try{
			//======Capturando os dados que os usuários estão digitando=====
			PesquisaUsuario usuario = new PesquisaUsuario();
			usuario.setCampoPesquisa(alimento);
			usuario.setDataCadastro(new Date());
			pesquisaUsuarioRepository.save(usuario);
			//=============fim=================
			
			List<TabelaNutricional> lista = informacaoNutricionalRepository.findLikeTabelaNutricional(alimento.toUpperCase());         
	        List<PesquisaTabelaNutricionalDTO> lstInformacaoNutricionalDTO = new ArrayList<PesquisaTabelaNutricionalDTO>();	        
	        if(lista!=null){	        	
	        	for(TabelaNutricional temp : lista){
		        	PesquisaTabelaNutricionalDTO   informacaoNutricionalDTO  = new PesquisaTabelaNutricionalDTO();
		        	informacaoNutricionalDTO.setId(temp.getId());
		        	informacaoNutricionalDTO.setAlimento(temp.getDescricaoAlimento());
		        	lstInformacaoNutricionalDTO.add(informacaoNutricionalDTO);	        	
		        }	        	
	        }                
            return lstInformacaoNutricionalDTO;
			
		}catch (Exception e) {
			System.out.print("Erro:"+e.getStackTrace());			
		}
		return null;        
    }
	
	@RequestMapping(value= "/getByOneAlimento/id/{id}",headers = "Accept=application/json", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
    public TabelaNutricional getByOneAlimento(@PathVariable("id") Long id) {		
		try{
			TabelaNutricional tabelaNutricional = informacaoNutricionalRepository.findById(id).get();
			return tabelaNutricional;		
		}catch (Exception e) {			
		}
		return null;	       
    }
	
	@RequestMapping(value= "/getByInformacaoAlimentar/id/{id}/medida/{medida}", headers = "Accept=application/json", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
    public InformacaoNutricionalDTO getByOneAlimentoCompleto(@PathVariable("id") Long id, @PathVariable("medida") int medida) {	              
	       InformacaoNutricionalDTO informacaoNutricionalDTO = new InformacaoNutricionalDTO();	
	       Double carboidrato,proteina,gorduraTotal,gorduraSaturada,fibraAlimentar,sodio;	       	       
	       try{
	    	   TabelaNutricional tabelaNutricional = informacaoNutricionalRepository.findById(id).get();
	    	   informacaoNutricionalDTO.setId(tabelaNutricional.getId());
	    	   informacaoNutricionalDTO.setAlimento(tabelaNutricional.getDescricaoAlimento());
	    	   
	    	   carboidrato     = Double.parseDouble(CalculosAlimentares.calcularElementos(tabelaNutricional.getCarboidratos()    ,medida).toString());
		       proteina        = Double.parseDouble(CalculosAlimentares.calcularElementos(tabelaNutricional.getProteina()        ,medida).toString());
		       gorduraTotal    = Double.parseDouble(CalculosAlimentares.calcularElementos(tabelaNutricional.getLipideos()        ,medida).toString());
		       gorduraSaturada = Double.parseDouble(CalculosAlimentares.calcularElementos(tabelaNutricional.getColesterol()      ,medida).toString());
		       fibraAlimentar  = Double.parseDouble(CalculosAlimentares.calcularElementos(tabelaNutricional.getFibra_alimentar() ,medida).toString());
		       sodio           = Double.parseDouble(CalculosAlimentares.calcularElementos(tabelaNutricional.getSodio()           ,medida).toString());
		       
		       informacaoNutricionalDTO.setKcal(CalculosAlimentares.calcularKcal(carboidrato,proteina,gorduraTotal));
		       informacaoNutricionalDTO.setCarboidrato(carboidrato);
		       informacaoNutricionalDTO.setProteina(proteina);
		       informacaoNutricionalDTO.setGorduraTotal(gorduraTotal);
		       informacaoNutricionalDTO.setGorduraSaturada(gorduraSaturada);
		       informacaoNutricionalDTO.setFibraAlimentar(fibraAlimentar);
		       informacaoNutricionalDTO.setSodio(sodio);
		              
		       //Valores Diários VD
		       BigDecimal vdKcal = CalculosAlimentares.calcularKcal(carboidrato,proteina,gorduraTotal);
		       informacaoNutricionalDTO.setVdKcal(CalculosAlimentares.calcularValorDiario(Double.parseDouble(vdKcal.toString()))                                                                 +"%");
		       informacaoNutricionalDTO.setVdCarboidrato(CalculosAlimentares.calcularVD(Double.parseDouble(carboidrato.toString()),ValoresReferenciaEnum.CARBOIDRATO).toString()                 +"%");
		       informacaoNutricionalDTO.setVdProteina(CalculosAlimentares.calcularVD(Double.parseDouble(proteina.toString()),ValoresReferenciaEnum.PROTEINA).toString()                          +"%");
		       informacaoNutricionalDTO.setVdGorduraTotal(CalculosAlimentares.calcularVD(Double.parseDouble(gorduraTotal.toString()),ValoresReferenciaEnum.GORDURAS_TOTAIS).toString()           +"%");
		       informacaoNutricionalDTO.setVdGorduraSaturada(CalculosAlimentares.calcularVD(Double.parseDouble(gorduraSaturada.toString()),ValoresReferenciaEnum.GORDURAS_SATURADAS).toString()  +"%");
		       informacaoNutricionalDTO.setVdFibraAlimentar(CalculosAlimentares.calcularVD(Double.parseDouble(fibraAlimentar.toString()),ValoresReferenciaEnum.FIBRA_ALIMENTAR).toString()       +"%");
		       informacaoNutricionalDTO.setVdSodio(CalculosAlimentares.calcularVD(Double.parseDouble(sodio.toString()),ValoresReferenciaEnum.SODIO).toString()                                   +"%");
		       
		       informacaoNutricionalDTO.setLstTabelaMedidasDTO(new ArrayList<TabelaMedidasDTO>());
		       	       
		       for(TabelaMedidas temp : tabelaNutricional.getLstTabelaMedidas()){
		    	   TabelaMedidasDTO tabela = new TabelaMedidasDTO();
		    	   tabela.setTipo(temp.getTipo());
		    	   tabela.setMedida(temp.getMedida());
		    	   tabela.setGramas(temp.getGramas());	    	   
		    	   informacaoNutricionalDTO.getLstTabelaMedidasDTO().add(tabela);	    	   
		       }		       
		       return informacaoNutricionalDTO;	    	   
	    	   
	       }catch (Exception e) {
	    	   System.out.print("Erro"+e.getStackTrace());
		   }	       	      	       	       
         return null;
    }	

}
