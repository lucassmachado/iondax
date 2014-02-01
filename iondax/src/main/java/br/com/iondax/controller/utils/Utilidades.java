package br.com.iondax.controller.utils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name="utils")
@SessionScoped
public class Utilidades {
	
	public List<SelectItem> getComboUfs(){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		
		//Centro Oeste
		lista.add(new SelectItem("Goiás","GO"));
		lista.add(new SelectItem("Mato Grosso","MT"));
		lista.add(new SelectItem("Mato Grosso do Sul","MS"));
		lista.add(new SelectItem("Distrito Federal","DF"));
		
		//Norte
		lista.add(new SelectItem("Amazonas","AM"));
		lista.add(new SelectItem("Acre","AC"));
		lista.add(new SelectItem("Rondônia","RO"));
		lista.add(new SelectItem("Roraima","RR"));
		lista.add(new SelectItem("Amapá","AP"));
		lista.add(new SelectItem("Tocantins","TO"));
		lista.add(new SelectItem("Pará","PA"));
		
		//Nordeste
		lista.add(new SelectItem("Maranhão","MA"));
		lista.add(new SelectItem("Piauí","PI"));
		lista.add(new SelectItem("Ceará","CE"));
		lista.add(new SelectItem("Rio Grande do Norte","RN"));
		lista.add(new SelectItem("Paraíba","PB"));
		lista.add(new SelectItem("Pernambuco","PE"));
		lista.add(new SelectItem("Sergipe","SE"));
		lista.add(new SelectItem("Alagoas","AL"));
		lista.add(new SelectItem("Bahia","BA"));
		
		//Sudeste
		lista.add(new SelectItem("São Paulo","SP"));
		lista.add(new SelectItem("Minas Gerais","MG"));
		lista.add(new SelectItem("Rio de Janeiro","RJ"));
		lista.add(new SelectItem("Espiríto Santo","ES"));
		
		//Sul
		lista.add(new SelectItem("Paraná","PR"));
		lista.add(new SelectItem("Santa Catarina","SC"));
		lista.add(new SelectItem("Rio Grande do Sul","ES"));
		
		
		return lista;
	}
	
	public static List<SelectItem> getComboBancos(){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		
		lista.add(new SelectItem(1,"001 - Banco do Brasil")); 				
		lista.add(new SelectItem(4,"004 - Banco do norderste"));				
		lista.add(new SelectItem(21,"021 - Banestes"));						
		lista.add(new SelectItem(33,"033 - Santander"));						
		lista.add(new SelectItem(41,"041 - Barisul"));							
		lista.add(new SelectItem(104,"104 - Caixa Econômica Federeal")); 		
		lista.add(new SelectItem(151,"151 - Nossa Caixa"));						
		lista.add(new SelectItem(237,"237 - Bradesco"));						
		lista.add(new SelectItem(341,"341 - Itaú"));							
		lista.add(new SelectItem(356,"356 - Banco Real"));						
		lista.add(new SelectItem(389,"389 - Mercantil do Brasil"));				
		lista.add(new SelectItem(399,"399 - HSBC")); 							
		lista.add(new SelectItem(409,"409 - Unibanco"));						
		lista.add(new SelectItem(422,"422 - Safra"));							
		lista.add(new SelectItem(453,"453 - Banco Rural"));						
		lista.add(new SelectItem(748,"748 - Sicredi"));							
		lista.add(new SelectItem(756,"756 - Bancoob - Banco Cooperativo"));		
		
		return lista;
	}
	
	public static List<SelectItem> getCarregaComboCarteirasBoleto(Integer bancoSelecionado){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		
		switch(bancoSelecionado){
		case 1 :
			lista.add(new SelectItem("16","16"));
			lista.add(new SelectItem("18","18"));
			break;
		case 4 :
			lista.add(new SelectItem("4","11"));
			lista.add(new SelectItem("41","16"));
			lista.add(new SelectItem("45","45"));
			lista.add(new SelectItem("46","46"));
			lista.add(new SelectItem("47","47"));
			lista.add(new SelectItem("48","48"));
			lista.add(new SelectItem("49","49"));
			lista.add(new SelectItem("50","50"));
			lista.add(new SelectItem("51","51"));
			lista.add(new SelectItem("52","52"));
			lista.add(new SelectItem("53","53"));
			lista.add(new SelectItem("54","54"));
			lista.add(new SelectItem("55","55"));
			lista.add(new SelectItem("57","57"));
			lista.add(new SelectItem("58","58"));
			lista.add(new SelectItem("59","59"));
			lista.add(new SelectItem("61","61"));
			lista.add(new SelectItem("63","63"));
			lista.add(new SelectItem("95","95"));
			lista.add(new SelectItem("97","97"));
			break;
		case 21:
			lista.add(new SelectItem("2","2"));
			lista.add(new SelectItem("3","3"));
			lista.add(new SelectItem("4","4"));
			lista.add(new SelectItem("5","5"));
			lista.add(new SelectItem("6","6"));
			lista.add(new SelectItem("7","7"));
			lista.add(new SelectItem("11","11"));
			lista.add(new SelectItem("13","13"));
			break;
		case 33:
			lista.add(new SelectItem("101","101"));
			lista.add(new SelectItem("102","102"));
			lista.add(new SelectItem("201","201"));
			lista.add(new SelectItem("COB","COB"));
			lista.add(new SelectItem("CSR","CSR"));
			lista.add(new SelectItem("ECR","ECR"));
			break;
		case 104:
			lista.add(new SelectItem("01","01"));
			lista.add(new SelectItem("02","02"));
			lista.add(new SelectItem("14","14"));
			lista.add(new SelectItem("24","24"));
			lista.add(new SelectItem("CR","CR"));
			lista.add(new SelectItem("CS","CS"));
			lista.add(new SelectItem("SR","SR"));
			break;
		case 151:
			lista.add(new SelectItem("9","9"));
			break;
		case 399:
			lista.add(new SelectItem("CNR","CNR "));
			lista.add(new SelectItem("CSB","CSB"));
			break;
		case 409:
			lista.add(new SelectItem("caucao","Caução"));
			lista.add(new SelectItem("direta","Direta"));
			lista.add(new SelectItem("especial","Especial"));
			lista.add(new SelectItem("escritural","Escritural"));
			lista.add(new SelectItem("simples","Simples"));
			lista.add(new SelectItem("simples-especial","Simples-Especial"));
			break;
		case 422:
			lista.add(new SelectItem("1","1"));
			lista.add(new SelectItem("2","2"));
			lista.add(new SelectItem("4","4"));
			lista.add(new SelectItem("6","6"));
			lista.add(new SelectItem("50","050"));
			lista.add(new SelectItem("60","060"));
			lista.add(new SelectItem("229","229"));
			break;
		}
		
		return lista;
	}
}
