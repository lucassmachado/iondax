package br.com.iondax.entities.financeiro.contabancaria.transacoes.receita;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import br.com.iondax.entities.financeiro.contabancaria.ContaBancaria;
import br.com.iondax.entities.financeiro.contabancaria.transacoes.Recorrencia;
import br.com.iondax.entities.financeiro.fluxocaixa.CategoriaTransacao;
import br.com.iondax.entities.financeiro.relatorios.Lancamentos;
import br.com.iondax.entities.venda.Cliente;
import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_receitas")
public class Receita extends BaseEntities<Long> {

	private static final long serialVersionUID = -3314539391213230639L;
	
	private String nomeReceita;
	private boolean situacao;
	private String caminhoArquivoAnexo;
	private Date dataCompetencia;
	private Date dataReceita;
	private Date dataVencimento;
	
	@ManyToOne
	@ForeignKey(name="FK_Receita_Cliente_ID")
    @JoinColumn(name="cliente", referencedColumnName = "id",nullable=true,updatable=true, insertable = true)
	private Cliente cliente;
	
	@ManyToOne
	@ForeignKey(name="FK_Receita_ContaBancaria_ID")
	@JoinColumn(name = "contaBancaria", referencedColumnName="id",nullable=false,insertable=true,updatable=true)
	private ContaBancaria contaBancaria;
	
	@ManyToOne
	@ForeignKey(name="FK_Receita_CategoriaTransacao_ID")
	@JoinColumn(name="subTipo", referencedColumnName = "id", insertable = true)
	private CategoriaTransacao subTipo;
	
	private String observacoes;
	
	@OneToOne
	@ForeignKey(name="FK_Receita_CategoriaTransacao_ID")
	@JoinColumn(name = "recorrencia",nullable=true,insertable=true,updatable=true)
	private Recorrencia recorrencia;
	
	@OneToOne
	@ForeignKey(name="FK_Receita_Lancamentos_ID")
	@JoinColumn(name = "lancamentos_id",nullable=false,insertable=true,updatable=true)
	private Lancamentos lancamentos;
	
	private BigDecimal valorReceita;

	public Receita() {
		super();
	}
	
	public Receita(String categoria){
		super();
	}

	public Receita(Receita r) {
		this.nomeReceita = r.getNomeReceita();
		this.situacao = r.isSituacao();
		this.caminhoArquivoAnexo = r.getCaminhoArquivoAnexo();
		this.cliente = r.getCliente();
		this.contaBancaria = r.getContaBancaria();
		this.dataCompetencia = r.getDataCompetencia();
		this.dataReceita = r.getDataReceita();
		this.dataVencimento = r.getDataVencimento();
		this.subTipo = r.getSubTipo();
		this.observacoes = r.getObservacoes();
		this.recorrencia = r.getRecorrencia();
		this.valorReceita = r.getValorReceita();
	}


	public Receita(String nomeReceita,boolean situacao, String caminhoArquivoAnexo,
			Cliente cliente, ContaBancaria contaBancaria,
			Date dataCompetencia, Date dataReceita, Date dataVencimento, CategoriaTransacao subTipo,
			String observacoes, Recorrencia recorrencia, BigDecimal valorReceita) {
		super();
		this.nomeReceita = nomeReceita;
		this.situacao = situacao;
		this.caminhoArquivoAnexo = caminhoArquivoAnexo;
		this.cliente = cliente;
		this.contaBancaria = contaBancaria;
		this.dataCompetencia = dataCompetencia;
		this.dataReceita = dataReceita;
		this.dataVencimento = dataVencimento;
		this.subTipo = subTipo;
		this.observacoes = observacoes;
		this.recorrencia = recorrencia;
		this.valorReceita = valorReceita;
	}

	public String getCaminhoArquivoAnexo() {
		return caminhoArquivoAnexo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}

	public Date getDataCompetencia() {
		return dataCompetencia;
	}

	public Date getDataVencimento() {
		return dataVencimento;
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

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}

	public void setCaminhoArquivoAnexo(String caminhoArquivoAnexo) {
		this.caminhoArquivoAnexo = caminhoArquivoAnexo;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	public void setDataCompetencia(Date dataCompetencia) {
		this.dataCompetencia = dataCompetencia;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
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

	public Date getDataReceita() {
		return dataReceita;
	}

	public void setDataReceita(Date dataReceita) {
		this.dataReceita = dataReceita;
	}

	public CategoriaTransacao getSubTipo() {
		return subTipo;
	}

	public void setSubTipo(CategoriaTransacao subTipo) {
		this.subTipo = subTipo;
	}

	public String getNomeReceita() {
		return nomeReceita;
	}

	public void setNomeReceita(String nomeReceita) {
		this.nomeReceita = nomeReceita;
	}

	public Lancamentos getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(Lancamentos lancamentos) {
		this.lancamentos = lancamentos;
	}

}
