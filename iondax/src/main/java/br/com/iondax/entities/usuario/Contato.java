package br.com.iondax.entities.usuario;

import java.io.Serializable;

public class Contato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5913623775064926361L;

	private String descEmail;
	private Long id;
	private String numCelular;
	private String numTelefone;

	public Contato() {
		super();
	}

	public Contato(Contato c) {
		this.id = c.getId();
		this.numTelefone = c.getNumTelefone();
		this.numCelular = c.getNumCelular();
		this.descEmail = c.getDescEmail();
	}

	public String getDescEmail() {
		return descEmail;
	}

	public Long getId() {
		return id;
	}

	public String getNumCelular() {
		return numCelular;
	}

	public String getNumTelefone() {
		return numTelefone;
	}

	public void setDescEmail(String descEmail) {
		this.descEmail = descEmail;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNumCelular(String numCelular) {
		this.numCelular = numCelular;
	}

	public void setNumTelefone(String numTelefone) {
		this.numTelefone = numTelefone;
	}

}
