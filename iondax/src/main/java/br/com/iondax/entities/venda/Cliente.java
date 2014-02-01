package br.com.iondax.entities.venda;

import java.io.Serializable;

public class Cliente implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 165699970730983089L;


	public Cliente(){
		super();
	}
	
	public Cliente(String nome){
		super();
		this.nome = nome;
	}
	
	public Cliente(Cliente c){
		super();
		this.nome = c.getNome();
	}
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
