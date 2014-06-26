package br.com.iondax.controller.utils;

import java.math.BigDecimal;

public class DecimalUtils {
	public static String format(BigDecimal valor){
		
		boolean eNegativo = false;
		
		String valorS = valor.toString().replace(".", ",");
		
		if(valorS.contains("-")){
			eNegativo = true;
			valorS = valorS.replace("-", "");
		}
		
		
		//tamanho da parte inteira:
		int tamanho = valorS.lastIndexOf(",");
		
		while(tamanho >3){
			valorS = valorS.substring(0,tamanho-3)+"."+valorS.substring(tamanho-3,valorS.length());
			tamanho = tamanho-3;
		}
		
		if(eNegativo){
			return "-"+valorS;
		}
		
		return valorS;
	}
	//-23456789,00
	public static BigDecimal convert(String valor){
		
		String valorS = valor.toString().replace(".","");
		valorS = valorS.toString().replace(",", ".");
		
		return new BigDecimal(valorS);
	}
}
