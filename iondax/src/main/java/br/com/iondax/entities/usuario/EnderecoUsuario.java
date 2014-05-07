package br.com.iondax.entities.usuario;

import java.io.Serializable;

public class EnderecoUsuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6130752571478472548L;

	private String cep;
	private String descEndereco;
	private String numero;

	public EnderecoUsuario() {
		super();
	}

	public EnderecoUsuario(EnderecoUsuario e) {
		super();
		this.cep = e.getCep();
		this.descEndereco = e.getDescEndereco();
		this.numero = e.getNumero();
	}

	public String getCep() {
		return cep;
	}

	public String getDescEndereco() {
		return descEndereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setDescEndereco(String descEndereco) {
		this.descEndereco = descEndereco;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
