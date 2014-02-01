package br.com.iondax.entities.financeiro.fluxocaixa.categorias;

import java.io.Serializable;
import java.util.List;

public class CategoriasFluxoCaixa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2964296165075771253L;

	private String tipo;
	private List<? extends CategoriasFluxoCaixa> listaNomesCategorias;
	
	public CategoriasFluxoCaixa(){
		super();
	}
	
	public CategoriasFluxoCaixa(String tipo, List<? extends CategoriasFluxoCaixa> listaNomesCategorias){
		super();
		this.tipo = tipo;
		this.listaNomesCategorias = listaNomesCategorias;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<? extends CategoriasFluxoCaixa> getListaNomesCategorias() {
		return listaNomesCategorias;
	}

	public void setListaNomesCategorias(List<? extends CategoriasFluxoCaixa> listaNomesCategorias) {
		this.listaNomesCategorias = listaNomesCategorias;
	}


}
