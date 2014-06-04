package br.com.iondax.entities.financeiro.relatorios;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;

import br.com.iondax.entities.financeiro.contabancaria.ContaBancaria;
import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_lancamentos")
public class Lancamentos extends BaseEntities<Long>{
	
	private static final long serialVersionUID = 26337511292985703L;

	@ManyToOne
	@ForeignKey(name="FK_Lancamentos_ContaBancaria_Id")
	@JoinColumn(name="contaBancaria",referencedColumnName = "id",nullable=false,insertable=true,updatable=true)
	private ContaBancaria contaBancaria;
	
	//Despesa, Receita ou Transferencia
	private String tipo;
	private String subTipo;
	private String nomeTransacao;
	private Date dataTransacao;
	private BigDecimal valor;
	private boolean situacao;

	@Transient
	private BigDecimal saldo;
	
	public Lancamentos(){
		super();
	}
	
	public Lancamentos(Lancamentos l) {
		super();
		this.contaBancaria = l.getContaBancaria();
		this.tipo = l.getTipo();
		this.subTipo = l.getSubTipo();
		this.nomeTransacao = l.getNomeTransacao();
		this.dataTransacao = l.getDataTransacao();
		this.valor = l.getValor();
		this.situacao = l.isSituacao();
	}
	
	public Lancamentos(ContaBancaria contaBancaria, String tipo, String subTipo, String nomeTransacao, 
			Date dataTransacao, BigDecimal valor, boolean situacao) {
		super();
		this.contaBancaria = contaBancaria;
		this.tipo = tipo;
		this.subTipo = subTipo;
		this.nomeTransacao = nomeTransacao;
		this.dataTransacao = dataTransacao;
		this.valor = valor;
		this.situacao = situacao;
	}
	
	
	//Getters e Setters
	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public String getSubTipo() {
		return subTipo;
	}

	public void setSubTipo(String subTipo) {
		this.subTipo = subTipo;
	}

	public Date getDataTransacao() {
		return dataTransacao;
	}

	public void setDataTransacao(Date dataTransacao) {
		this.dataTransacao = dataTransacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getNomeTransacao() {
		return nomeTransacao;
	}

	public void setNomeTransacao(String nomeTransacao) {
		this.nomeTransacao = nomeTransacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Long getId(){
		return super.getId();
	}
	
	public void setId(Long id){
		super.setId(id);
	}

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}
	
	
}
