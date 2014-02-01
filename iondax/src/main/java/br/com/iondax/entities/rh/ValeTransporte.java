package br.com.iondax.entities.rh;

import java.io.Serializable;
import java.math.BigDecimal;

public class ValeTransporte implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8673396381834615863L;
	
	private Long codBeneficioTransporte;
	private String descTransporte;
	private BigDecimal valorTransporte;
	
	public ValeTransporte(){
		super();
	}
	
	public ValeTransporte(ValeTransporte b) {
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
