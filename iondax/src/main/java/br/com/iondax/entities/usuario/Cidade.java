package br.com.iondax.entities.usuario;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_cidades")
public class Cidade extends BaseEntities<Long> {

	private static final long serialVersionUID = -281343803322270441L;
	private String nomeCidade;
	
	@ManyToOne
	@ForeignKey(name="FK_Cidade_Estado_ID")
    @JoinColumn(name="cidade_estado_id", referencedColumnName = "id", insertable = true)
	private Estado estadoCidade;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cidade")
	private List<Enderecos> listaEnderecos;
	
	public Cidade() {
		super();
	}

	public Cidade(Cidade c) {
		super();
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}
	
	public List<Enderecos> getListaEnderecos() {
		return listaEnderecos;
	}

	public void setListaEnderecos(List<Enderecos> listaEnderecos) {
		this.listaEnderecos = listaEnderecos;
	}

	public Estado getEstadoCidade() {
		return estadoCidade;
	}

	public void setEstadoCidade(Estado estadoCidade) {
		this.estadoCidade = estadoCidade;
	}

}
