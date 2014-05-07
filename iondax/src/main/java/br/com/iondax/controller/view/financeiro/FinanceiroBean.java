package br.com.iondax.controller.view.financeiro;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import org.jrimum.bopepo.view.BoletoViewer;
import org.primefaces.event.FileUploadEvent;

import br.com.iondax.controller.boleto.GeradorDeBoleto;
import br.com.iondax.controller.utils.Utilidades;
import br.com.iondax.entities.financeiro.contabancaria.ContaBancaria;
import br.com.iondax.entities.financeiro.contabancaria.despesa.Despesa;
import br.com.iondax.entities.financeiro.contabancaria.receitas.Receita;
import br.com.iondax.entities.financeiro.contabancaria.receitas.Recorrencia;
import br.com.iondax.entities.financeiro.fluxocaixa.categorias.CategoriaDespesas;
import br.com.iondax.entities.financeiro.fluxocaixa.categorias.CategoriaReceita;
import br.com.iondax.entities.financeiro.fluxocaixa.categorias.CategoriaTransferencias;
import br.com.iondax.entities.financeiro.fluxocaixa.categorias.CategoriasFluxoCaixa;
import br.com.iondax.entities.fornecedor.Fornecedor;
import br.com.iondax.entities.venda.Cliente;

@ManagedBean(name = "financeiroBean")
@SessionScoped
public class FinanceiroBean {

	private List<SelectItem> comboCategoriaReceitasEDespesas;
	private ContaBancaria conta;
	private Despesa contaAPagar;
	private Receita contaAReceber;
	private List<SelectItem> listaBancos = new ArrayList<SelectItem>();
	private List<SelectItem> listaCarteirasBoleto = new ArrayList<SelectItem>();
	private List<CategoriasFluxoCaixa> listaCategoriaFluxo;
	private List<ContaBancaria> listaContas;

	private Long mascaraNossoNumero = 99999999999999999L;

	private int numBancoSelecionado = 0;

	private int numCategoriaReceitaSelecionado = 0;

	/*
	 * M�todo que abre a p�gina de Consulta das conta
	 */
	public String abrirPaginaConsultaConta() {
		return "/content/financeiro/contaBancaria/conContaBancaria.jsf?faces-redirect=true";
	}

	/*
	 * M�todo que abre a p�gina de Extrato de Lan�amentos
	 */
	public String abrirPaginaExtrato() {
		return "/content/financeiro/relatorios/extratoLancamentos.jsf?faces-redirect=true";
	}

	/*
	 * M�todo que abre a p�gina de Hist�rico de Movimenta��o
	 */
	public String abrirPaginaHistoricoMovimentacao() {
		return "/content/financeiro/relatorios/histMovimentacao.jsf?faces-redirect=true";
	}

	/*
	 * M�todo que abre a p�gina de inclus�o de conta a pagar
	 */
	public String abrirPaginaIncContaAPagar() {
		contaAPagar = new Despesa();
		contaAPagar.setRecorrencia(new Recorrencia());
		contaAPagar.setFornecedor(new Fornecedor());
		carregaComboCategoriaTelaIncContaPagar();
		listaBancos = Utilidades.getComboBancos();
		return "/content/financeiro/contaBancaria/incContasPagar.jsf?faces-redirect=true";
	}

	/*
	 * M�todo que abre a p�gina de inclus�o de conta a receber
	 */
	public String abrirPaginaIncContaAReceber() {
		contaAReceber = new Receita();
		contaAReceber.setRecorrencia(new Recorrencia());
		contaAReceber.setCliente(new Cliente());
		carregaComboCategoriaTelaIncContaReceber();
		listaBancos = Utilidades.getComboBancos();
		return "/content/financeiro/contaBancaria/incContasReceber.jsf?faces-redirect=true";
	}

	/*
	 * M�todo que abre a p�gina de Inclus�o da conta
	 */
	public String abrirPaginaIncContaBanc() {
		conta = new ContaBancaria();

		listaBancos = Utilidades.getComboBancos();
		return "/content/financeiro/contaBancaria/incContaBancaria.jsf?faces-redirect=true";
	}

	public void carregaComboCategoriaTelaIncContaPagar() {
		comboCategoriaReceitasEDespesas = new ArrayList<SelectItem>();
		List<CategoriaDespesas> listaCategoriaPagar = carregaListaDespesa();
		for (int i = 0; i < listaCategoriaPagar.size(); i++) {
			comboCategoriaReceitasEDespesas.add(new SelectItem(i,
					listaCategoriaPagar.get(i).getNome()));
		}
	}

	public void carregaComboCategoriaTelaIncContaReceber() {
		comboCategoriaReceitasEDespesas = new ArrayList<SelectItem>();
		List<CategoriaReceita> listaCategoriaReceita = carregaListaReceita();
		for (int i = 0; i < listaCategoriaReceita.size(); i++) {
			comboCategoriaReceitasEDespesas.add(new SelectItem(i,
					listaCategoriaReceita.get(i).getNome()));
		}
	}

	public List<CategoriasFluxoCaixa> carregaListaCategoriaFluxoCaixa() {
		List<CategoriasFluxoCaixa> lista = new ArrayList<CategoriasFluxoCaixa>();

		List<CategoriaReceita> listaReceita = new ArrayList<CategoriaReceita>();
		listaReceita = carregaListaReceita();

		List<CategoriaDespesas> listaDespesa = new ArrayList<CategoriaDespesas>();
		listaDespesa = carregaListaDespesa();

		List<CategoriaTransferencias> listaTransferencia = new ArrayList<CategoriaTransferencias>();
		listaTransferencia.add(new CategoriaTransferencias(
				"Transfer�ncia de Entrada"));
		listaTransferencia.add(new CategoriaTransferencias(
				"Transfer�ncia de Saida"));

		lista.add(new CategoriasFluxoCaixa("Receita", listaReceita));
		lista.add(new CategoriasFluxoCaixa("Despesa", listaDespesa));
		lista.add(new CategoriasFluxoCaixa("Transferencia", listaTransferencia));

		return lista;
	}

	public List<CategoriaDespesas> carregaListaDespesa() {
		List<CategoriaDespesas> listaDespesa = new ArrayList<CategoriaDespesas>();
		listaDespesa.add(new CategoriaDespesas("Impostos"));
		listaDespesa.add(new CategoriaDespesas("Fornecedores"));
		listaDespesa.add(new CategoriaDespesas("Retirada Mensal"));
		listaDespesa.add(new CategoriaDespesas("Sal�rios de funcion�rios"));
		listaDespesa.add(new CategoriaDespesas("�gua"));
		listaDespesa.add(new CategoriaDespesas("Energia El�trica"));
		listaDespesa.add(new CategoriaDespesas("Aluguel"));
		listaDespesa.add(new CategoriaDespesas("Telefone"));
		listaDespesa.add(new CategoriaDespesas("Celular"));
		listaDespesa.add(new CategoriaDespesas("Internet"));
		listaDespesa.add(new CategoriaDespesas("Despesas Banc�rias"));
		listaDespesa.add(new CategoriaDespesas("Despesas Financeiras"));
		listaDespesa.add(new CategoriaDespesas("Honor�rios Cont�beis"));
		listaDespesa.add(new CategoriaDespesas("Despesas com ve�culos"));
		listaDespesa.add(new CategoriaDespesas("Materiais de escrit�rio"));
		listaDespesa.add(new CategoriaDespesas("Gastos com Equipamentos"));
		return listaDespesa;
	}

	public List<CategoriaReceita> carregaListaReceita() {
		List<CategoriaReceita> listaReceita = new ArrayList<CategoriaReceita>();
		listaReceita.add(new CategoriaReceita("Dinheiro"));
		listaReceita.add(new CategoriaReceita("Cheque pr�-datado"));
		listaReceita.add(new CategoriaReceita("Duplicatas a Receber"));
		listaReceita.add(new CategoriaReceita("Cart�o de Cr�dito"));
		listaReceita.add(new CategoriaReceita("Outras receitas"));
		return listaReceita;
	}

	public String download() {

		BoletoViewer boletoViewer = GeradorDeBoleto.boletoCriado();

		byte[] pdfAsBytes = boletoViewer.getPdfAsByteArray();

		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();

		try {

			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition",
					"attachment; filename=boleto.pdf");

			OutputStream output = response.getOutputStream();
			output.write(pdfAsBytes);
			response.flushBuffer();

			FacesContext.getCurrentInstance().responseComplete();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * M�todo que carrega p�gina de edi��o de categorias de fluxo de caixa
	 */
	public String editarCategoriasFluxoCaixa() {
		return "/content/financeiro/fluxoCaixa/categoriasFluxoCaixa.jsf?faces-redirect=true";
	}

	public List<SelectItem> getComboCategoriaReceitasEDespesas() {
		return comboCategoriaReceitasEDespesas;
	}

	public ContaBancaria getConta() {
		return conta;
	}

	public Despesa getContaAPagar() {
		return contaAPagar;
	}

	public Receita getContaAReceber() {
		return contaAReceber;
	}

	// Getters e Setters
	public List<SelectItem> getListaBancos() {
		return listaBancos;
	}

	public List<SelectItem> getListaCarteirasBoleto() {
		return listaCarteirasBoleto;
	}

	public List<CategoriasFluxoCaixa> getListaCategoriaFluxo() {
		return listaCategoriaFluxo;
	}

	public List<ContaBancaria> getListaContas() {
		return listaContas;
	}

	public Long getMascaraNossoNumero() {
		return mascaraNossoNumero;
	}

	public int getNumBancoSelecionado() {
		return numBancoSelecionado;
	}

	public int getNumCategoriaReceitaSelecionado() {
		return numCategoriaReceitaSelecionado;
	}

	public String incluirConta() {
		listaContas = new ArrayList<ContaBancaria>();
		conta.setBanco(listaBancos.get(numBancoSelecionado).getLabel());
		listaContas.add(conta);
		return abrirPaginaConsultaConta();
	}

	public void pegarNomeDoArquivo(FileUploadEvent event) {
		contaAReceber.setCaminhoArquivoAnexo("C:\\images\\"
				+ event.getFile().getFileName());
		System.out.println(event.getFile().getFileName());
	}

	public void setComboCategoriaReceitasEDespesas(
			List<SelectItem> comboCategoriaReceitasEDespesas) {
		this.comboCategoriaReceitasEDespesas = comboCategoriaReceitasEDespesas;
	}

	public void setConta(ContaBancaria conta) {
		this.conta = conta;
	}

	public void setContaAPagar(Despesa contaAPagar) {
		this.contaAPagar = contaAPagar;
	}

	public void setContaAReceber(Receita contaAReceber) {
		this.contaAReceber = contaAReceber;
	}

	public void setListaBancos(List<SelectItem> listaBancos) {
		this.listaBancos = listaBancos;
	}

	public void setListaCarteirasBoleto(List<SelectItem> listaCarteirasBoleto) {
		this.listaCarteirasBoleto = listaCarteirasBoleto;
	}

	public void setListaCategoriaFluxo(
			List<CategoriasFluxoCaixa> listaCategoriaFluxo) {
		this.listaCategoriaFluxo = listaCategoriaFluxo;
	}

	public void setListaContas(List<ContaBancaria> listaContas) {
		this.listaContas = listaContas;
	}

	public void setMascaraNossoNumero(Long mascaraNossoNumero) {
		this.mascaraNossoNumero = mascaraNossoNumero;
	}

	public void setNumBancoSelecionado(int numBancoSelecionado) {
		this.numBancoSelecionado = numBancoSelecionado;
	}

	public void setNumCategoriaReceitaSelecionado(
			int numCategoriaReceitaSelecionado) {
		this.numCategoriaReceitaSelecionado = numCategoriaReceitaSelecionado;
	}

	public void trocaCarteira() {
		if (numBancoSelecionado == 1 || numBancoSelecionado == 4
				|| numBancoSelecionado == 21 || numBancoSelecionado == 33
				|| numBancoSelecionado == 104 || numBancoSelecionado == 151
				|| numBancoSelecionado == 399 || numBancoSelecionado == 409
				|| numBancoSelecionado == 422) {
			listaCarteirasBoleto = Utilidades
					.getCarregaComboCarteirasBoleto(numBancoSelecionado);
		} else {
			numBancoSelecionado = 0;
		}

	}

	/*
	 * M�todo que carrega p�gina de fluxo de caixa
	 */
	public String visualizarFluxoCaixa() {
		listaCategoriaFluxo = new ArrayList<CategoriasFluxoCaixa>();

		listaCategoriaFluxo = carregaListaCategoriaFluxoCaixa();

		return "/content/financeiro/fluxoCaixa/visuFluxoCaixa.jsf?faces-redirect=true";
	}
}
