package br.com.iondax.entities.usuario;

import java.io.Serializable;

public class Bairro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1520264487762967427L;
	
	private String descBairro;
	private Long id;

	public Bairro() {
		super();
	}

	public Bairro(Bairro b) {
		super();
		this.id = b.getId();
		this.descBairro = b.getDescBairro();
	}

	public String getDescBairro() {
		return descBairro;
	}

	public Long getId() {
		return id;
	}

	public void setDescBairro(String descBairro) {
		this.descBairro = descBairro;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
