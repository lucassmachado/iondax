package br.com.iondax.entities.rh.funcionario.beneficios;

import java.io.Serializable;
import java.math.BigDecimal;

public class ValeRefeicao implements Serializable{

	private static final long serialVersionUID = -794213426772377416L;

	private BigDecimal custoEmpresa;
	private BigDecimal custoFuncionario;
	
	public ValeRefeicao() {
		super();
	}
	public ValeRefeicao(BigDecimal custoEmpresa, BigDecimal custoFuncionario) {
		super();
		this.custoEmpresa = custoEmpresa;
		this.custoFuncionario = custoFuncionario;
	}
	public ValeRefeicao(ValeRefeicao vr) {
		super();
		this.custoEmpresa = vr.getCustoEmpresa();
		this.custoFuncionario = vr.getCustoFuncionario();
	}
	public BigDecimal getCustoEmpresa() {
		return custoEmpresa;
	}
	public BigDecimal getCustoFuncionario() {
		return custoFuncionario;
	}
	public void setCustoEmpresa(BigDecimal custoEmpresa) {
		this.custoEmpresa = custoEmpresa;
	}
	public void setCustoFuncionario(BigDecimal custoFuncionario) {
		this.custoFuncionario = custoFuncionario;
	}
	
}
