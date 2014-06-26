package br.com.iondax.entities.financeiro.relatorios;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import br.com.iondax.entities.usuario.Usuario;
import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_historicoMovimentacao")
public class HistMovimentacao extends BaseEntities<Long>{
	
	private static final long serialVersionUID = -3893177465756682789L;

	@OneToOne
	@ForeignKey(name="FK_HistoricoMovimentacao_Usuario_ID")
	@JoinColumn(name = "usuario_id",nullable=false,insertable=true,updatable=true)
	private Usuario usuario;
	
	//delecao, inclusao, alteracao
	private String acao;
	
	//onde fez uma das 3 opções acima.
	private String descricao;
	
	private BigDecimal valor;
	
	private Date dataHora;
	
	public HistMovimentacao(){
		super();
	}
	
	public HistMovimentacao(Usuario usuario, String acao, String descricao,
			BigDecimal valor, Date dataHora) {
		super();
		this.usuario = usuario;
		this.acao = acao;
		this.descricao = descricao;
		this.valor = valor;
		this.dataHora = dataHora;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	
}
