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
		
		int nroRepeticoes = (valorS.indexOf(",")/3) - 1;
		//12.500,00
		
		for(int i=nroRepeticoes; i>0 ;i--){
			valorS= valorS.substring(0,valorS.indexOf(",")-(i*3))+"."+valorS.substring(valorS.indexOf(",")-(i*3),valorS.length());
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
