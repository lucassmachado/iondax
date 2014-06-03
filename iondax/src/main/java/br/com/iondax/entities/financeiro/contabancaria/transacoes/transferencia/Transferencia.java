package br.com.iondax.entities.financeiro.contabancaria.transacoes.transferencia;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;

import br.com.iondax.entities.financeiro.contabancaria.ContaBancaria;
import br.com.iondax.entities.financeiro.fluxocaixa.CategoriaTransacao;
import br.com.iondax.entities.financeiro.relatorios.Lancamentos;
import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_transferencia")
public class Transferencia extends BaseEntities<Long>{

	private static final long serialVersionUID = 5700779903265758047L;
	
	private Date dataTransferencia;
	private String categoria;
	
	@ManyToOne
	@ForeignKey(name="FK_Transferencia_ContaBancaria_ID")
	@JoinColumn(name = "contaBancaria", referencedColumnName="id",nullable=false,insertable=true,updatable=true)
	private ContaBancaria contaBancaria;
	
	
	@OneToOne
	@ForeignKey(name="FK_Transferencia_ContaBancariaOrigem_ID")
	@JoinColumn(name = "contaBancaria_Origem",nullable=true,updatable=true)
	private ContaBancaria contaOrigem;
	
	@OneToOne
	@ForeignKey(name="FK__ContaBancariaDestino_ID")
	@JoinColumn(name = "contaBancaria_Destino",nullable=true,updatable=true)
	private ContaBancaria contaDestino;

	@ManyToOne
	@ForeignKey(name="FK_Transferencia_CategoriaTransacao_ID")
	@JoinColumn(name="subTipo", referencedColumnName = "id", insertable = true)
	private CategoriaTransacao subTipo;
	
	@OneToOne
	@ForeignKey(name="FK_Transferencia_Lancamentos_ID")
	@JoinColumn(name = "lancamentos_id",nullable=true,insertable=true,updatable=true)
	private Lancamentos lancamentos;
	
	private BigDecimal valorTransferencia;

	public Transferencia() {
		super();
	}

	public Transferencia(Date dataTransferencia, String categoria, String nome, ContaBancaria contaOrigem, ContaBancaria contaDestino, CategoriaTransacao subTipo, BigDecimal valorTransferencia) {
		super();
		this.dataTransferencia = dataTransferencia;
		this.categoria = categoria;
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.subTipo = subTipo;
		this.valorTransferencia = valorTransferencia;
	}

	public Transferencia(Transferencia t) {
		super();
		this.dataTransferencia = t.getDataTransferencia();
		this.categoria = t.getCategoria();
		this.contaOrigem = t.getContaOrigem();
		this.contaDestino = t.getContaDestino();
		this.subTipo = t.getSubTipo();
		this.valorTransferencia = t.getValorTransferencia();
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public ContaBancaria getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(ContaBancaria contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public ContaBancaria getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(ContaBancaria contaDestino) {
		this.contaDestino = contaDestino;
	}

	public Date getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(Date dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public BigDecimal getValorTransferencia() {
		return valorTransferencia;
	}

	public void setValorTransferencia(BigDecimal valorTransferencia) {
		this.valorTransferencia = valorTransferencia;
	}

	public CategoriaTransacao getSubTipo() {
		return subTipo;
	}

	public void setSubTipo(CategoriaTransacao subTipo) {
		this.subTipo = subTipo;
	}

}
