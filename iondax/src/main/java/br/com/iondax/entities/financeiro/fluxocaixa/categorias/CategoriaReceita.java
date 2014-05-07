package br.com.iondax.entities.financeiro.fluxocaixa.categorias;

import java.io.Serializable;

public class CategoriaReceita extends CategoriasFluxoCaixa implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2964296165075771253L;

	private String nome;

	public CategoriaReceita() {
		super();
	}

	public CategoriaReceita(CategoriaReceita cr) {
		super();
		this.nome = cr.getNome();
	}

	public CategoriaReceita(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
