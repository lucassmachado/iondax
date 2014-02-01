package br.com.iondax.entities.financeiro.contabancaria.receitas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class Recorrencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8332783745142003324L;
	
	private boolean repetir;
	private String frequencia;
	private Integer qtdOcorrencias;
	private Integer numComboFrequencia;
	
	public Recorrencia(){
		super();
	}
	
	public Recorrencia(boolean repetir, String frequencia, Integer qtdOcorrencias){
		super();
		this.repetir = repetir;
		this.frequencia = frequencia;
		this.qtdOcorrencias = qtdOcorrencias;
	}
	
	public Recorrencia(Recorrencia r){
		super();
		this.repetir = r.isRepetir();
		this.frequencia = r.getFrequencia();
		this.qtdOcorrencias = r.getQtdOcorrencias();
	}
	
	public boolean isRepetir() {
		return repetir;
	}
	public void setRepetir(boolean repetir) {
		this.repetir = repetir;
	}
	public String getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}
	public Integer getQtdOcorrencias() {
		return qtdOcorrencias;
	}
	public void setQtdOcorrencias(Integer qtdOcorrencias) {
		this.qtdOcorrencias = qtdOcorrencias;
	}
	public List<SelectItem> getComboFrequenciaRepeticao(){
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(1,"Diaria"));
		list.add(new SelectItem(2,"Semanal"));
		list.add(new SelectItem(3,"Mensal"));
		list.add(new SelectItem(4,"Bimestral"));
		list.add(new SelectItem(5,"Trimestral"));
		list.add(new SelectItem(6,"Quadrimestral"));
		list.add(new SelectItem(7,"A cada 5 meses"));
		list.add(new SelectItem(8,"Semestral"));
		list.add(new SelectItem(9,"A cada 7 meses"));
		list.add(new SelectItem(10,"A cada 8 meses"));
		list.add(new SelectItem(11,"A cada 9 meses"));
		list.add(new SelectItem(12,"A cada 10 meses"));
		list.add(new SelectItem(13,"A cada 11 meses"));
		list.add(new SelectItem(14,"Anual"));
		return list;
	}

	public Integer getNumComboFrequencia() {
		return numComboFrequencia;
	}

	public void setNumComboFrequencia(Integer numComboFrequencia) {
		this.numComboFrequencia = numComboFrequencia;
	}
	
	
}
