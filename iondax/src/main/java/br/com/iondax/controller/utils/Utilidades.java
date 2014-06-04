package br.com.iondax.controller.utils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.print.attribute.standard.Severity;

import org.hibernate.cfg.Environment;

@ManagedBean(name = "utils")
@SessionScoped
public class Utilidades {

	public static List<SelectItem> getCarregaComboCarteirasBoleto(
			Integer bancoSelecionado) {
		List<SelectItem> lista = new ArrayList<SelectItem>();

		switch (bancoSelecionado) {
		case 1:
			lista.add(new SelectItem("16", "16"));
			lista.add(new SelectItem("18", "18"));
			break;
		case 33:
			lista.add(new SelectItem("101", "101"));
			lista.add(new SelectItem("102", "102"));
			lista.add(new SelectItem("201", "201"));
			lista.add(new SelectItem("COB", "COB"));
			lista.add(new SelectItem("CSR", "CSR"));
			lista.add(new SelectItem("ECR", "ECR"));
			break;
		case 104:
			lista.add(new SelectItem("01", "01"));
			lista.add(new SelectItem("02", "02"));
			lista.add(new SelectItem("14", "14"));
			lista.add(new SelectItem("24", "24"));
			lista.add(new SelectItem("CR", "CR"));
			lista.add(new SelectItem("CS", "CS"));
			lista.add(new SelectItem("SR", "SR"));
			break;
		case 151:
			lista.add(new SelectItem("9", "9"));
			break;
		case 237:
			break;
		case 341:
			break;
		}

		return lista;
	}

	public static List<SelectItem> getComboBancos() {
		List<SelectItem> lista = new ArrayList<SelectItem>();

		lista.add(new SelectItem(1, "001 - Banco do Brasil"));
		lista.add(new SelectItem(33, "033 - Santander"));
		lista.add(new SelectItem(104, "104 - Caixa Econômica Federeal"));
		lista.add(new SelectItem(151, "151 - Nossa Caixa"));
		lista.add(new SelectItem(237, "237 - Bradesco"));
		lista.add(new SelectItem(341, "341 - Itaú"));

		return lista;
	}

	public List<SelectItem> getComboUfs() {
		List<SelectItem> lista = new ArrayList<SelectItem>();

		// Centro Oeste
		lista.add(new SelectItem("Goiás", "GO"));
		lista.add(new SelectItem("Mato Grosso", "MT"));
		lista.add(new SelectItem("Mato Grosso do Sul", "MS"));
		lista.add(new SelectItem("Distrito Federal", "DF"));

		// Norte
		lista.add(new SelectItem("Amazonas", "AM"));
		lista.add(new SelectItem("Acre", "AC"));
		lista.add(new SelectItem("Rondônia", "RO"));
		lista.add(new SelectItem("Roraima", "RR"));
		lista.add(new SelectItem("Amapá", "AP"));
		lista.add(new SelectItem("Tocantins", "TO"));
		lista.add(new SelectItem("Pará", "PA"));

		// Nordeste
		lista.add(new SelectItem("Maranhão", "MA"));
		lista.add(new SelectItem("Piauí", "PI"));
		lista.add(new SelectItem("Ceará", "CE"));
		lista.add(new SelectItem("Rio Grande do Norte", "RN"));
		lista.add(new SelectItem("Paraíba", "PB"));
		lista.add(new SelectItem("Pernambuco", "PE"));
		lista.add(new SelectItem("Sergipe", "SE"));
		lista.add(new SelectItem("Alagoas", "AL"));
		lista.add(new SelectItem("Bahia", "BA"));

		// Sudeste
		lista.add(new SelectItem("São Paulo", "SP"));
		lista.add(new SelectItem("Minas Gerais", "MG"));
		lista.add(new SelectItem("Rio de Janeiro", "RJ"));
		lista.add(new SelectItem("Espiríto Santo", "ES"));

		// Sul
		lista.add(new SelectItem("Paraná", "PR"));
		lista.add(new SelectItem("Santa Catarina", "SC"));
		lista.add(new SelectItem("Rio Grande do Sul", "ES"));

		return lista;
	}
	
	/**
	 * 
	 * @param mensagem
	 * @param tipo
	 * 		<br><strong> tipo pode ser "erro", "alerta", "sucesso"  </strong>
	 */
	public static void mensagemNaTela(String mensagem, String tipo){
		FacesContext context = FacesContext.getCurrentInstance();
		if(tipo.equals("sucesso") ){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,mensagem,null) );	
		}
		if(tipo.equals("erro") ){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,mensagem,null));
		}
		if(tipo.equals("alerta") ){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,mensagem,null));
		}
	}
	
}
