package br.com.iondax.entities.financeiro.fluxocaixa;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.iondax.controller.utils.DataUtils;
import br.com.iondax.entities.financeiro.contabancaria.transacoes.despesa.Despesa;
import br.com.iondax.entities.financeiro.contabancaria.transacoes.receita.Receita;
import br.com.iondax.entities.financeiro.contabancaria.transacoes.transferencia.Transferencia;
import br.com.iondax.entities.financeiro.relatorios.Lancamentos;
import br.com.iondax.repositories.financeiro.ILancamentosRepositories;
import br.com.iondax.util.BaseEntities;

@Entity
@Table(name="tb_subtipo_transacao")
public class CategoriaTransacao extends BaseEntities<Long>{

	private static final long serialVersionUID = 4184834585157556981L;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subTipo")
	private List<Despesa> listaDespesa;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subTipo")
	private List<Receita> listaReceita;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subTipo")
	private List<Transferencia> listaTransferencia;
	
	@Transient
	private List<CategoriaTransacao> categoriasTransacao;
	
	//Receita, Despesa, ou Transferencia
	private String tipo;
	
	//Nome da categoria - ex: venda de celulares.
	private String nome;
	
	@Transient
	private BigDecimal valorMes1;
	@Transient
	private BigDecimal valorMes2;
	@Transient
	private BigDecimal valorMes3;
	@Transient
	private BigDecimal valorMes4;
	@Transient
	private BigDecimal valorMes5;
	@Transient
	private BigDecimal valorMes6;
	@Transient
	private BigDecimal valorMes7;
	@Transient
	private BigDecimal valorMes8;
	@Transient
	private BigDecimal valorMes9;
	@Transient
	private BigDecimal valorMes10;
	@Transient
	private BigDecimal valorMes11;
	@Transient
	private BigDecimal valorMes12;
	
	public CategoriaTransacao(){
		super();
	}
	
	public CategoriaTransacao(CategoriaTransacao c) {
		super();
		this.tipo = c.getTipo();
		this.categoriasTransacao = c.getCategoriasTransacao();
	}

	public CategoriaTransacao(String tipo, List<CategoriaTransacao> categoriasTransacao,ILancamentosRepositories service, String ano) {
		super();
		this.tipo = tipo;
		this.categoriasTransacao = categoriasTransacao;
		
		List<Lancamentos> lancamentos = service.findAll();
		
		for(int j=0;j<categoriasTransacao.size();j++){
			
			categoriasTransacao.get(j).setValorMes1(new BigDecimal(0));
			categoriasTransacao.get(j).setValorMes2(new BigDecimal(0));
			categoriasTransacao.get(j).setValorMes3(new BigDecimal(0));
			categoriasTransacao.get(j).setValorMes4(new BigDecimal(0));
			categoriasTransacao.get(j).setValorMes5(new BigDecimal(0));
			categoriasTransacao.get(j).setValorMes6(new BigDecimal(0));
			categoriasTransacao.get(j).setValorMes7(new BigDecimal(0));
			categoriasTransacao.get(j).setValorMes8(new BigDecimal(0));
			categoriasTransacao.get(j).setValorMes9(new BigDecimal(0));
			categoriasTransacao.get(j).setValorMes10(new BigDecimal(0));
			categoriasTransacao.get(j).setValorMes11(new BigDecimal(0));
			categoriasTransacao.get(j).setValorMes12(new BigDecimal(0));
			
			for(int i=0;i<lancamentos.size();i++){
				
				if(lancamentos.get(i).getTipo().equals(tipo)){
					
					if(lancamentos.get(i).getSubTipo().equals(categoriasTransacao.get(j).getNome())){
						
						if(DataUtils.dataToString(lancamentos.get(i).getDataTransacao()).split("/")[2].equals(ano)){
							
							switch(DataUtils.dataToString(lancamentos.get(i).getDataTransacao()).split("/")[1]){
							case "01":
								categoriasTransacao.get(j).setValorMes1(categoriasTransacao.get(j).getValorMes1().add(lancamentos.get(i).getValor()));
								break;
							case "02":
								categoriasTransacao.get(j).setValorMes2(categoriasTransacao.get(j).getValorMes2().add(lancamentos.get(i).getValor()));
								break;
							case "03":
								categoriasTransacao.get(j).setValorMes3(categoriasTransacao.get(j).getValorMes3().add(lancamentos.get(i).getValor()));
								break;
							case "04":
								categoriasTransacao.get(j).setValorMes4(categoriasTransacao.get(j).getValorMes4().add(lancamentos.get(i).getValor()));
								break;
							case "05":
								categoriasTransacao.get(j).setValorMes5(categoriasTransacao.get(j).getValorMes5().add(lancamentos.get(i).getValor()));
								break;
							case "06":
								categoriasTransacao.get(j).setValorMes6(categoriasTransacao.get(j).getValorMes6().add(lancamentos.get(i).getValor()));
								break;
							case "07":
								categoriasTransacao.get(j).setValorMes7(categoriasTransacao.get(j).getValorMes7().add(lancamentos.get(i).getValor()));
								break;
							case "08":
								categoriasTransacao.get(j).setValorMes8(categoriasTransacao.get(j).getValorMes8().add(lancamentos.get(i).getValor()));
								break;
							case "09":
								categoriasTransacao.get(j).setValorMes9(categoriasTransacao.get(j).getValorMes9().add(lancamentos.get(i).getValor()));
								break;
							case "10":
								categoriasTransacao.get(j).setValorMes10(categoriasTransacao.get(j).getValorMes10().add(lancamentos.get(i).getValor()));
								break;
							case "11":
								categoriasTransacao.get(j).setValorMes11(categoriasTransacao.get(j).getValorMes11().add(lancamentos.get(i).getValor()));
								break;
							case "12":
								categoriasTransacao.get(j).setValorMes12(categoriasTransacao.get(j).getValorMes12().add(lancamentos.get(i).getValor()));
								break;
							
							}
						}
					}
				}
			}
		}
		
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Long getId(){
		return super.getId();
	}
	public void setId(Long id){
		super.setId(id);
	}

	public List<CategoriaTransacao> getCategoriasTransacao() {
		return categoriasTransacao;
	}

	public void setCategoriasTransacao(List<CategoriaTransacao> categoriasTransacao) {
		this.categoriasTransacao = categoriasTransacao;
	}
	
	public Long getRowkey(){
		return super.getId();
	}

	public BigDecimal getValorMes1() {
		return valorMes1;
	}

	public void setValorMes1(BigDecimal valorMes1) {
		this.valorMes1 = valorMes1;
	}

	public BigDecimal getValorMes6() {
		return valorMes6;
	}

	public void setValorMes6(BigDecimal valorMes6) {
		this.valorMes6 = valorMes6;
	}

	public BigDecimal getValorMes2() {
		return valorMes2;
	}

	public void setValorMes2(BigDecimal valorMes2) {
		this.valorMes2 = valorMes2;
	}

	public BigDecimal getValorMes3() {
		return valorMes3;
	}

	public void setValorMes3(BigDecimal valorMes3) {
		this.valorMes3 = valorMes3;
	}

	public BigDecimal getValorMes4() {
		return valorMes4;
	}

	public void setValorMes4(BigDecimal valorMes4) {
		this.valorMes4 = valorMes4;
	}

	public BigDecimal getValorMes5() {
		return valorMes5;
	}

	public void setValorMes5(BigDecimal valorMes5) {
		this.valorMes5 = valorMes5;
	}

	public BigDecimal getValorMes7() {
		return valorMes7;
	}

	public void setValorMes7(BigDecimal valorMes7) {
		this.valorMes7 = valorMes7;
	}

	public BigDecimal getValorMes8() {
		return valorMes8;
	}

	public void setValorMes8(BigDecimal valorMes8) {
		this.valorMes8 = valorMes8;
	}

	public BigDecimal getValorMes9() {
		return valorMes9;
	}

	public void setValorMes9(BigDecimal valorMes9) {
		this.valorMes9 = valorMes9;
	}

	public BigDecimal getValorMes10() {
		return valorMes10;
	}

	public void setValorMes10(BigDecimal valorMes10) {
		this.valorMes10 = valorMes10;
	}

	public BigDecimal getValorMes11() {
		return valorMes11;
	}

	public void setValorMes11(BigDecimal valorMes11) {
		this.valorMes11 = valorMes11;
	}

	public BigDecimal getValorMes12() {
		return valorMes12;
	}

	public void setValorMes12(BigDecimal valorMes12) {
		this.valorMes12 = valorMes12;
	}
	
}
