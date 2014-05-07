package br.com.iondax.entities.usuario;

import java.io.Serializable;

public class Cidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -281343803322270441L;

	private String descCidade;
	private Long id;
	private String uf;

	public Cidade() {
		super();
	}

	public Cidade(Cidade c) {
		super();
		this.id = c.getId();
		this.descCidade = c.getDescCidade();
		this.uf = c.getUf();
	}

	public String getDescCidade() {
		return descCidade;
	}

	public Long getId() {
		return id;
	}

	public String getUf() {
		return uf;
	}

	public void setDescCidade(String descCidade) {
		this.descCidade = descCidade;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}
