package br.com.iondax.entities.financeiro.fluxocaixa.categorias;

import java.io.Serializable;

public class CategoriaDespesas extends CategoriasFluxoCaixa implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1433262692789927920L;

	private String nome;

	public CategoriaDespesas() {
		super();
	}

	public CategoriaDespesas(CategoriaDespesas cd) {
		super();
		this.nome = cd.getNome();
	}

	public CategoriaDespesas(String nome) {
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
