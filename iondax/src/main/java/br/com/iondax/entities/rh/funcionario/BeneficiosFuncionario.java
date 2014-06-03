package br.com.iondax.entities.rh.funcionario;

import java.io.Serializable;

import br.com.iondax.entities.rh.funcionario.beneficios.AssistenciaMedica;
import br.com.iondax.entities.rh.funcionario.beneficios.AssistenciaOdontologica;
import br.com.iondax.entities.rh.funcionario.beneficios.SeguroFuncionario;
import br.com.iondax.entities.rh.funcionario.beneficios.ValeAlimentacao;
import br.com.iondax.entities.rh.funcionario.beneficios.ValeRefeicao;
import br.com.iondax.entities.rh.funcionario.beneficios.ValeTransporte;

public class BeneficiosFuncionario implements Serializable{
	
	private static final long serialVersionUID = -978454958693298923L;
	
	private AssistenciaMedica am;
	private AssistenciaOdontologica ao;
	private Boolean auxiliocreche;
	private Boolean cestabasica;
	private SeguroFuncionario sf;
	private ValeAlimentacao va;
	private ValeRefeicao vr;
	private ValeTransporte vt;
	
	public BeneficiosFuncionario() {
		super();
	}

	public BeneficiosFuncionario(BeneficiosFuncionario bf) {
		super();
		this.cestabasica = bf.getCestabasica();
		this.auxiliocreche = bf.getAuxiliocreche();
		this.am = bf.getAm();
		this.ao = bf.getAo();
		this.sf = bf.getSf();
		this.va = bf.getVa();
		this.vr = bf.getVr();
		this.vt = bf.getVt();
	}

	public BeneficiosFuncionario(Boolean cestabasica, Boolean auxiliocreche,
			AssistenciaMedica am, AssistenciaOdontologica ao,
			ValeAlimentacao va, ValeRefeicao vr, ValeTransporte vt) {
		super();
		this.cestabasica = cestabasica;
		this.auxiliocreche = auxiliocreche;
		this.am = am;
		this.ao = ao;
		this.va = va;
		this.vr = vr;
		this.vt = vt;
	}

	public AssistenciaMedica getAm() {
		return am;
	}

	public AssistenciaOdontologica getAo() {
		return ao;
	}

	public Boolean getAuxiliocreche() {
		return auxiliocreche;
	}

	public Boolean getCestabasica() {
		return cestabasica;
	}

	public SeguroFuncionario getSf() {
		return sf;
	}

	public ValeAlimentacao getVa() {
		return va;
	}

	public ValeRefeicao getVr() {
		return vr;
	}

	public ValeTransporte getVt() {
		return vt;
	}

	public void setAm(AssistenciaMedica am) {
		this.am = am;
	}

	public void setAo(AssistenciaOdontologica ao) {
		this.ao = ao;
	}

	public void setAuxiliocreche(Boolean auxiliocreche) {
		this.auxiliocreche = auxiliocreche;
	}

	public void setCestabasica(Boolean cestabasica) {
		this.cestabasica = cestabasica;
	}

	public void setSf(SeguroFuncionario sf) {
		this.sf = sf;
	}

	public void setVa(ValeAlimentacao va) {
		this.va = va;
	}

	public void setVr(ValeRefeicao vr) {
		this.vr = vr;
	}

	public void setVt(ValeTransporte vt) {
		this.vt = vt;
	}
	
	
	
}