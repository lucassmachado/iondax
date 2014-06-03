package br.com.iondax.entities.financeiro.contabancaria.transacoes.despesa;

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
import br.com.iondax.entities.fornecedor.Fornecedor;
import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_despesas")
public class Despesa extends BaseEntities<Long>{

	private static final long serialVersionUID = -6601008180509865287L;
	
	private String nomeDespesa;
	private boolean booleanSituacao;
	private String caminhoArquivoAnexo;
	private String categoria;
	private Date dataDespesa;
	
	@ManyToOne
	@ForeignKey(name="FK_Despesa_ContaBancaria_ID")
	@JoinColumn(name="contaBancaria_id", referencedColumnName="id",nullable=false,updatable=true, insertable = true)
	private ContaBancaria contaBancaria;
	
	private Date dataCompetencia;
	private Date dataVencimento;
	
	@ManyToOne
	@ForeignKey(name="FK_Despesa_Fornecedor_ID")
    @JoinColumn(name="fornecedor_id", referencedColumnName = "id",nullable=true,updatable=true, insertable = true)
	private Fornecedor fornecedor;

	@ManyToOne
	@ForeignKey(name="FK_Despesa_CategoriaTransacao_ID")
	@JoinColumn(name="subTipo_id", referencedColumnName = "id", insertable = true)
	private CategoriaTransacao subTipo;
	
	private String observacoes;
	
	@OneToOne
	@ForeignKey(name="FK_Despesa_Recorrencia_ID")
	@JoinColumn(name = "recorrencia_id",nullable=true,insertable=true,updatable=true)
	private Recorrencia recorrencia;
	
	@OneToOne
	@ForeignKey(name="FK_Despesa_Lancamentos_ID")
	@JoinColumn(name = "lancamentos_id",nullable=true,insertable=true,updatable=true)
	private Lancamentos lancamentos;
	
	private BigDecimal valorDespesa;

	public Despesa() {
		super();
	}

	public Despesa(Despesa d) {
		super();
		this.categoria = d.getCategoria();
		this.valorDespesa = d.getValorDespesa();
		this.dataVencimento = d.getDataVencimento();
		this.booleanSituacao = d.isBooleanSituacao();
		this.dataCompetencia = d.getDataCompetencia();
		this.subTipo = d.getSubTipo();
		this.observacoes = d.getObservacoes();
		this.caminhoArquivoAnexo = d.getCaminhoArquivoAnexo();
	}

	public Despesa(String categoria, BigDecimal valorDespesa,
			ContaBancaria contaBancaria, Date dataVencimento, boolean situacao,
			Recorrencia recorrencia, Fornecedor fornecedor,
			Date dataCompetencia, String observacoes,CategoriaTransacao subTipo, String caminhoArquivoAnexo) {
		super();
		this.categoria = categoria;
		this.valorDespesa = valorDespesa;
		this.contaBancaria = contaBancaria;
		this.dataVencimento = dataVencimento;
		this.booleanSituacao = situacao;
		this.recorrencia = recorrencia;
		this.fornecedor = fornecedor;
		this.dataCompetencia = dataCompetencia;
		this.subTipo = subTipo;
		this.observacoes = observacoes;
		this.caminhoArquivoAnexo = caminhoArquivoAnexo;
	}

	public String getCaminhoArquivoAnexo() {
		return caminhoArquivoAnexo;
	}

	public String getCategoria() {
		return categoria;
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

	public Fornecedor getFornecedor() {
		return fornecedor;
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

	public void setContaBancaria(ContaBancaria contaBancaria) {
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

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public void setRecorrencia(Recorrencia recorrencia) {
		this.recorrencia = recorrencia;
	}

	public void setValorDespesa(BigDecimal valorDespesa) {
		this.valorDespesa = valorDespesa;
	}

	public CategoriaTransacao getSubTipo() {
		return subTipo;
	}

	public void setSubTipo(CategoriaTransacao subTipo) {
		this.subTipo = subTipo;
	}

	public String getNomeDespesa() {
		return nomeDespesa;
	}

	public void setNomeDespesa(String nomeDespesa) {
		this.nomeDespesa = nomeDespesa;
	}

	public Date getDataDespesa() {
		return dataDespesa;
	}

	public void setDataDespesa(Date dataDespesa) {
		this.dataDespesa = dataDespesa;
	}

	public Lancamentos getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(Lancamentos lancamentos) {
		this.lancamentos = lancamentos;
	}

}
