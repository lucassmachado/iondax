package br.com.iondax.entities.venda;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.iondax.entities.financeiro.contabancaria.transacoes.receita.Receita;
import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_cliente")
public class Cliente extends BaseEntities<Long>{

	private static final long serialVersionUID = 165699970730983089L;

	private String nome;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cliente")
	private List<Receita> listaReceitas;

	public Cliente() {
		super();
	}

	public Cliente(Cliente c) {
		super();
		this.nome = c.getNome();
	}

	public Cliente(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
