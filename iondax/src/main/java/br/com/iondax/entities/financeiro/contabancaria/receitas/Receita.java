package br.com.iondax.entities.financeiro.contabancaria.receitas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.iondax.entities.venda.Cliente;

public class Receita implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3314539391213230639L;

	private boolean booleanSituacao;
	private String caminhoArquivoAnexo;
	private String categoria;
	private Cliente cliente;
	private String contaBancaria;
	private Date dataCompetencia;
	private Date dataVencimento;
	private String nome;
	private String observacoes;
	private Recorrencia recorrencia;
	private BigDecimal valorReceita;

	public Receita() {
		super();
	}

	public Receita(Receita r) {
		this.nome = r.getNome();
		this.categoria = r.getCategoria();
		this.valorReceita = r.getValorReceita();
		this.dataVencimento = r.getDataVencimento();
		this.booleanSituacao = r.isBooleanSituacao();
		this.dataCompetencia = r.getDataCompetencia();
		this.observacoes = r.getObservacoes();
		this.caminhoArquivoAnexo = r.getCaminhoArquivoAnexo();
	}

	public Receita(
			String nome,
			String categoria,
			BigDecimal valorReceita,
			String contaBancaria,
			Date dataVencimento,
			boolean situacao,
			br.com.iondax.entities.financeiro.contabancaria.receitas.Recorrencia recorrencia,
			Cliente cliente, Date dataCompetencia, String observacoes,
			String caminhoArquivoAnexo) {
		this.nome = nome;
		this.categoria = categoria;
		this.valorReceita = valorReceita;
		this.contaBancaria = contaBancaria;
		this.dataVencimento = dataVencimento;
		this.booleanSituacao = situacao;
		this.recorrencia = recorrencia;
		this.cliente = cliente;
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

	public Cliente getCliente() {
		return cliente;
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

	public String getNome() {
		return nome;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public Recorrencia getRecorrencia() {
		return recorrencia;
	}

	public BigDecimal getValorReceita() {
		return valorReceita;
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

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public void setRecorrencia(Recorrencia recorrencia) {
		this.recorrencia = recorrencia;
	}

	public void setValorReceita(BigDecimal valorReceita) {
		this.valorReceita = valorReceita;
	}

}
