package br.com.iondax.entities.rh.funcionario.beneficios;

import java.io.Serializable;
import java.math.BigDecimal;

public class ValeAlimentacao implements Serializable{

	private static final long serialVersionUID = -1795147617975923225L;
	
	private BigDecimal custoEmpresa;
	private BigDecimal custoFuncionario;
	
	public ValeAlimentacao() {
		super();
	}
	public ValeAlimentacao(BigDecimal custoEmpresa, BigDecimal custoFuncionario) {
		super();
		this.custoEmpresa = custoEmpresa;
		this.custoFuncionario = custoFuncionario;
	}
	public ValeAlimentacao(ValeAlimentacao va) {
		super();
		this.custoEmpresa = va.getCustoEmpresa();
		this.custoFuncionario = va.getCustoFuncionario();
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
