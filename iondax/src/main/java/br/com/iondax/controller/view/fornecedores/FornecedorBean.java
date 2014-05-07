package br.com.iondax.controller.view.fornecedores;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "fornecedorBean")
@SessionScoped
public class FornecedorBean {

	// Chama Tela de consulta de Fornecedores
	public String carregaTelaConsultaFornecedores() {
		return "/content/fornecedores/fornecedores/conFornecedores.jsf?faces-redirect=true";
	}

	// Chama Tela de Inclusão de Fornecedores
	public String carregaTelaInclusaoFornecedores() {
		return "/content/fornecedores/fornecedores/incFornecedores.jsf?faces-redirect=true";
	}
}
