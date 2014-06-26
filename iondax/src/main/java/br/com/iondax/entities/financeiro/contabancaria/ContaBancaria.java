package br.com.iondax.entities.financeiro.contabancaria;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.iondax.entities.financeiro.contabancaria.transacoes.despesa.Despesa;
import br.com.iondax.entities.financeiro.contabancaria.transacoes.receita.Receita;
import br.com.iondax.entities.financeiro.relatorios.Lancamentos;
import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_contabancaria")
public class ContaBancaria extends BaseEntities<Long> {

	private static final long serialVersionUID = 9156272129043052939L;
	@Transient
	private Long rowkey;
	
	private String agencia;
	private String banco;
	private String carteira;
	private String codigoDoCliente;
	private String convenio;
	private String modalidade;
	public String nomeContaBancaria;
	private String nossoNumeroInicial;
	private char tipo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contaBancaria")
	private List<Despesa> listaDespesas;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contaBancaria")
	private List<Receita> listaReceitas;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contaBancaria")
	private List<Lancamentos> lancamentos;
	
	// nroConta terá de ser um índice
	@Column(insertable=true,updatable=true,unique=true)
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

	public Long getRowkey() {
		return super.getId();
	}

	public void setRowkey(Long rowkey) {
		this.rowkey = super.getId();
	}
	
	public void setId(Long id){
		super.setId(id);
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

}
