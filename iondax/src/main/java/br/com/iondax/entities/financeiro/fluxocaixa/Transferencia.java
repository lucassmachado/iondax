package br.com.iondax.entities.financeiro.fluxocaixa;

import java.io.Serializable;

import br.com.iondax.entities.financeiro.fluxocaixa.categorias.CategoriasFluxoCaixa;

public class Transferencia extends CategoriasFluxoCaixa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5700779903265758047L;

	private String categoria;
	private String nome;

	public Transferencia() {
		super();
	}

	public Transferencia(String categoria, String nome) {
		super();
		this.categoria = categoria;
		this.nome = nome;
	}

	public Transferencia(Transferencia t) {
		super();
		this.categoria = t.getCategoria();
		this.nome = t.getNome();
	}

	public String getCategoria() {
		return categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
