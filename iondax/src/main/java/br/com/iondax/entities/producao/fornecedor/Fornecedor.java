package br.com.iondax.entities.producao.fornecedor;

import java.io.Serializable;

import br.com.iondax.entities.usuario.Contato;
import br.com.iondax.entities.usuario.EnderecoUsuario;

public class Fornecedor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6592302407716966633L;
	
	private String nome;
	private EnderecoUsuario endereco;
	private Contato contato;
	
	public Fornecedor(){
		super();
	}
	public Fornecedor(Fornecedor f){
		super();
		this.contato = f.getContato();
		this.endereco = f.getEndereco();
		this.nome = f.getNome();
	}
	public Fornecedor(String nome, EnderecoUsuario endereco, Contato contato){
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.contato = contato;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public EnderecoUsuario getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoUsuario endereco) {
		this.endereco = endereco;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	
}
