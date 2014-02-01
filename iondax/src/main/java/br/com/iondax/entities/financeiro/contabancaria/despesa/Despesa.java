package br.com.iondax.entities.financeiro.contabancaria.despesa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.iondax.entities.financeiro.contabancaria.receitas.Recorrencia;
import br.com.iondax.entities.financeiro.fluxocaixa.categorias.CategoriasFluxoCaixa;
import br.com.iondax.entities.producao.fornecedor.Fornecedor;

public class Despesa extends CategoriasFluxoCaixa implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6601008180509865287L;
	
	private String nome;
	private BigDecimal valorDespesa;
	private String contaBancaria;
	private Date dataVencimento;
	private String categoria;
	private boolean booleanSituacao;
	private Recorrencia recorrencia;
	private Fornecedor fornecedor;
	private Date dataCompetencia;
	private String observacoes;
	private String caminhoArquivoAnexo;
	
	public Despesa(){
		super();
	}
	
	public Despesa(String nome, String categoria,BigDecimal valorDespesa, String contaBancaria,
					Date dataVencimento, boolean situacao, Recorrencia recorrencia,Fornecedor fornecedor, Date dataCompetencia, 
					String observacoes, String caminhoArquivoAnexo){
		super();
		this.nome = nome;
		this.categoria = categoria;
		this.valorDespesa = valorDespesa;
		this.contaBancaria = contaBancaria;
		this.dataVencimento = dataVencimento;
		this.booleanSituacao = situacao;
		this.recorrencia = recorrencia;
		this.fornecedor = fornecedor; 
		this.dataCompetencia = dataCompetencia;
		this.observacoes = observacoes;
		this.caminhoArquivoAnexo = caminhoArquivoAnexo;
	}

	public Despesa(Despesa d){
		super();
		this.nome = d.getNome();
		this.categoria = d.getCategoria();
		this.valorDespesa = d.getValorDespesa();
		this.dataVencimento = d.getDataVencimento();
		this.booleanSituacao = d.isBooleanSituacao();
		this.dataCompetencia = d.getDataCompetencia();
		this.observacoes = d.getObservacoes();
		this.caminhoArquivoAnexo = d.getCaminhoArquivoAnexo();
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

	public BigDecimal getValorDespesa() {
		return valorDespesa;
	}

	public void setValorDespesa(BigDecimal valorDespesa) {
		this.valorDespesa = valorDespesa;
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

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
