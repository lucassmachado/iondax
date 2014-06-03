package br.com.iondax.entities.rh.funcionario.beneficios;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AssistenciaOdontologica implements Serializable{
	
	private static final long serialVersionUID = 2938452534987344024L;
	
	private String planoAssisOdontologica;
	private String acomodacao;
	private BigDecimal valorPlano;
	private BigDecimal valorDisponibilizado;
	private Date validade;
	
	public AssistenciaOdontologica() {
		super();
	}
	
	public AssistenciaOdontologica(AssistenciaOdontologica ao) {
		super();
		this.planoAssisOdontologica = ao.getPlanoAssisOdontologica();
		this.acomodacao = ao.getAcomodacao();
		this.valorPlano = ao.getValorPlano();
		this.valorDisponibilizado = ao.getValorDisponibilizado();
		this.validade = ao.getValidade();
	}
	
	public String getPlanoAssisOdontologica() {
		return planoAssisOdontologica;
	}
	public void setPlanoAssisOdontologica(String planoAssisOdontologica) {
		this.planoAssisOdontologica = planoAssisOdontologica;
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
