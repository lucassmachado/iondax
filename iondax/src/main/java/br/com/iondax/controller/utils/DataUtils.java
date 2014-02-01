package br.com.iondax.controller.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
	// RG
	public static String dataToString(Object param) {

		Date dateParam = (Date) param;

		SimpleDateFormat formatBra = new SimpleDateFormat("dd/MM/yyyy");
		
		String data = formatBra.format(dateParam);

		return data;
	}

	public static Date dataToDate(Object value) {
		Date data = null;
		String stringData = value.toString();
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
		try{
			data = format.parse(stringData);
		}catch(ParseException e){
			System.out.println("Erro de conversão de String para Data");
		}
		
		return data;
	}
}
