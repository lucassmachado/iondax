package br.com.iondax.entities.financeiro.contabancaria.despesa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.iondax.entities.financeiro.contabancaria.receitas.Recorrencia;
import br.com.iondax.entities.financeiro.fluxocaixa.categorias.CategoriasFluxoCaixa;
import br.com.iondax.entities.fornecedor.Fornecedor;

public class Despesa extends CategoriasFluxoCaixa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6601008180509865287L;

	private boolean booleanSituacao;
	private String caminhoArquivoAnexo;
	private String categoria;
	private String contaBancaria;
	private Date dataCompetencia;
	private Date dataVencimento;
	private Fornecedor fornecedor;
	private String nome;
	private String observacoes;
	private Recorrencia recorrencia;
	private BigDecimal valorDespesa;

	public Despesa() {
		super();
	}

	public Despesa(Despesa d) {
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

	public Despesa(String nome, String categoria, BigDecimal valorDespesa,
			String contaBancaria, Date dataVencimento, boolean situacao,
			Recorrencia recorrencia, Fornecedor fornecedor,
			Date dataCompetencia, String observacoes, String caminhoArquivoAnexo) {
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

	public String getCaminhoArquivoAnexo() {
		return caminhoArquivoAnexo;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getContaBancaria() {
		return contaBancaria;
	}

	public Date getDataCompetencia() {
		return dataCompetencia;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public String getNome() {
		return nome;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public Recorrencia getRecorrencia() {
		return recorrencia;
	}

	public BigDecimal getValorDespesa() {
		return valorDespesa;
	}

	public boolean isBooleanSituacao() {
		return booleanSituacao;
	}

	public void setBooleanSituacao(boolean booleanSituacao) {
		this.booleanSituacao = booleanSituacao;
	}

	public void setCaminhoArquivoAnexo(String caminhoArquivoAnexo) {
		this.caminhoArquivoAnexo = caminhoArquivoAnexo;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setContaBancaria(String contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public void setDataCompetencia(Date dataCompetencia) {
		this.dataCompetencia = dataCompetencia;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public void setRecorrencia(Recorrencia recorrencia) {
		this.recorrencia = recorrencia;
	}

	public void setValorDespesa(BigDecimal valorDespesa) {
		this.valorDespesa = valorDespesa;
	}

}
