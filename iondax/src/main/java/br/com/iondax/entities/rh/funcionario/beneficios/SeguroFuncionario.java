package br.com.iondax.entities.rh.funcionario.beneficios;

import java.io.Serializable;
import java.math.BigDecimal;

public class SeguroFuncionario implements Serializable{

	private static final long serialVersionUID = 8180351661441291553L;

	private BigDecimal capitalSegurado;
	private BigDecimal custoEmpresa;
	private BigDecimal custoFuncionario;
	private String seguro;
	
	public SeguroFuncionario() {
		super();
	}
	public SeguroFuncionario(SeguroFuncionario sf) {
		super();
		this.seguro = sf.getSeguro();
		this.capitalSegurado = sf.getCapitalSegurado();
		this.custoEmpresa = sf.getCustoEmpresa();
		this.custoFuncionario = sf.getCustoFuncionario();
	}
	public SeguroFuncionario(String seguro, BigDecimal capitalSegurado,
			BigDecimal custoEmpresa, BigDecimal custoFuncionario) {
		super();
		this.seguro = seguro;
		this.capitalSegurado = capitalSegurado;
		this.custoEmpresa = custoEmpresa;
		this.custoFuncionario = custoFuncionario;
	}
	public BigDecimal getCapitalSegurado() {
		return capitalSegurado;
	}
	public BigDecimal getCustoEmpresa() {
		return custoEmpresa;
	}
	public BigDecimal getCustoFuncionario() {
		return custoFuncionario;
	}
	public String getSeguro() {
		return seguro;
	}
	public void setCapitalSegurado(BigDecimal capitalSegurado) {
		this.capitalSegurado = capitalSegurado;
	}
	public void setCustoEmpresa(BigDecimal custoEmpresa) {
		this.custoEmpresa = custoEmpresa;
	}
	public void setCustoFuncionario(BigDecimal custoFuncionario) {
		this.custoFuncionario = custoFuncionario;
	}
	public void setSeguro(String seguro) {
		this.seguro = seguro;
	}
	
	
	
	
}
