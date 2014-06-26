package br.com.iondax.controller.utils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@ManagedBean(name = "utils")
@SessionScoped
public class Utilidades {

	public static List<SelectItem> getCarregaComboCarteirasBoleto(
			Integer bancoSelecionado) {
		List<SelectItem> lista = new ArrayList<SelectItem>();

		switch (bancoSelecionado) {
		case 1:
			lista.add(new SelectItem("16", "16 - Nosso n�mero 5 dig�tos"));
			lista.add(new SelectItem("18", "18 - Nosso n�mero 11 dig�tos"));
			break;
		case 33:
			lista.add(new SelectItem("COB", "COB"));
			lista.add(new SelectItem("CSR", "CSR"));
			break;
		case 104:
			lista.add(new SelectItem("CS", "CS"));
			lista.add(new SelectItem("SR", "SR"));
			lista.add(new SelectItem("SR14", "SR-14"));
			break;
		}

		return lista;
	}

	public static List<SelectItem> getComboBancos() {
		List<SelectItem> lista = new ArrayList<SelectItem>();

		lista.add(new SelectItem(1, "001 - Banco do Brasil"));
		lista.add(new SelectItem(33, "033 - Santander"));
		lista.add(new SelectItem(104, "104 - Caixa Econ�mica Federeal"));

		return lista;
	}

	public List<SelectItem> getComboUfs() {
		List<SelectItem> lista = new ArrayList<SelectItem>();

		// Centro Oeste
		lista.add(new SelectItem("Goi�s", "GO"));
		lista.add(new SelectItem("Mato Grosso", "MT"));
		lista.add(new SelectItem("Mato Grosso do Sul", "MS"));
		lista.add(new SelectItem("Distrito Federal", "DF"));

		// Norte
		lista.add(new SelectItem("Amazonas", "AM"));
		lista.add(new SelectItem("Acre", "AC"));
		lista.add(new SelectItem("Rond�nia", "RO"));
		lista.add(new SelectItem("Roraima", "RR"));
		lista.add(new SelectItem("Amap�", "AP"));
		lista.add(new SelectItem("Tocantins", "TO"));
		lista.add(new SelectItem("Par�", "PA"));

		// Nordeste
		lista.add(new SelectItem("Maranh�o", "MA"));
		lista.add(new SelectItem("Piau�", "PI"));
		lista.add(new SelectItem("Cear�", "CE"));
		lista.add(new SelectItem("Rio Grande do Norte", "RN"));
		lista.add(new SelectItem("Para�ba", "PB"));
		lista.add(new SelectItem("Pernambuco", "PE"));
		lista.add(new SelectItem("Sergipe", "SE"));
		lista.add(new SelectItem("Alagoas", "AL"));
		lista.add(new SelectItem("Bahia", "BA"));

		// Sudeste
		lista.add(new SelectItem("S�o Paulo", "SP"));
		lista.add(new SelectItem("Minas Gerais", "MG"));
		lista.add(new SelectItem("Rio de Janeiro", "RJ"));
		lista.add(new SelectItem("Espir�to Santo", "ES"));

		// Sul
		lista.add(new SelectItem("Paran�", "PR"));
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
