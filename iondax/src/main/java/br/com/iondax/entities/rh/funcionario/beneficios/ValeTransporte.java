package br.com.iondax.entities.rh.funcionario.beneficios;

import java.io.Serializable;
import java.math.BigDecimal;

public class ValeTransporte implements Serializable {

	private static final long serialVersionUID = 8673396381834615863L;

	private Long codValeTransporte;
	private String descTransporte;
	private BigDecimal valorTransporte;

	public ValeTransporte() {
		super();
	}

	public ValeTransporte(ValeTransporte b) {
		super();
		this.codValeTransporte = b.getCodBeneficioTransporte();
		this.descTransporte = b.getDescTransporte();
		this.valorTransporte = b.getValorTransporte();
	}

	// Getters e Setters
	public Long getCodBeneficioTransporte() {
		return codValeTransporte;
	}

	public String getDescTransporte() {
		return descTransporte;
	}

	public BigDecimal getValorTransporte() {
		return valorTransporte;
	}

	public void setCodBeneficioTransporte(Long codBeneficioTransporte) {
		this.codValeTransporte = codBeneficioTransporte;
	}

	public void setDescTransporte(String descTransporte) {
		this.descTransporte = descTransporte;
	}

	public void setValorTransporte(BigDecimal valorTransporte) {
		this.valorTransporte = valorTransporte;
	}

}
