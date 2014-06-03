package br.com.iondax.entities.rh.funcionario.filial;

import java.io.Serializable;

import br.com.iondax.entities.usuario.Enderecos;

public class Filial implements Serializable{

	private static final long serialVersionUID = 3288190700524832319L;
	
	private String nomeFilial;
	private Enderecos enderecoFilial;
	
	public Filial(Filial f) {
		super();
		this.nomeFilial = f.getNomeFilial();
		this.enderecoFilial = f.getEnderecoFilial();
	}

	public Filial(String nomeFilial) {
		super();
		this.nomeFilial = nomeFilial;
	}
	
	public Filial(String nomeFilial, Enderecos enderecoFilial) {
		super();
		this.nomeFilial = nomeFilial;
		this.enderecoFilial = enderecoFilial;
	}

	public Filial(){
		super();
	}

	public String getNomeFilial() {
		return nomeFilial;
	}

	public void setNomeFilial(String nomeFilial) {
		this.nomeFilial = nomeFilial;
	}

	public Enderecos getEnderecoFilial() {
		return enderecoFilial;
	}

	public void setEnderecoFilial(Enderecos enderecoFilial) {
		this.enderecoFilial = enderecoFilial;
	}	
	
	
}
