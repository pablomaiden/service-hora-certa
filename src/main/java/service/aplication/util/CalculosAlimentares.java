package service.aplication.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import service.aplication.enumeration.ValoresReferenciaEnum;

public class CalculosAlimentares {
		
	public static BigDecimal calcularKcal(Double carboidrato, Double proteina, Double gordura){		
		//1 grama de carboidrato = 4kcal
		//1 grama de proteina    = 4kcal
		//1 grama de lipiedio    = 9kcal
		//4 (gramas de carboidrato)+4 (gramas de proteina)+9(gramas de gorduras)  = Valor Energético do alimento em kcal
		//17(gramas de carboidrato)+17(gramas de proteina)+37(gramas de gorduras) = Valor Energético do alimento em kj		
		if(gordura==null){
		   gordura=00D;	
		}
		
		if(proteina==null){
		   proteina=00D;	
		}
		
		if(carboidrato==null){
		   carboidrato=00D;	
		}
		
		Double somaCarboidrato   = (carboidrato*4);
		Double somaProteina      = (proteina*4);
		Double somaGordura       = (gordura*9);	
		Double resultadoCalorias = (somaCarboidrato+somaProteina+somaGordura);		
		return new BigDecimal(resultadoCalorias).setScale(2,RoundingMode.UP);		
	}
	
	public Double calcularKj(Double carboidrato, Double proteina, Double gordura){		
		//1 grama de carboidrato = 4kcal
		//1 grama de proteina    = 4kcal
		//1 grama de lipiedio    = 9kcal
		//4 (gramas de carboidrato)+4 (gramas de proteina)+9(gramas de gorduras)  = Valor Energético do alimento em kcal
		//17(gramas de carboidrato)+17(gramas de proteina)+37(gramas de gorduras) = Valor Energético do alimento em kj		
		Double somaCarboidrato   = (carboidrato*17);
		Double somaProteina      = (proteina*17);
		Double somaLipidios      = (gordura*37);	
		Double resultadoCalorias = (somaCarboidrato+somaProteina+somaLipidios);	
		return resultadoCalorias;		
	}	
	
	//Calculo do valor diário da caloria do alimento
	public static BigDecimal calcularValorDiario(Double valorEnergico){		
		//            |
		//  2000kcal  | 100%
		//  __________|___________
		//  74,86kcal | 3,7
		//            |
		//            |
		//
		// 2000 kcal é valor de referência
		// 74,86 kcal é a caloria total do alimento
		// resultado depois da multiplicação cruzada é 3,7		
		Integer kcal    = 2000;
		Integer percent = 100;
		Double  resultado = (percent*valorEnergico)/kcal;		
		BigDecimal arredondado = new BigDecimal(resultado).setScale(1,RoundingMode.HALF_EVEN);		
		return arredondado;
	}
	
	public static BigDecimal calcularElementos(Double elementosEmGramas, int quantidadeGramas){		
		if(elementosEmGramas==null){
		   elementosEmGramas=00D;
		}
		
		Double referenciaCentessimal = 100D;		
		Double resultado = (elementosEmGramas*quantidadeGramas)/referenciaCentessimal;	
		return new BigDecimal(resultado).setScale(2,RoundingMode.HALF_EVEN);		
	}
	
	public static BigDecimal calcularVD(Double carboidratoGramas, ValoresReferenciaEnum referencia){		
		if(carboidratoGramas==null){
		   carboidratoGramas=00D;
		}
		
		Double percent = 100D;
		Double resultado = (carboidratoGramas*percent)/ValoresReferenciaEnum.getValorReferencia(referencia.getId());	
		BigDecimal arredondado = new BigDecimal(resultado).setScale(2,RoundingMode.UP);
		return arredondado;
	}	
	
	/*
	 * public static void main(String[] args){
	 * 
	 * BigDecimal carboidrato = calcularElementos(15.2D,20); BigDecimal proteina =
	 * calcularElementos(3.34D,20); BigDecimal gorduraTotal =
	 * calcularElementos(33.5D,20); BigDecimal gorduraSaturada =
	 * calcularElementos(29.7D,20); BigDecimal fibraAlimentar =
	 * calcularElementos(9.4D,20); BigDecimal sodio = calcularElementos(20D,20);
	 * 
	 * System.out.println("kcal             "+calcularKcal(3.04D,0.60D,6.70D)+" VD "
	 * +calcularValorDiario(74.86));
	 * System.out.println("Carboidrato      "+carboidrato
	 * +" VD "+calcularVD(Double.parseDouble(carboidrato.toString()),
	 * ValoresReferenciaEnum.CARBOIDRATO));
	 * System.out.println("Proteina         "+proteina
	 * +" VD "+calcularVD(Double.parseDouble(proteina.toString()),
	 * ValoresReferenciaEnum.PROTEINA));
	 * System.out.println("Gordura Total    "+gorduraTotal
	 * +" VD "+calcularVD(Double.parseDouble(gorduraTotal.toString()),
	 * ValoresReferenciaEnum.GORDURAS_TOTAIS));
	 * System.out.println("Gordura Saturada "+gorduraSaturada
	 * +" VD "+calcularVD(Double.parseDouble(gorduraSaturada.toString()),
	 * ValoresReferenciaEnum.GORDURAS_SATURADAS));
	 * System.out.println("Fibra Alimentar  "+fibraAlimentar
	 * +" VD "+calcularVD(Double.parseDouble(fibraAlimentar.toString()),
	 * ValoresReferenciaEnum.FIBRA_ALIMENTAR));
	 * System.out.println("Sódio            "+sodio
	 * +" VD "+calcularVD(Double.parseDouble(fibraAlimentar.toString()),
	 * ValoresReferenciaEnum.SODIO));
	 * 
	 * }
	 */

}
