package br.com.iondax.controller.view.financeiro;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import org.jrimum.bopepo.view.BoletoViewer;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.iondax.controller.boleto.GeradorDeBoleto;
import br.com.iondax.controller.utils.Utilidades;
import br.com.iondax.entities.financeiro.contabancaria.ContaBancaria;
import br.com.iondax.entities.financeiro.contabancaria.transacoes.Recorrencia;
import br.com.iondax.entities.financeiro.contabancaria.transacoes.despesa.Despesa;
import br.com.iondax.entities.financeiro.contabancaria.transacoes.receita.Receita;
import br.com.iondax.entities.financeiro.fluxocaixa.CategoriaTransacao;
import br.com.iondax.entities.financeiro.relatorios.Lancamentos;
import br.com.iondax.entities.fornecedor.Fornecedor;
import br.com.iondax.entities.venda.Cliente;
import br.com.iondax.repositories.financeiro.ICategoriaTransacaoRepositories;
import br.com.iondax.repositories.financeiro.IContaRepositories;
import br.com.iondax.repositories.financeiro.IDespesaRepositories;
import br.com.iondax.repositories.financeiro.IFornecedorRepositories;
import br.com.iondax.repositories.financeiro.ILancamentosRepositories;
import br.com.iondax.repositories.financeiro.IReceitaRepositories;
import br.com.iondax.repositories.financeiro.IRecorrenciaRepositories;
import br.com.iondax.repositories.financeiro.ITransferenciaRepositories;

@Component
@ManagedBean(name = "financeiroBean")
@SessionScoped
public class FinanceiroBean {

	private ContaBancaria conta;
	private Despesa contaAPagar;
	private Receita contaAReceber;
	private List<SelectItem> listaBancos = new ArrayList<SelectItem>();
	
	private List<SelectItem> comboContasCadastradas = new ArrayList<SelectItem>();

	private List<SelectItem> listaCarteirasBoleto = new ArrayList<SelectItem>();
	private List<CategoriaTransacao> listaCategoriaTransacao;
	private List<String> listaCategoriaTransacaoReceitas;
	private List<String> listaCategoriaTransacaoDespesa;
	private List<String> listaCategoriaTransacaoTransferencia;
	private List<SelectItem> comboCategoriaTransacao;
	private List<ContaBancaria> listaContas;
	
	private Lancamentos lancamentos;
	private List<Lancamentos> listaLancamentos;
	private List<Lancamentos> listaLancamentosSelecionados;
	
	private Long mascaraNossoNumero = 99999999999999999L;

	private int numBancoSelecionado = 0;

	private int numCategoriaTransacaoSelecionado = 0;
	
	@Autowired
	private IContaRepositories IContaRepositories;
	
	@Autowired
	private IDespesaRepositories IDespesaRepositories;
	
	@Autowired
	private IReceitaRepositories IReceitaRepositories;
	
	@Autowired
	private ITransferenciaRepositories ITransferenciaRepositories;
	
	@Autowired
	private IRecorrenciaRepositories IRecorrenciaRepositories;
	
	@Autowired
	private IFornecedorRepositories IFornecedorRepositories;
	
	@Autowired
	private ILancamentosRepositories ILancamentosRepositories;
	
	@Autowired
	private ICategoriaTransacaoRepositories ICategoriaTransacaoRepositories;
	
	/*
	 * Método que abre a página de Consulta das conta
	 */
	public String abrirPaginaConsultaConta() {
		conta=new ContaBancaria();
		listaContas = new ArrayList<ContaBancaria>();
		listaContas.addAll(IContaRepositories.findAll());
		setAtivaBotoesEditarExcluirTelaConsultaContas(true);
		return "/content/financeiro/contaBancaria/conContaBancaria.jsf?faces-redirect=true";
	}
	public void ativaBotoesEditarExcluirTelaConcultaConta(){
		setAtivaBotoesEditarExcluirTelaConsultaContas(false);
	}
	private boolean ativaBotoesEditarExcluirTelaConsultaContas;
	
	/*
	 * Método que abre a página de Extrato de Lançamentos
	 */
	public String abrirPaginaExtrato() {
		
		listaLancamentosSelecionados = new ArrayList<Lancamentos>();

		listaLancamentos = new ArrayList<Lancamentos>();
		listaLancamentos = ILancamentosRepositories.findAll();
		if(listaLancamentos.size()>0){
			for(int i=0;i<listaLancamentos.size();i++){
				if(i>0){
					listaLancamentos.get(i).setSaldo(listaLancamentos.get(i-1).getSaldo().add(listaLancamentos.get(i).getValor())); 
				}else{
					listaLancamentos.get(i).setSaldo(listaLancamentos.get(i).getValor());
				}
			}
		}
		setAtivaBotaoEditarTelaExtrato(true);
		setAtivaBotaoExcluirTelaExtrato(true);
		return "/content/financeiro/relatorios/extratoLancamentos.jsf?faces-redirect=true";
	}
	private boolean ativaBotaoEditarTelaExtrato;
	private boolean ativaBotaoExcluirTelaExtrato;
	
	public void ativaBotoesTelaExtrato(){
		if(listaLancamentosSelecionados.size()>1){
			setAtivaBotaoEditarTelaExtrato(true);
			setAtivaBotaoExcluirTelaExtrato(false);
		}else if(listaLancamentosSelecionados.size() == 0){
			setAtivaBotaoEditarTelaExtrato(true);
			setAtivaBotaoExcluirTelaExtrato(true);
		}else{
			setAtivaBotaoEditarTelaExtrato(false);
			setAtivaBotaoExcluirTelaExtrato(false);
		}
	}

	/*
	 * Método que abre a página de Histórico de Movimentação
	 */
	public String abrirPaginaHistoricoMovimentacao() {
		return "/content/financeiro/relatorios/histMovimentacao.jsf?faces-redirect=true";
	}

	/*
	 * Método que abre a página de inclusão de conta a pagar
	 */
	public String abrirPaginaIncContaAPagar() {
		conta = new ContaBancaria();
		listaContas = IContaRepositories.findAll(); 
		comboContasCadastradas = new ArrayList<SelectItem>();
		for (ContaBancaria conta : listaContas) {
			comboContasCadastradas.add(new SelectItem(conta.getId(),conta.getNomeContaBancaria()));
		}
		
		contaAPagar = new Despesa();
		contaAPagar.setRecorrencia(new Recorrencia());
		contaAPagar.setFornecedor(new Fornecedor());
		contaAPagar.setSubTipo(new CategoriaTransacao());
		
		carregaComboCategoriaTelaIncContaPagar();
		
		return "/content/financeiro/contaBancaria/incAltContasPagar.jsf?faces-redirect=true";
	}

	/*
	 * Método que abre a página de inclusão de conta a receber
	 */
	public String abrirPaginaIncContaAReceber() {
		contaAReceber = new Receita();
		contaAReceber.setRecorrencia(new Recorrencia());
		contaAReceber.setCliente(new Cliente());
		carregaComboCategoriaTelaIncContaReceber();
		listaBancos = Utilidades.getComboBancos();
		return "/content/financeiro/contaBancaria/incAltContasReceber.jsf?faces-redirect=true";
	}

	/*
	 * Método que abre a página de Inclusão da conta
	 */
	public String abrirPaginaIncContaBanc() {
		conta = new ContaBancaria();
		listaBancos = Utilidades.getComboBancos();
		numBancoSelecionado = 0;
		return "/content/financeiro/contaBancaria/incAltContaBancaria.jsf?faces-redirect=true";
	}

	public void carregaComboCategoriaTelaIncContaPagar() {
		setComboCategoriaTransacao(new ArrayList<SelectItem>());
		List<CategoriaTransacao> listaCategoriaPagar = carregaListaCategoriasDespesa();
		
		for (int i = 0; i < listaCategoriaPagar.size(); i++) {
			if(listaCategoriaPagar.get(i).getTipo().equalsIgnoreCase("Despesa")){
				comboCategoriaTransacao.add(new SelectItem(listaCategoriaPagar.get(i).getId(),listaCategoriaPagar.get(i).getNome()));
			}
		}
	}

	public void carregaComboCategoriaTelaIncContaReceber() {
		comboCategoriaTransacao = new ArrayList<SelectItem>();
		List<CategoriaTransacao> listaCategoriaReceita = carregaListaCategoriasReceita();
		for (int i = 0; i < listaCategoriaReceita.size(); i++) {
			if(listaCategoriaReceita.get(i).getTipo().equalsIgnoreCase("Receita")){
				for(int j = 0 ; j < listaCategoriaReceita.get(i).getCategoriasTransacao().size();j++){
					comboCategoriaTransacao.add(new SelectItem(i+1,listaCategoriaReceita.get(i).getCategoriasTransacao().get(j)));
				}
			}
		}
	}

	public List<CategoriaTransacao> carregaListaCategoriaFluxoCaixa() {
		
		List<CategoriaTransacao> listaCategoriaTransacao = new ArrayList<CategoriaTransacao>();
		
		List<CategoriaTransacao> listaCategoriaTransacaoTemp = ICategoriaTransacaoRepositories.findAll();
		
		
		listaCategoriaTransacaoReceitas = new ArrayList<String>();
		for(CategoriaTransacao categoria: listaCategoriaTransacaoTemp){
			if(categoria.getTipo().equalsIgnoreCase("Receita")){
				listaCategoriaTransacaoReceitas.add(categoria.getNome());
			}
		}
		listaCategoriaTransacao.add(new CategoriaTransacao("Receitas",listaCategoriaTransacaoReceitas));
		
		
		
		listaCategoriaTransacaoDespesa = new ArrayList<String>();
		for(CategoriaTransacao categoria: listaCategoriaTransacaoTemp){
			if(categoria.getTipo().equalsIgnoreCase("Despesa")){
				listaCategoriaTransacaoDespesa.add(categoria.getNome());
			}
		}
		listaCategoriaTransacao.add(new CategoriaTransacao("Despesas",listaCategoriaTransacaoDespesa));

		
		listaCategoriaTransacaoTransferencia = new ArrayList<String>();
		for(CategoriaTransacao categoria: listaCategoriaTransacaoTemp){
			if(categoria.getTipo().equalsIgnoreCase("transferência")){
				listaCategoriaTransacaoDespesa.add(categoria.getNome());
			}
		}
		listaCategoriaTransacao.add(new CategoriaTransacao("Transferências",listaCategoriaTransacaoTransferencia));
		
		
		return listaCategoriaTransacao;
	}
	
	public List<CategoriaTransacao> carregaListaCategoriasDespesa(){
		return ICategoriaTransacaoRepositories.findByTipo("Despesa");
	}
	
	public List<CategoriaTransacao> carregaListaCategoriasReceita(){
		return ICategoriaTransacaoRepositories.findByTipo("Receita");
	}
	
	public List<CategoriaTransacao> carregaListaCategoriasTransferencia(){
		return ICategoriaTransacaoRepositories.findByTipo("Transferência");
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
	 * Método que carrega página de edição de categorias de fluxo de caixa
	 */
	public String altCategoriasFluxoCaixa() {
		setListaCategoriaTransacao(carregaListaCategoriaFluxoCaixa());
		return "/content/financeiro/fluxoCaixa/altCategoriasFluxoCaixa.jsf?faces-redirect=true";
	}

	public void pegarNomeDoArquivoContaReceber(FileUploadEvent event) {
		contaAReceber.setCaminhoArquivoAnexo("C:\\images\\"
				+ event.getFile().getFileName());
		System.out.println(event.getFile().getFileName());
	}
	
	public void pegarNomeDoArquivoContaPagar(FileUploadEvent event) {
		contaAReceber.setCaminhoArquivoAnexo("C:\\images\\"
				+ event.getFile().getFileName());
		System.out.println(event.getFile().getFileName());
	}

	public void trocaCarteira() {
		if (numBancoSelecionado == 1 || numBancoSelecionado == 33
				|| numBancoSelecionado == 104 || numBancoSelecionado == 151
				|| numBancoSelecionado == 237 || numBancoSelecionado == 341) {
			listaCarteirasBoleto = Utilidades
					.getCarregaComboCarteirasBoleto(numBancoSelecionado);
		} else {
			numBancoSelecionado = 0;
		}

	}

	/*
	 * Método que carrega página de fluxo de caixa
	 */
	public String visualizarFluxoCaixa() {
		listaCategoriaTransacao = carregaListaCategoriaFluxoCaixa();

		return "/content/financeiro/fluxoCaixa/visuFluxoCaixa.jsf?faces-redirect=true";
	}
	
	
	private int comboTipoCategoria;
	
	
	
	
	
	
	
	
	
	
	
	
	//Metodos CRUD
	public String incluirConta() {
		for (SelectItem banco : listaBancos) {
			if(banco.getValue().equals(numBancoSelecionado)){
				conta.setBanco(banco.getLabel());
			}
		}
		IContaRepositories.save(conta);
		
		if(conta.getSaldoAtual().intValue() > 0){
			lancamentos = new Lancamentos(conta,"Receita","Inclusão de conta","Inclusão da conta "+conta.getNomeContaBancaria(),new Date(), conta.getSaldoAtual());
		}else{
			lancamentos = new Lancamentos(conta,"Despesa","Inclusão de conta","Inclusão da conta "+conta.getNomeContaBancaria(),new Date(), conta.getSaldoAtual());
		}
		
		ILancamentosRepositories.save(lancamentos);
		
		return abrirPaginaConsultaConta();
	}
	
	private boolean flagEdicaoConta=false;
	
	public String chamaEditarContaBancaria() {
		flagEdicaoConta = true;
		listaBancos = Utilidades.getComboBancos();
		if(!(conta.getBanco().equals(""))){
			numBancoSelecionado = Integer.parseInt(conta.getBanco().split("-")[0].trim());
		}
		trocaCarteira();
		return "/content/financeiro/contaBancaria/incAltContaBancaria.jsf?faces-redirect=true";
	}
	public String editarContaBancaria(){
		flagEdicaoConta = false;
		IContaRepositories.save(conta);
		return abrirPaginaConsultaConta();
	}
	public void excluirContaBancaria() {
		if(ILancamentosRepositories.findByConta(conta).isEmpty()){;
			IContaRepositories.delete(conta);
			Utilidades.mensagemNaTela("Conta Excluída com sucesso !","sucesso");
		}else{
			Utilidades.mensagemNaTela("Não foi possível excluir a conta, existe\n "
					+ "lançamentos com esta conta, exclua os lançamentos e depois\n"
					+ "tente excluir a conta","erro");
		}
		
		listaContas = new ArrayList<ContaBancaria>();
		listaContas.addAll(IContaRepositories.findAll());
	}
	public String incluirDespesa(){
		
		contaAPagar.setContaBancaria(IContaRepositories.findOne(conta.getId()));
		contaAPagar.getContaBancaria().setSaldoAtual(contaAPagar.getContaBancaria().getSaldoAtual().add(contaAPagar.getValorDespesa().negate()));
		contaAPagar.setDataDespesa(new Date());
		contaAPagar.setValorDespesa(contaAPagar.getValorDespesa().negate());

		for(int i=0;i<comboCategoriaTransacao.size();i++){
			if(comboCategoriaTransacao.get(i).getValue() == contaAPagar.getSubTipo().getId()){
				contaAPagar.getSubTipo().setNome(comboCategoriaTransacao.get(i).getLabel());
			}
		}
		contaAPagar.getSubTipo().setTipo("Despesa");

		IContaRepositories.save(contaAPagar.getContaBancaria());
		
		if(contaAPagar.getRecorrencia().isRepetir()){
			IRecorrenciaRepositories.save(contaAPagar.getRecorrencia());
		}else{
			contaAPagar.setRecorrencia(null);
		}
		if("".equals(contaAPagar.getFornecedor().getNomeFantasia())){
			IFornecedorRepositories.save(contaAPagar.getFornecedor());
		}else{
			contaAPagar.setFornecedor(null);
		}
		
		IDespesaRepositories.save(contaAPagar);

		lancamentos = new Lancamentos(contaAPagar.getContaBancaria(),"Despesa",contaAPagar.getSubTipo().getNome(), contaAPagar.getNomeDespesa(),contaAPagar.getDataDespesa(),contaAPagar.getValorDespesa());
		ILancamentosRepositories.save(lancamentos);
		
		return abrirPaginaExtrato();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//Getters e Setters

	public ContaBancaria getConta() {
		return conta;
	}

	public void setConta(ContaBancaria conta) {
		this.conta = conta;
	}

	public Despesa getContaAPagar() {
		return contaAPagar;
	}

	public void setContaAPagar(Despesa contaAPagar) {
		this.contaAPagar = contaAPagar;
	}

	public Receita getContaAReceber() {
		return contaAReceber;
	}

	public void setContaAReceber(Receita contaAReceber) {
		this.contaAReceber = contaAReceber;
	}

	public List<SelectItem> getListaBancos() {
		return listaBancos;
	}

	public void setListaBancos(List<SelectItem> listaBancos) {
		this.listaBancos = listaBancos;
	}

	public List<SelectItem> getListaCarteirasBoleto() {
		return listaCarteirasBoleto;
	}

	public void setListaCarteirasBoleto(List<SelectItem> listaCarteirasBoleto) {
		this.listaCarteirasBoleto = listaCarteirasBoleto;
	}

	public List<CategoriaTransacao> getListaCategoriaTransacao() {
		return listaCategoriaTransacao;
	}

	public void setListaCategoriaTransacao(
			List<CategoriaTransacao> listaCategoriaTransacao) {
		this.listaCategoriaTransacao = listaCategoriaTransacao;
	}

	public List<String> getListaCategoriaTransacaoReceitas() {
		return listaCategoriaTransacaoReceitas;
	}

	public void setListaCategoriaTransacaoReceitas(
			List<String> listaCategoriaTransacaoReceitas) {
		this.listaCategoriaTransacaoReceitas = listaCategoriaTransacaoReceitas;
	}

	public List<String> getListaCategoriaTransacaoDespesa() {
		return listaCategoriaTransacaoDespesa;
	}

	public void setListaCategoriaTransacaoDespesa(
			List<String> listaCategoriaTransacaoDespesa) {
		this.listaCategoriaTransacaoDespesa = listaCategoriaTransacaoDespesa;
	}

	public List<String> getListaCategoriaTransacaoTransferencia() {
		return listaCategoriaTransacaoTransferencia;
	}

	public void setListaCategoriaTransacaoTransferencia(
			List<String> listaCategoriaTransacaoTransferencia) {
		this.listaCategoriaTransacaoTransferencia = listaCategoriaTransacaoTransferencia;
	}

	public List<SelectItem> getComboCategoriaTransacao() {
		return comboCategoriaTransacao;
	}

	public void setComboCategoriaTransacao(List<SelectItem> comboCategoriaTransacao) {
		this.comboCategoriaTransacao = comboCategoriaTransacao;
	}

	public List<ContaBancaria> getListaContas() {
		return listaContas;
	}

	public void setListaContas(List<ContaBancaria> listaContas) {
		this.listaContas = listaContas;
	}

	public Long getMascaraNossoNumero() {
		return mascaraNossoNumero;
	}

	public void setMascaraNossoNumero(Long mascaraNossoNumero) {
		this.mascaraNossoNumero = mascaraNossoNumero;
	}

	public int getNumBancoSelecionado() {
		return numBancoSelecionado;
	}

	public void setNumBancoSelecionado(int numBancoSelecionado) {
		this.numBancoSelecionado = numBancoSelecionado;
	}

	public int getNumCategoriaTransacaoSelecionado() {
		return numCategoriaTransacaoSelecionado;
	}

	public void setNumCategoriaTransacaoSelecionado(
			int numCategoriaTransacaoSelecionado) {
		this.numCategoriaTransacaoSelecionado = numCategoriaTransacaoSelecionado;
	}

	public IContaRepositories getIContaRepositories() {
		return IContaRepositories;
	}

	public void setIContaRepositories(IContaRepositories iContaRepositories) {
		IContaRepositories = iContaRepositories;
	}

	public boolean isFlagEdicaoConta() {
		return flagEdicaoConta;
	}

	public void setFlagEdicaoConta(boolean flagEdicaoConta) {
		this.flagEdicaoConta = flagEdicaoConta;
	}

	public List<SelectItem> getComboContasCadastradas() {
		return comboContasCadastradas;
	}

	public void setComboContasCadastradas(List<SelectItem> comboContasCadastradas) {
		this.comboContasCadastradas = comboContasCadastradas;
	}

	public IDespesaRepositories getIDespesaRepositories() {
		return IDespesaRepositories;
	}

	public void setIDespesaRepositories(IDespesaRepositories iDespesaRepositories) {
		IDespesaRepositories = iDespesaRepositories;
	}

	public Lancamentos getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(Lancamentos lancamentos) {
		this.lancamentos = lancamentos;
	}

	public List<Lancamentos> getListaLancamentos() {
		return listaLancamentos;
	}

	public void setListaLancamentos(List<Lancamentos> listaLancamentos) {
		this.listaLancamentos = listaLancamentos;
	}
	public boolean isAtivaBotoesEditarExcluirTelaConsultaContas() {
		return ativaBotoesEditarExcluirTelaConsultaContas;
	}
	public void setAtivaBotoesEditarExcluirTelaConsultaContas(
			boolean ativaBotoesEditarExcluirTelaConsultaContas) {
		this.ativaBotoesEditarExcluirTelaConsultaContas = ativaBotoesEditarExcluirTelaConsultaContas;
	}
	public List<Lancamentos> getListaLancamentosSelecionados() {
		return listaLancamentosSelecionados;
	}
	public void setListaLancamentosSelecionados(
			List<Lancamentos> listaLancamentosSelecionados) {
		this.listaLancamentosSelecionados = listaLancamentosSelecionados;
	}
	public boolean isAtivaBotaoEditarTelaExtrato() {
		return ativaBotaoEditarTelaExtrato;
	}
	public void setAtivaBotaoEditarTelaExtrato(boolean ativaBotaoEditarTelaExtrato) {
		this.ativaBotaoEditarTelaExtrato = ativaBotaoEditarTelaExtrato;
	}
	public boolean isAtivaBotaoExcluirTelaExtrato() {
		return ativaBotaoExcluirTelaExtrato;
	}
	public void setAtivaBotaoExcluirTelaExtrato(boolean ativaBotaoExcluirTelaExtrato) {
		this.ativaBotaoExcluirTelaExtrato = ativaBotaoExcluirTelaExtrato;
	}
	public int getComboTipoCategoria() {
		return comboTipoCategoria;
	}
	public void setComboTipoCategoria(int comboTipoCategoria) {
		this.comboTipoCategoria = comboTipoCategoria;
	}


}
