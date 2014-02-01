package br.com.iondax.entities.usuario;

import java.io.Serializable;

public class Cidade implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -281343803322270441L;
	
	private Long id;
	private String descCidade;
	private String uf;
	
	public Cidade(){
		super();
	}
	public Cidade(Cidade c){
		super();
		this.id = c.getId();
		this.descCidade = c.getDescCidade();
		this.uf = c.getUf();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescCidade() {
		return descCidade;
	}
	public void setDescCidade(String descCidade) {
		this.descCidade = descCidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
}
