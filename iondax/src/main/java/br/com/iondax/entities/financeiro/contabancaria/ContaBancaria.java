package br.com.iondax.entities.financeiro.contabancaria;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ContaBancaria implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9156272129043052939L;
	
	private String banco;
	private String agencia;
	private String nroConta;
	private BigDecimal saldoAtual;
	private Date ultimaVisualizacaoSaldo;
	private String nomeContaBancaria;
	private String carteira;
	private String convenio;
	private String variacaoCarteira;
	private String modalidade;
	private String codigoDoCliente;
	private String nossoNumeroInicial;
	
	public ContaBancaria(){
		super();
	}
	
	public ContaBancaria(ContaBancaria c){
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
	
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public String getNroConta() {
		return nroConta;
	}
	public void setNroConta(String nroConta) {
		this.nroConta = nroConta;
	}
	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}
	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}
	public Date getUltimaVisualizacaoSaldo() {
		return ultimaVisualizacaoSaldo;
	}
	public void setUltimaVisualizacaoSaldo(Date ultimaVisualizacaoSaldo) {
		this.ultimaVisualizacaoSaldo = ultimaVisualizacaoSaldo;
	}
	public String getNomeContaBancaria() {
		return nomeContaBancaria;
	}
	public void setNomeContaBancaria(String nomeContaBancaria) {
		this.nomeContaBancaria = nomeContaBancaria;
	}
	public String getCarteira() {
		return carteira;
	}
	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}
	public String getConvenio() {
		return convenio;
	}
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}
	public String getVariacaoCarteira() {
		return variacaoCarteira;
	}
	public void setVariacaoCarteira(String variacaoCarteira) {
		this.variacaoCarteira = variacaoCarteira;
	}
	public String getModalidade() {
		return modalidade;
	}
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	public String getCodigoDoCliente() {
		return codigoDoCliente;
	}
	public void setCodigoDoCliente(String codigoDoCliente) {
		this.codigoDoCliente = codigoDoCliente;
	}

	public String getNossoNumeroInicial() {
		return nossoNumeroInicial;
	}

	public void setNossoNumeroInicial(String nossoNumeroInicial) {
		this.nossoNumeroInicial = nossoNumeroInicial;
	}

}
