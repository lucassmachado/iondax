package br.com.iondax.entities.rh;

import java.io.Serializable;
import java.util.Date;

public class Funcionario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7187641030303731589L;
	
	private Date dataDemissao;
	private Long re;
	private Date dataAdmissao;
	private Integer qtdDependentes;
	private Long pis;
	private boolean statusFuncionario;
	private Long numCAM;
	private String escolaridade;
	private String setor;
	private Long filial;
	private String descFilial;
	
	private CTPS ctps;
	
	public Funcionario(){
		super();
		ctps = new CTPS();
	}
	
	public Funcionario(Funcionario f){
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
	
	//Getters e Setters
	public Long getRe() {
		return re;
	}
	public void setRe(Long re) {
		this.re = re;
	}
	public boolean isStatusFuncionario() {
		return statusFuncionario;
	}
	public void setStatusFuncionario(boolean statusFuncionario) {
		this.statusFuncionario = statusFuncionario;
	}
	public Date getDataDemissao() {
		return dataDemissao;
	}
	public void setDataDemissao(Date dataDemissao) {
		this.dataDemissao = dataDemissao;
	}
	public Date getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	public Integer getQtdDependentes() {
		return qtdDependentes;
	}
	public void setQtdDependentes(Integer qtdDependentes) {
		this.qtdDependentes = qtdDependentes;
	}
	public Long getPis() {
		return pis;
	}
	public void setPis(Long pis) {
		this.pis = pis;
	}
	public Long getNumCAM() {
		return numCAM;
	}
	public void setNumCAM(Long numCAM) {
		this.numCAM = numCAM;
	}
	public String getEscolaridade() {
		return escolaridade;
	}
	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}

	public Long getFilial() {
		return filial;
	}

	public void setFilial(Long filial) {
		this.filial = filial;
	}

	public String getDescFilial() {
		return descFilial;
	}

	public void setDescFilial(String descFilial) {
		this.descFilial = descFilial;
	}

	public CTPS getCtps() {
		return ctps;
	}

	public void setCtps(CTPS ctps) {
		this.ctps = ctps;
	}
	
}
