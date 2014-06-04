package br.com.iondax.entities.financeiro.fluxocaixa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.iondax.entities.financeiro.contabancaria.transacoes.despesa.Despesa;
import br.com.iondax.entities.financeiro.contabancaria.transacoes.receita.Receita;
import br.com.iondax.entities.financeiro.contabancaria.transacoes.transferencia.Transferencia;
import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_subtipo_transacao")
public class CategoriaTransacao extends BaseEntities<Long>{

	private static final long serialVersionUID = 4184834585157556981L;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subTipo")
	private List<Despesa> listaDespesa;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subTipo")
	private List<Receita> listaReceita;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subTipo")
	private List<Transferencia> listaTransferencia;
	
	@Transient
	private List<String> categoriasTransacao;
	
	//Receita, Despesa, ou Transferencia
	private String tipo;
	
	//Nome da categoria - ex: venda de celulares.
	private String nome;
	
	public CategoriaTransacao(){
		super();
	}
	
	public CategoriaTransacao(CategoriaTransacao c) {
		super();
		this.tipo = c.getTipo();
		this.categoriasTransacao = c.getCategoriasTransacao();
	}

	public CategoriaTransacao(String tipo, List<String> categoriasTransacao) {
		super();
		this.tipo = tipo;
		this.categoriasTransacao = categoriasTransacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Long getId(){
		return super.getId();
	}
	public void setId(Long id){
		super.setId(id);
	}

	public List<String> getCategoriasTransacao() {
		return categoriasTransacao;
	}

	public void setCategoriasTransacao(List<String> categoriasTransacao) {
		this.categoriasTransacao = categoriasTransacao;
	}
	
	public Long getRowkey(){
		return super.getId();
	}
	
}
