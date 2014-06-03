package br.com.iondax.entities.usuario;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_estados")
public class Estado extends BaseEntities<Long>{

	private static final long serialVersionUID = 8364823976234638406L;
	private String nomeEstado;
	private String sigla;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "estadoCidade")
	private List<Cidade> listaCidades;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "estadoEndereco")
	private List<Enderecos> listaEnderecos;
	
	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public List<Cidade> getListaCidades(){
		return listaCidades;
	}

	public List<Enderecos> getListaEnderecos() {
		return listaEnderecos;
	}

	public void setListaEnderecos(List<Enderecos> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}

	public void setListaCidades(List<Cidade> listaCidades) {
		this.listaCidades = listaCidades;
	}
	
	
}
