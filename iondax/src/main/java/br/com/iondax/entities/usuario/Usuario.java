package br.com.iondax.entities.usuario;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;

import br.com.iondax.entities.rh.funcionario.Funcionario;
import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_usuarios")
public class Usuario extends BaseEntities<Long>{

	private static final long serialVersionUID = -2325703609610144911L;

	private Contato contato;
	private Long cpf;
	private Date dataNascimento;

	// Endereço
	@OneToOne
	@ForeignKey(name="FK_Usuario_Endereco_id")
	@JoinColumn(name = "usuario_endereco_id", referencedColumnName = "id", insertable = true, updatable = true,nullable=true)
	private Enderecos endereco;
	
	
	private String estadoCivil;
	private Integer idade;
	private String naturalCid;
	private String naturalUf;
	private String nome;

	private Integer rg;
	private String senha;
	private String sexo;
	private boolean statusSistema;

	private String username;
	
	public Usuario() {
		super();
		endereco = new Enderecos();
		contato = new Contato();
	}

	public Usuario(Usuario u) {
		super();
		this.username = u.getUsername();
		this.senha = u.getSenha();
		this.sexo = u.getSexo();
		this.rg = u.getRg();
		this.dataNascimento = u.getDataNascimento();
		this.cpf = u.getCpf();
		this.idade = u.getIdade();
		this.naturalCid = u.getNaturalCid();
		this.naturalUf = u.getNaturalUf();
		this.estadoCivil = u.getEstadoCivil();
		this.statusSistema = u.isStatusSistema();
	}

	public Contato getContato() {
		return contato;
	}

	public Long getCpf() {
		return cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public Enderecos getEndereco() {
		return endereco;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public Integer getIdade() {
		return idade;
	}

	public String getNaturalCid() {
		return naturalCid;
	}

	public String getNaturalUf() {
		return naturalUf;
	}

	public String getNome() {
		return nome;
	}

	public Integer getRg() {
		return rg;
	}

	public String getSenha() {
		return senha;
	}

	public String getSexo() {
		return sexo;
	}

	public String getUsername() {
		return username;
	}

	public boolean isStatusSistema() {
		return statusSistema;
	}

	public void setcontato(Contato contato) {
		this.contato = contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setEndereco(Enderecos endereco) {
		this.endereco = endereco;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}


	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public void setNaturalCid(String naturalCid) {
		this.naturalCid = naturalCid;
	}

	public void setNaturalUf(String naturalUf) {
		this.naturalUf = naturalUf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setStatusSistema(boolean statusSistema) {
		this.statusSistema = statusSistema;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
