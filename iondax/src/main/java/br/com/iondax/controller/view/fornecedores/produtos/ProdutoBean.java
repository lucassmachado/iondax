package br.com.iondax.controller.view.fornecedores.produtos;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "produtoBean")
@SessionScoped
public class ProdutoBean {

	// Chama Tela de consulta de Produtos
	public String carregaTelaConsultaProdutos() {
		return "/content/fornecedores/produtos/cadastro/conProdutos.jsf?faces-redirect=true";
	}

	// Chama Tela de Inclusão de Produtos
	public String carregaTelaInclusaoProdutos() {
		return "/content/fornecedores/produtos/cadastro/incProdutos.jsf?faces-redirect=true";
	}

}
