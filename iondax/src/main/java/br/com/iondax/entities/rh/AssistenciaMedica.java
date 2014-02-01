package br.com.iondax.entities.rh;

import java.io.Serializable;
import java.math.BigDecimal;

public class AssistenciaMedica implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4515700962961560151L;
	
	private String descBeneficio;
	private String descPlano;
	private BigDecimal valorPlano;
	private String tipoContratacao;
	
	public AssistenciaMedica(){
		super();
	}
	
	public AssistenciaMedica(AssistenciaMedica a){
		super();
		this.descBeneficio = a.getDescBeneficio();
		this.descPlano = a.getDescPlano();
		this.valorPlano = a.getValorPlano();
		this.tipoContratacao = a.getTipoContratacao();
	}
	
	public String getDescBeneficio() {
		return descBeneficio;
	}
	public void setDescBeneficio(String descBeneficio) {
		this.descBeneficio = descBeneficio;
	}
	public String getDescPlano() {
		return descPlano;
	}
	public void setDescPlano(String descPlano) {
		this.descPlano = descPlano;
	}
	public BigDecimal getValorPlano() {
		return valorPlano;
	}
	public void setValorPlano(BigDecimal valorPlano) {
		this.valorPlano = valorPlano;
	}
	public String getTipoContratacao() {
		return tipoContratacao;
	}
	public void setTipoContratacao(String tipoContratacao) {
		this.tipoContratacao = tipoContratacao;
	}
	
	
}
