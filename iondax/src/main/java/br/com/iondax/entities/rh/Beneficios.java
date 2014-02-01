package br.com.iondax.entities.rh;

import java.math.BigDecimal;

public class Beneficios {
	
	private Long codBeneficioTransporte;
	private String descTransporte;
	private BigDecimal valorTransporte;
	
	public Beneficios(){
		super();
	}
	
	public Beneficios(Beneficios b) {
		super();
		this.codBeneficioTransporte = b.getCodBeneficioTransporte();
		this.descTransporte = b.getDescTransporte();
		this.valorTransporte = b.getValorTransporte();
	}
	
	//Getters e Setters
	public Long getCodBeneficioTransporte() {
		return codBeneficioTransporte;
	}
	public void setCodBeneficioTransporte(Long codBeneficioTransporte) {
		this.codBeneficioTransporte = codBeneficioTransporte;
	}
	public String getDescTransporte() {
		return descTransporte;
	}
	public void setDescTransporte(String descTransporte) {
		this.descTransporte = descTransporte;
	}
	public BigDecimal getValorTransporte() {
		return valorTransporte;
	}
	public void setValorTransporte(BigDecimal valorTransporte) {
		this.valorTransporte = valorTransporte;
	}
	
	
}
