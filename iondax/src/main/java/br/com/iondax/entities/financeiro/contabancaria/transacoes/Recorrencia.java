package br.com.iondax.entities.financeiro.contabancaria.transacoes;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;

import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_recorrencia")
public class Recorrencia extends BaseEntities<Long>{

	private static final long serialVersionUID = -8332783745142003324L;

	private String frequencia;
	
	private Integer qtdOcorrencias;
	@Transient
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

	public String getFrequencia() {
		return frequencia;
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

	public void setQtdOcorrencias(Integer qtdOcorrencias) {
		this.qtdOcorrencias = qtdOcorrencias;
	}

	public void setRepetir(boolean repetir) {
		this.repetir = repetir;
	}

}
