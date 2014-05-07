package br.com.iondax.entities.rh;

import java.io.Serializable;
import java.util.Date;

public class CTPS implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6301349045702917136L;

	private Date dataEmissao;
	private Long id;
	private Integer numero;
	private Integer serie;
	private String ufEmissao;

	public CTPS() {
		super();
	}

	public CTPS(CTPS c) {
		super();
		this.id = c.getId();
		this.numero = c.getNumero();
		this.serie = c.getSerie();

		this.dataEmissao = c.getDataEmissao();
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public Long getId() {
		return id;
	}

	public Integer getNumero() {
		return numero;
	}

	public Integer getSerie() {
		return serie;
	}

	public String getUfEmissao() {
		return ufEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void setSerie(Integer serie) {
		this.serie = serie;
	}

	public void setUfEmissao(String ufEmissao) {
		this.ufEmissao = ufEmissao;
	}

}
