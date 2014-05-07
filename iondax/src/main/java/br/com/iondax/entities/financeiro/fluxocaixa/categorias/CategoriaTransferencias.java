package br.com.iondax.entities.financeiro.fluxocaixa.categorias;

import java.io.Serializable;

public class CategoriaTransferencias extends CategoriasFluxoCaixa implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6657154247535984482L;

	private String nome;

	public CategoriaTransferencias() {
		super();
	}

	public CategoriaTransferencias(CategoriaTransferencias cr) {
		super();
		this.nome = cr.getNome();
	}

	public CategoriaTransferencias(String nome) {
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
