package br.com.iondax.entities.financeiro.contabancaria.transacoes;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_recorrencia")
public class Recorrencia extends BaseEntities<Long>{

	private static final long serialVersionUID = -8332783745142003324L;

	private String frequencia;
	private Integer numComboFrequencia;
	private Integer qtdOcorrencias;
	private boolean repetir;

	public Recorrencia() {
		super();
	}

	public Recorrencia(boolean repetir, String frequencia,
			Integer qtdOcorrencias) {
		super();
		this.repetir = repetir;
		this.frequencia = frequencia;
		this.qtdOcorrencias = qtdOcorrencias;
	}

	public Recorrencia(Recorrencia r) {
		super();
		this.repetir = r.isRepetir();
		this.frequencia = r.getFrequencia();
		this.qtdOcorrencias = r.getQtdOcorrencias();
	}

	public List<SelectItem> getComboFrequenciaRepeticao() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(1, "Diaria"));
		list.add(new SelectItem(2, "Semanal"));
		list.add(new SelectItem(3, "Mensal"));
		list.add(new SelectItem(4, "Bimestral"));
		list.add(new SelectItem(5, "Trimestral"));
		list.add(new SelectItem(6, "Quadrimestral"));
		list.add(new SelectItem(7, "A cada 5 meses"));
		list.add(new SelectItem(8, "Semestral"));
		list.add(new SelectItem(9, "A cada 7 meses"));
		list.add(new SelectItem(10, "A cada 8 meses"));
		list.add(new SelectItem(11, "A cada 9 meses"));
		list.add(new SelectItem(12, "A cada 10 meses"));
		list.add(new SelectItem(13, "A cada 11 meses"));
		list.add(new SelectItem(14, "Anual"));
		return list;
	}

	public String getFrequencia() {
		return frequencia;
	}

	public Integer getNumComboFrequencia() {
		return numComboFrequencia;
	}

	public Integer getQtdOcorrencias() {
		return qtdOcorrencias;
	}

	public boolean isRepetir() {
		return repetir;
	}

	public void setFrequencia(String frequencia) {
		this.frequencia = frequencia;
	}

	public void setNumComboFrequencia(Integer numComboFrequencia) {
		this.numComboFrequencia = numComboFrequencia;
	}

	public void setQtdOcorrencias(Integer qtdOcorrencias) {
		this.qtdOcorrencias = qtdOcorrencias;
	}

	public void setRepetir(boolean repetir) {
		this.repetir = repetir;
	}

}
