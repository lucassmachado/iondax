package br.com.iondax.controller.view.fornecedores;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.iondax.entities.fornecedor.Fornecedor;

@ManagedBean(name = "fornecedorBean")
@SessionScoped
public class FornecedorBean {
	
	public Fornecedor fornecedor;
	
	// Chama Tela de consulta de Fornecedores
	public String carregaTelaConsultaFornecedores() {
		return "/content/fornecedores/fornecedores/conFornecedores.jsf?faces-redirect=true";
	}

	// Chama Tela de Inclusão de Fornecedores
	public String carregaTelaInclusaoFornecedores() {
		return "/content/fornecedores/fornecedores/incFornecedores.jsf?faces-redirect=true";
	}
	
	public String incluirFornecedores(){
		return "/index.jsf?faces-redirect=true";
	}
	
	//Getters e Setters
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
}
