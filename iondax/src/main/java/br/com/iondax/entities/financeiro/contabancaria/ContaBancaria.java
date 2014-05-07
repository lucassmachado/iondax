package br.com.iondax.entities.financeiro.contabancaria;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ContaBancaria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9156272129043052939L;

	private String agencia;
	private String banco;
	private String carteira;
	private String codigoDoCliente;
	private String convenio;
	private String modalidade;
	private String nomeContaBancaria;
	private String nossoNumeroInicial;
	private String nroConta;
	private BigDecimal saldoAtual;
	private Date ultimaVisualizacaoSaldo;
	private String variacaoCarteira;

	public ContaBancaria() {
		super();
	}

	public ContaBancaria(ContaBancaria c) {
		super();
		this.banco = c.getBanco();
		this.agencia = c.getAgencia();
		this.nroConta = c.getNroConta();
		this.saldoAtual = c.getSaldoAtual();
		this.ultimaVisualizacaoSaldo = c.getUltimaVisualizacaoSaldo();
		this.nomeContaBancaria = c.getNomeContaBancaria();
		this.carteira = c.getCarteira();
		this.convenio = c.getConvenio();
		this.variacaoCarteira = c.getVariacaoCarteira();
		this.modalidade = c.getModalidade();
		this.codigoDoCliente = c.getCodigoDoCliente();
	}

	public String getAgencia() {
		return agencia;
	}

	public String getBanco() {
		return banco;
	}

	public String getCarteira() {
		return carteira;
	}

	public String getCodigoDoCliente() {
		return codigoDoCliente;
	}

	public String getConvenio() {
		return convenio;
	}

	public String getModalidade() {
		return modalidade;
	}

	public String getNomeContaBancaria() {
		return nomeContaBancaria;
	}

	public String getNossoNumeroInicial() {
		return nossoNumeroInicial;
	}

	public String getNroConta() {
		return nroConta;
	}

	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}

	public Date getUltimaVisualizacaoSaldo() {
		return ultimaVisualizacaoSaldo;
	}

	public String getVariacaoCarteira() {
		return variacaoCarteira;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}

	public void setCodigoDoCliente(String codigoDoCliente) {
		this.codigoDoCliente = codigoDoCliente;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public void setNomeContaBancaria(String nomeContaBancaria) {
		this.nomeContaBancaria = nomeContaBancaria;
	}

	public void setNossoNumeroInicial(String nossoNumeroInicial) {
		this.nossoNumeroInicial = nossoNumeroInicial;
	}

	public void setNroConta(String nroConta) {
		this.nroConta = nroConta;
	}

	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public void setUltimaVisualizacaoSaldo(Date ultimaVisualizacaoSaldo) {
		this.ultimaVisualizacaoSaldo = ultimaVisualizacaoSaldo;
	}

	public void setVariacaoCarteira(String variacaoCarteira) {
		this.variacaoCarteira = variacaoCarteira;
	}

}
