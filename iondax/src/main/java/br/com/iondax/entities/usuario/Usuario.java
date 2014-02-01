package br.com.iondax.entities.usuario;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import br.com.iondax.entities.rh.Funcionario;

@Entity
public class Usuario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	
	@Transient
	private String senha;
	@Transient
	private String nome;
	@Transient
	private String sexo;
	@Transient
	private Integer rg;
	@Transient
	private Date dataNascimento;
	@Transient
	private Long cpf;
	@Transient
	private Integer idade;
	@Transient
	private String naturalCid;
	@Transient
	private String naturalUf;
	@Transient
	private String estadoCivil;
	@Transient
	private boolean statusSistema;
	
	//Endereço
	@Transient
	private EnderecoUsuario endereco;
	@Transient
	private Cidade cidade;
	@Transient
	private Bairro bairro;
	// Fim Endereço
	
	@Transient
	private Funcionario funcionario;
	
	@Transient
	private Contato contato;
	
	
	public Usuario(){
		super();
		endereco = new EnderecoUsuario();
		cidade = new Cidade();
		bairro = new Bairro();
		funcionario = new Funcionario();
		contato = new Contato();
	}
	
	public Usuario(Usuario u){
		super();
		this.id = u.getId();
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
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
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
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Long getCpf() {
		return cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public boolean isStatusSistema() {
		return statusSistema;
	}
	public void setStatusSistema(boolean statusSistema) {
		this.statusSistema = statusSistema;
	}
	public EnderecoUsuario getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoUsuario endereco) {
		this.endereco = endereco;
	}
	public Contato getContato() {
		return contato;
	}
	public void setcontato(Contato contato) {
		this.contato = contato;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public Bairro getBairro() {
		return bairro;
	}
	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
}
