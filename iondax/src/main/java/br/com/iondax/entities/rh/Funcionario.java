package br.com.iondax.entities.rh;

import java.io.Serializable;
import java.util.Date;

public class Funcionario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7187641030303731589L;

	private CTPS ctps;
	private Date dataAdmissao;
	private Date dataDemissao;
	private String descFilial;
	private String escolaridade;
	private Long filial;
	private Long numCAM;
	private Long pis;
	private Integer qtdDependentes;
	private Long re;
	private String setor;

	private boolean statusFuncionario;

	public Funcionario() {
		super();
		ctps = new CTPS();
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

	public String getDescFilial() {
		return descFilial;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public Long getFilial() {
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

	public void setDescFilial(String descFilial) {
		this.descFilial = descFilial;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public void setFilial(Long filial) {
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

}
