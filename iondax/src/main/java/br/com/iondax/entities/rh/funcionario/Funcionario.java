package br.com.iondax.entities.rh.funcionario;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;

import br.com.iondax.entities.rh.funcionario.filial.Filial;
import br.com.iondax.entities.usuario.Usuario;
import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_funcionarios")
public class Funcionario extends BaseEntities<Long>{

	private static final long serialVersionUID = -7187641030303731589L;

	@Transient
	private CTPS ctps;
	private Date dataAdmissao;
	private Date dataDemissao;
	private String escolaridade;
	@Transient
	private Filial filial;
	private Long numCAM;
	private Long pis;
	private Integer qtdDependentes;
	private Long re;
	private String setor;
	
	@OneToOne
	@ForeignKey(name="FK_Funcionario_Usuario_id")
	@JoinColumn(name = "funcionario_usuario_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Usuario usuario;
	
	//verifica se ainda é ou não usuário da empresa
	private boolean statusFuncionario;

	public Funcionario() {
		super();
		ctps = new CTPS();
		usuario = new Usuario();
	}

	public Funcionario(Funcionario f) {
		super();
		this.dataDemissao = f.getDataDemissao();
		this.re = f.getRe();
		this.dataAdmissao = f.getDataAdmissao();
		this.pis = f.getPis();
		this.statusFuncionario = f.isStatusFuncionario();
		this.numCAM = f.getNumCAM();
		this.escolaridade = f.getEscolaridade();
		this.setor = f.getSetor();
		this.filial = f.getFilial();
		this.usuario = f.getUsuario();
	}

	// Getters e Setters
	public Long getRe() {
		return re;
	}

	public String getSetor() {
		return setor;
	}

	public boolean isStatusFuncionario() {
		return statusFuncionario;
	}

	public void setCtps(CTPS ctps) {
		this.ctps = ctps;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public void setNumCAM(Long numCAM) {
		this.numCAM = numCAM;
	}

	public void setPis(Long pis) {
		this.pis = pis;
	}

	public void setQtdDependentes(Integer qtdDependentes) {
		this.qtdDependentes = qtdDependentes;
	}

	public void setRe(Long re) {
		this.re = re;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public void setStatusFuncionario(boolean statusFuncionario) {
		this.statusFuncionario = statusFuncionario;
	}
	public CTPS getCtps() {
		return ctps;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public Date getDataDemissao() {
		return dataDemissao;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public Filial getFilial() {
		return filial;
	}

	public Long getNumCAM() {
		return numCAM;
	}

	public Long getPis() {
		return pis;
	}

	public Integer getQtdDependentes() {
		return qtdDependentes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
