package br.com.iondax.entities.fornecedor;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import br.com.iondax.entities.financeiro.contabancaria.transacoes.despesa.Despesa;
import br.com.iondax.entities.usuario.Contato;
import br.com.iondax.entities.usuario.Enderecos;
import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_fornecedor")
public class Fornecedor extends BaseEntities<Long> {

	private static final long serialVersionUID = 6592302407716966633L;
	
	private boolean ativo;
	private String razaoSocial;
	private String nomeFantasia;
	private int tipoPessoa;
	private String cpfCnpj;
	private String observacoes;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "fornecedor")
	private List<Despesa> listaDespesa;
	
	@ManyToOne
	@ForeignKey(name="FK_Fornecedor_contato_ID")
    @JoinColumn(name="fornecedor_contato_id", referencedColumnName = "id", insertable = true)
	private Contato Contato;
	
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE},targetEntity=Enderecos.class)  
    @JoinTable(name="tb_fornecedor_endereco",
    		   joinColumns=@JoinColumn(name="tb_fornecedor_ID"), 
			   inverseJoinColumns=@JoinColumn(name="tb_enderecos_ID"))
	@ForeignKey(name="FK_fornecedor_endereco_endereco_fornecedor_ID")
	private List<Enderecos> listaEnderecos;

	public Fornecedor() {
		super();
	}
	
	
	//Getters e Setters
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public int getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(int tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public List<Enderecos> getListaEnderecos() {
		return listaEnderecos;
	}

	public void setListaEnderecos(List<Enderecos> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}

}
