package br.com.iondax.entities.usuario;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_contatos")
public class Contato extends BaseEntities<Long> {

	private static final long serialVersionUID = -5913623775064926361L;

	private String descEmail;
	private String numCelular;
	private String numTelefone;
	private String site;
	
	public Contato() {
		super();
	}

	public Contato(Contato c) {
		this.numTelefone = c.getNumTelefone();
		this.numCelular = c.getNumCelular();
		this.descEmail = c.getDescEmail();
	}

	public String getDescEmail() {
		return descEmail;
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

	public void setNumCelular(String numCelular) {
		this.numCelular = numCelular;
	}

	public void setNumTelefone(String numTelefone) {
		this.numTelefone = numTelefone;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

}
