package br.com.iondax.entities.rh;

import java.io.Serializable;
import java.util.Date;


public class CTPS implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6301349045702917136L;
	
	private Long id;
	private Integer numero;
	private Integer serie;
	private String ufEmissao;
	private Date dataEmissao;
	
	public CTPS(){
		super();
	}
	
	public CTPS(CTPS c){
		super();
		this.id = c.getId();
		this.numero = c.getNumero();
		this.serie = c.getSerie();
		
		this.dataEmissao = c.getDataEmissao();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Integer getSerie() {
		return serie;
	}
	public void setSerie(Integer serie) {
		this.serie = serie;
	}
	public Date getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public String getUfEmissao() {
		return ufEmissao;
	}

	public void setUfEmissao(String ufEmissao) {
		this.ufEmissao = ufEmissao;
	}
	
	

}
