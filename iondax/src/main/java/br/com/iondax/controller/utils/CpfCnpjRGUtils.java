package br.com.iondax.controller.utils;

public class CpfCnpjRGUtils {
	
	//RG
	public static String rgToString(Object param){
		
		String parametro = param.toString();
		
		parametro = parametro.substring(0,2)+"."+
					parametro.substring(2,5)+"."+
					parametro.substring(5,8)+"-"+
					parametro.substring(8,parametro.length());
		
		return parametro ;
	}
	public static Integer rgToInteger(String param){
		param = param.replace(".","");
		return Integer.parseInt(param);
	}
	
	//CPF
	public static String cpfToString(Object param){
		
		String parametro = param.toString();
		
		parametro = parametro.substring(0,3)+"."+
				parametro.substring(3,6)+"."+
				parametro.substring(6,9)+"-"+
				parametro.substring(9,parametro.length());
		
		return parametro ;
	}
	public static Long cpfToLong(String param){
		param = param.replace(".","");
		return Long.parseLong(param);
	}
	
}
