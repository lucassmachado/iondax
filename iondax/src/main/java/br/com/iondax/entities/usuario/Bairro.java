package br.com.iondax.entities.usuario;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_bairros")
public class Bairro extends BaseEntities<Long> {

	private static final long serialVersionUID = 1520264487762967427L;
	private String descBairro;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "bairro")
	private List<Enderecos> listaEnderecos;
	
	public Bairro() {
		super();
	}

	public Bairro(Bairro b) {
		super();
		this.descBairro = b.getDescBairro();
	}

	public String getDescBairro() {
		return descBairro;
	}

	public void setDescBairro(String descBairro) {
		this.descBairro = descBairro;
	}

	public List<Enderecos> getListaEnderecos() {
		return listaEnderecos;
	}

	public void setListaEnderecos(List<Enderecos> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}


}
