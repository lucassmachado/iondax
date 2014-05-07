package br.com.iondax.entities.financeiro.fluxocaixa.categorias;

import java.io.Serializable;
import java.util.List;

public class CategoriasFluxoCaixa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2964296165075771253L;

	private List<? extends CategoriasFluxoCaixa> listaNomesCategorias;
	private String tipo;

	public CategoriasFluxoCaixa() {
		super();
	}

	public CategoriasFluxoCaixa(String tipo,
			List<? extends CategoriasFluxoCaixa> listaNomesCategorias) {
		super();
		this.tipo = tipo;
		this.listaNomesCategorias = listaNomesCategorias;
	}

	public List<? extends CategoriasFluxoCaixa> getListaNomesCategorias() {
		return listaNomesCategorias;
	}

	public String getTipo() {
		return tipo;
	}

	public void setListaNomesCategorias(
			List<? extends CategoriasFluxoCaixa> listaNomesCategorias) {
		this.listaNomesCategorias = listaNomesCategorias;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
