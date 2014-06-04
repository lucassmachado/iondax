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
import br.com.iondax.entities.fornecedor.Fornecedor;
import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_despesas")
public class Despesa extends BaseEntities<Long>{

	private static final long serialVersionUID = -6601008180509865287L;
	
	private String nomeDespesa;
	private boolean situacao;
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
	
	private BigDecimal valorDespesa;

	public Despesa() {
		super();
	}

	public Despesa(Despesa d) {
		super();
		this.nomeDespesa = d.getNomeDespesa();
		this.situacao = d.isSituacao();
		this.caminhoArquivoAnexo = d.getCaminhoArquivoAnexo();
		this.categoria = d.getCategoria();
		this.dataDespesa = d.getDataDespesa();
		this.contaBancaria = d.getContaBancaria();
		this.dataCompetencia = d.getDataCompetencia();
		this.dataVencimento = d.getDataVencimento();
		this.fornecedor = d.getFornecedor();
		this.subTipo = d.getSubTipo();
		this.observacoes = d.getObservacoes();
		this.recorrencia = d.getRecorrencia();
		this.valorDespesa = d.getValorDespesa();
	}


	public Despesa(String nomeDespesa, boolean situacao,
			String caminhoArquivoAnexo, String categoria, Date dataDespesa,
			ContaBancaria contaBancaria, Date dataCompetencia,
			Date dataVencimento, Fornecedor fornecedor,
			CategoriaTransacao subTipo, String observacoes,
			Recorrencia recorrencia, BigDecimal valorDespesa) {
		super();
		this.nomeDespesa = nomeDespesa;
		this.situacao = situacao;
		this.caminhoArquivoAnexo = caminhoArquivoAnexo;
		this.categoria = categoria;
		this.dataDespesa = dataDespesa;
		this.contaBancaria = contaBancaria;
		this.dataCompetencia = dataCompetencia;
		this.dataVencimento = dataVencimento;
		this.fornecedor = fornecedor;
		this.subTipo = subTipo;
		this.observacoes = observacoes;
		this.recorrencia = recorrencia;
		this.valorDespesa = valorDespesa;
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

	public boolean isSituacao() {
		return situacao;
	}

	public void setSituacao(boolean booleanSituacao) {
		this.situacao = booleanSituacao;
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

}
