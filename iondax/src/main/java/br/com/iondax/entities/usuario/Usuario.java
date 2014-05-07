package br.com.iondax.entities.usuario;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.iondax.entities.rh.Funcionario;
import br.com.iondax.util.BaseEntities;

@Entity
@Table(name = "usuario2")
public class Usuario extends BaseEntities<Long> {

	private static final long serialVersionUID = -2325703609610144911L;

	@Transient
	private Bairro bairro;
	// Fim Endereço

	@Transient
	private Cidade cidade;

	@Transient
	private Contato contato;
	@Transient
	private Long cpf;
	@Transient
	private Date dataNascimento;
	// Endereço
	@Transient
	private EnderecoUsuario endereco;
	@Transient
	private String estadoCivil;
	@Transient
	private Funcionario funcionario;
	@Transient
	private Integer idade;
	@Transient
	private String naturalCid;
	@Transient
	private String naturalUf;
	@Transient
	private String nome;

	@Transient
	private Integer rg;
	private String senha;
	@Transient
	private String sexo;
	@Transient
	private boolean statusSistema;

	private String username;

	public Usuario() {
		super();
		endereco = new EnderecoUsuario();
		cidade = new Cidade();
		bairro = new Bairro();
		funcionario = new Funcionario();
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
		this.funcionario = u.getFuncionario();
		this.statusSistema = u.isStatusSistema();
	}

	public Bairro getBairro() {
		return bairro;
	}

	public Cidade getCidade() {
		return cidade;
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

	public EnderecoUsuario getEndereco() {
		return endereco;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public Funcionario getFuncionario() {
		return funcionario;
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

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
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

	public void setEndereco(EnderecoUsuario endereco) {
		this.endereco = endereco;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
