package br.com.iondax.entities.financeiro.contabancaria.receitas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.iondax.entities.venda.Cliente;

public class Receita implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3314539391213230639L;
	
	private String nome;
	private BigDecimal valorReceita;
	private String contaBancaria;
	private Date dataVencimento;
	private String categoria;
	private boolean booleanSituacao;
	private Recorrencia recorrencia;
	private Cliente cliente;
	private Date dataCompetencia;
	private String observacoes;
	private String caminhoArquivoAnexo;
	
	public Receita(){
		super();
	}
	
	public Receita(String nome, String categoria,BigDecimal valorReceita, String contaBancaria,
					Date dataVencimento, boolean situacao, br.com.iondax.entities.financeiro.contabancaria.receitas.Recorrencia recorrencia,Cliente cliente, Date dataCompetencia, 
					String observacoes, String caminhoArquivoAnexo){
		this.nome = nome;
		this.categoria = categoria;
		this.valorReceita = valorReceita;
		this.contaBancaria = contaBancaria;
		this.dataVencimento = dataVencimento;
		this.booleanSituacao = situacao;
		this.recorrencia = recorrencia;
		this.cliente= cliente; 
		this.dataCompetencia = dataCompetencia;
		this.observacoes = observacoes;
		this.caminhoArquivoAnexo = caminhoArquivoAnexo;
	}

	public Receita(Receita r){
		this.nome = r.getNome();
		this.categoria = r.getCategoria();
		this.valorReceita = r.getValorReceita();
		this.dataVencimento = r.getDataVencimento();
		this.booleanSituacao = r.isBooleanSituacao();
		this.dataCompetencia = r.getDataCompetencia();
		this.observacoes = r.getObservacoes();
		this.caminhoArquivoAnexo = r.getCaminhoArquivoAnexo();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getValorReceita() {
		return valorReceita;
	}

	public void setValorReceita(BigDecimal valorReceita) {
		this.valorReceita = valorReceita;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataCompetencia() {
		return dataCompetencia;
	}

	public void setDataCompetencia(Date dataCompetencia) {
		this.dataCompetencia = dataCompetencia;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getCaminhoArquivoAnexo() {
		return caminhoArquivoAnexo;
	}

	public void setCaminhoArquivoAnexo(String caminhoArquivoAnexo) {
		this.caminhoArquivoAnexo = caminhoArquivoAnexo;
	}

	public String getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(String contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public Recorrencia getRecorrencia() {
		return recorrencia;
	}

	public void setRecorrencia(Recorrencia recorrencia) {
		this.recorrencia = recorrencia;
	}

	public boolean isBooleanSituacao() {
		return booleanSituacao;
	}

	public void setBooleanSituacao(boolean booleanSituacao) {
		this.booleanSituacao = booleanSituacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
}
