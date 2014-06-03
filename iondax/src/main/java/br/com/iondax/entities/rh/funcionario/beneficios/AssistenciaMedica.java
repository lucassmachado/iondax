package br.com.iondax.entities.rh.funcionario.beneficios;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AssistenciaMedica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4515700962961560151L;

	private String planoAssisMedica;
	private String acomodacao;
	private BigDecimal valorPlano;
	private BigDecimal valorDisponibilizado;
	private Date validade;
	

	public AssistenciaMedica() {
		super();
	}
	
	public AssistenciaMedica(AssistenciaMedica am) {
		super();
		this.planoAssisMedica = am.getPlanoAssisMedica();
		this.acomodacao = am.getAcomodacao();
		this.valorPlano = am.getValorDisponibilizado();
		this.valorDisponibilizado = am.getValorDisponibilizado();
		this.validade = am.getValidade();
	}

	public String getPlanoAssisMedica() {
		return planoAssisMedica;
	}
	public void setPlanoAssisMedica(String planoAssisMedica) {
		this.planoAssisMedica = planoAssisMedica;
	}
	public String getAcomodacao() {
		return acomodacao;
	}
	public void setAcomodacao(String acomodacao) {
		this.acomodacao = acomodacao;
	}
	public BigDecimal getValorPlano() {
		return valorPlano;
	}
	public void setValorPlano(BigDecimal valorPlano) {
		this.valorPlano = valorPlano;
	}
	public BigDecimal getValorDisponibilizado() {
		return valorDisponibilizado;
	}
	public void setValorDisponibilizado(BigDecimal valorDisponibilizado) {
		this.valorDisponibilizado = valorDisponibilizado;
	}
	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}

}
