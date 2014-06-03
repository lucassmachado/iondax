package br.com.iondax.entities.usuario;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.iondax.entities.fornecedor.Fornecedor;
import br.com.iondax.entities.usuario.Bairro;

import org.hibernate.annotations.ForeignKey;

import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_enderecos")
public class Enderecos extends BaseEntities<Long> {

	private static final long serialVersionUID = 6130752571478472548L;

	private String cep;
	private String descEndereco;
	private String numero;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "endereco")
	private Usuario usuario;
	
	@ManyToOne
	@ForeignKey(name="FK_Endereco_Bairro_id")
	private Bairro bairro;
	
	@ManyToOne
	@ForeignKey(name="FK_Endereco_Cidade_id")
	private Cidade cidade;
	
	@ManyToOne
	@ForeignKey(name="FK_Endereco_Estado_id")
	private Estado estadoEndereco;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "listaEnderecos", targetEntity = Fornecedor.class)
	@ForeignKey(name="FK_endereco_fornecedor_fornecedor_endereco_ID")
	private List<Fornecedor> fornecedor;
	
	public Enderecos() {
		super();
	}

	public String getCep() {
		return cep;
	}

	public String getDescEndereco() {
		return descEndereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setDescEndereco(String descEndereco) {
		this.descEndereco = descEndereco;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstadoEndereco() {
		return estadoEndereco;
	}

	public void setEstadoEndereco(Estado estadoEndereco) {
		this.estadoEndereco = estadoEndereco;
	}
	
	
}
