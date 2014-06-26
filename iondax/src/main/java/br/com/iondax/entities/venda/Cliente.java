package br.com.iondax.entities.venda;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import br.com.iondax.entities.financeiro.contabancaria.transacoes.receita.Receita;
import br.com.iondax.entities.usuario.Contato;
import br.com.iondax.entities.usuario.Enderecos;
import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_cliente")
public class Cliente extends BaseEntities<Long>{

	private static final long serialVersionUID = 165699970730983089L;

	private String nome;
	private Integer idade;
	private String sexo;
	private Integer rg;
	private Long cpf;
	private Date dataNascimento;
	private String estadoCivil;
	private String naturalCid;
	private String naturalUf;
	private Contato contato;
	
	@OneToOne
	@ForeignKey(name="FK_Cliente_Endereco_id")
	@JoinColumn(name = "cliente_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Enderecos endereco;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cliente")
	private List<Receita> listaReceitas;
	
	public Cliente() {
		super();
	}
	
	public Cliente(Cliente c) {
		super();
		this.nome = c.getNome();
		this.idade = c.getIdade();
		this.sexo = c.getSexo();
		this.rg = c.getRg();
		this.cpf = c.getCpf();
		this.dataNascimento = c.getDataNascimento();
		this.estadoCivil = c.getEstadoCivil();
		this.naturalCid = c.getNaturalCid();
		this.naturalUf = c.getNaturalUf();
		this.contato = c.getContato();
		this.endereco = c.getEndereco();
		this.listaReceitas = c.getListaReceitas();
	}
	public Cliente(String nome, Integer idade, String sexo, Integer rg,
			Long cpf, Date dataNascimento, String estadoCivil,
			String naturalCid, String naturalUf, Contato contato,
			Enderecos endereco, List<Receita> listaReceitas) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
		this.rg = rg;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.estadoCivil = estadoCivil;
		this.naturalCid = naturalCid;
		this.naturalUf = naturalUf;
		this.contato = contato;
		this.endereco = endereco;
		this.listaReceitas = listaReceitas;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Integer getRg() {
		return rg;
	}
	public void setRg(Integer rg) {
		this.rg = rg;
	}
	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getNaturalCid() {
		return naturalCid;
	}
	public void setNaturalCid(String naturalCid) {
		this.naturalCid = naturalCid;
	}
	public String getNaturalUf() {
		return naturalUf;
	}
	public void setNaturalUf(String naturalUf) {
		this.naturalUf = naturalUf;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	public Enderecos getEndereco() {
		return endereco;
	}
	public void setEndereco(Enderecos endereco) {
		this.endereco = endereco;
	}
	public List<Receita> getListaReceitas() {
		return listaReceitas;
	}
	public void setListaReceitas(List<Receita> listaReceitas) {
		this.listaReceitas = listaReceitas;
	}

}
