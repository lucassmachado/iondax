package br.com.iondax.controller.view.financeiro;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jrimum.bopepo.view.BoletoViewer;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.iondax.controller.boleto.GeradorDeBoleto;
import br.com.iondax.controller.utils.DataUtils;
import br.com.iondax.controller.utils.Utilidades;
import br.com.iondax.entities.financeiro.contabancaria.ContaBancaria;
import br.com.iondax.entities.financeiro.contabancaria.transacoes.Recorrencia;
import br.com.iondax.entities.financeiro.contabancaria.transacoes.despesa.Despesa;
import br.com.iondax.entities.financeiro.contabancaria.transacoes.receita.Receita;
import br.com.iondax.entities.financeiro.contabancaria.transacoes.transferencia.Transferencia;
import br.com.iondax.entities.financeiro.fluxocaixa.CategoriaTransacao;
import br.com.iondax.entities.financeiro.relatorios.HistMovimentacao;
import br.com.iondax.entities.financeiro.relatorios.Lancamentos;
import br.com.iondax.entities.financeiro.relatorios.LazyLancamentosDataModel;
import br.com.iondax.entities.fornecedor.Fornecedor;
import br.com.iondax.entities.usuario.Usuario;
import br.com.iondax.entities.venda.Cliente;
import br.com.iondax.repositories.financeiro.ICategoriaTransacaoRepositories;
import br.com.iondax.repositories.financeiro.IClienteRepositories;
import br.com.iondax.repositories.financeiro.IContaRepositories;
import br.com.iondax.repositories.financeiro.IDespesaRepositories;
import br.com.iondax.repositories.financeiro.IHistMovimentacaoRepositories;
import br.com.iondax.repositories.financeiro.ILancamentosRepositories;
import br.com.iondax.repositories.financeiro.IReceitaRepositories;
import br.com.iondax.repositories.financeiro.IRecorrenciaRepositories;
import br.com.iondax.repositories.financeiro.ITransferenciaRepositories;
import br.com.iondax.repositories.fornecedor.IFornecedorRepositories;

@Component
@ManagedBean(name = "financeiroBean")
@SessionScoped
public class FinanceiroBean {
	
	private ContaBancaria conta;
	private Despesa contaAPagar;
	private Receita contaAReceber;
	private Transferencia transferencia;
	private Lancamentos lancamentos;
	private CategoriaTransacao categoriaTransacao;
	private CategoriaTransacao categoriaTransacaoSelecionado;
	
	
	private List<SelectItem> listaBancos = new ArrayList<SelectItem>();
	private List<SelectItem> comboContasCadastradas = new ArrayList<SelectItem>();
	private List<SelectItem> listaCarteirasBoleto = new ArrayList<SelectItem>();
	private List<SelectItem> comboCategoriaTransacao;
	
	private List<CategoriaTransacao> listaCategoriaTransacaoReceitas;
	private List<CategoriaTransacao> listaCategoriaTransacaoDespesa;
	private List<CategoriaTransacao> listaCategoriaTransacaoTransferencia;
	private List<ContaBancaria> listaContas;
	private List<CategoriaTransacao> listaCategoriaTransacao;
	private List<Lancamentos> listaLancamentos;
	private List<Lancamentos> listaLancamentosSelecionados;
	
	
	private Long mascaraNossoNumero;
	private Integer mascaraAgencia;
	private Long mascaraConta;
	private Integer mascaraConvenio;
	private Integer mascaraVariacaoDeCarteira;

	private int numBancoSelecionado = 0;

	private int numCategoriaTransacaoSelecionado = 0;
	
	private int numComboEditarCategoriaTransacao = 0;
	
	private int numComboFrequencia = 0;
	
	private int numRadioSelecionadoTipoConta = 0;
	
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
	
	@Autowired
	private IClienteRepositories IClienteRepositories;
	
	@Autowired
	private IHistMovimentacaoRepositories IHistMovimentacaoRepositories;
	
	/*
	 * Método que abre a página de Consulta das conta
	 */
	public String abrirPaginaConsultaConta() {
		conta=new ContaBancaria();
		listaContas = new ArrayList<ContaBancaria>();
		listaContas.addAll(IContaRepositories.findByTipo('E'));
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
	
	private LazyDataModel<Lancamentos> lz;
	
	public String abrirPaginaExtrato() {
		setRenderizaBotaoEnviarBoleto(false);
		listaLancamentosSelecionados = new ArrayList<Lancamentos>();

		listaLancamentos = new ArrayList<Lancamentos>();
		
		listaLancamentos = ILancamentosRepositories.findAll();
		
		lz = new LazyLancamentosDataModel(listaLancamentos,ILancamentosRepositories);
		
		setAtivaBotaoEditarTelaExtrato(true);
		setAtivaBotaoExcluirTelaExtrato(true);
		return "/content/financeiro/relatorios/extratoLancamentos.jsf?faces-redirect=true";
	}
	
	private boolean ativaBotaoEditarTelaExtrato;
	private boolean ativaBotaoExcluirTelaExtrato;
	private boolean renderizaBotaoEnviarBoleto;

	public void ativaBotoesTelaExtrato(){
		if(listaLancamentosSelecionados.size()>1){
			setAtivaBotaoEditarTelaExtrato(true);
			setAtivaBotaoExcluirTelaExtrato(false);
			setRenderizaBotaoEnviarBoleto(false);
		}else if(listaLancamentosSelecionados.size() == 0){
			setAtivaBotaoEditarTelaExtrato(true);
			setAtivaBotaoExcluirTelaExtrato(true);
			setRenderizaBotaoEnviarBoleto(false);
		}else{
			if(listaLancamentosSelecionados.get(0).getTipo().equals("Receita") && !(listaLancamentosSelecionados.get(0).isSituacao())){
				setRenderizaBotaoEnviarBoleto(true);
				setAtivaBotaoEditarTelaExtrato(false);
				setAtivaBotaoExcluirTelaExtrato(false);
			}else{
				setRenderizaBotaoEnviarBoleto(false);
				setAtivaBotaoEditarTelaExtrato(false);
				setAtivaBotaoExcluirTelaExtrato(false);
			}
		}
	}
	
	/*
	 * Método que abre a página de Histórico de Movimentação
	 */
	private List<HistMovimentacao> listaHistorico;
	public String abrirPaginaHistoricoMovimentacao() {
		listaHistorico = IHistMovimentacaoRepositories.findAll();
		return "/content/financeiro/relatorios/histMovimentacao.jsf?faces-redirect=true";
	}

	/*
	 * Método que abre a página de inclusão de conta a pagar
	 */
	public String abrirPaginaIncContaAPagar() {
		
		listaContas = IContaRepositories.findByTipo('E');
		if(listaContas.size()>0){
		
			conta = new ContaBancaria();
			comboContasCadastradas = new ArrayList<SelectItem>();
			for (ContaBancaria conta : listaContas) {
				comboContasCadastradas.add(new SelectItem(conta.getId(),conta.getNomeContaBancaria()));
			}
			
			contaAPagar = new Despesa();
			contaAPagar.setRecorrencia(new Recorrencia());
			contaAPagar.getRecorrencia().setRepetir(false);
			contaAPagar.setFornecedor(new Fornecedor());
			contaAPagar.setSubTipo(new CategoriaTransacao());
			
			carregaComboCategoriaTelaIncContaPagar();
			return "/content/financeiro/contaBancaria/incAltContasPagar.jsf?faces-redirect=true";
		}
		Utilidades.mensagemNaTela("Erro! \nÉ necessário incluir ao menos uma conta do tipo empresa para incluir uma Conta a Pagar", "erro");
		return "";
		
	}

	/*
	 * Método que abre a página de inclusão de conta a receber
	 */
	public String abrirPaginaIncContaAReceber() {
		listaContas = IContaRepositories.findByTipo('E');
		if(listaContas.size()>0){
			conta = new ContaBancaria();
			comboContasCadastradas = new ArrayList<SelectItem>();
			for (ContaBancaria conta : listaContas) {
				comboContasCadastradas.add(new SelectItem(conta.getId(),conta.getNomeContaBancaria()));
			}
			
			contaAReceber = new Receita();
			contaAReceber.setRecorrencia(new Recorrencia());
			contaAReceber.getRecorrencia().setRepetir(false);
			contaAReceber.setCliente(new Cliente());
			contaAReceber.setSubTipo(new CategoriaTransacao());
			
			carregaComboCategoriaTelaIncContaReceber();
			
			return "/content/financeiro/contaBancaria/incAltContasReceber.jsf?faces-redirect=true";
		}
		Utilidades.mensagemNaTela("Erro! \nÉ necessário incluir ao menos uma conta do tipo empresa para incluir uma Conta a Receber", "erro");
		return "";
	}

	/*
	 * Método que abre a página de Inclusão da conta
	 */
	public String abrirPaginaIncContaBanc() {
		setMascaraAgencia(9999);
		setMascaraConta(999999999L);
		setMascaraConvenio(999999);
		setMascaraVariacaoDeCarteira(999);
		setMascaraNossoNumero(99999L);
		
		conta = new ContaBancaria();
		conta.setSaldoAtual(BigDecimal.ZERO);
		listaBancos = Utilidades.getComboBancos();
		numBancoSelecionado = 0;
		numRadioSelecionadoTipoConta = 0;
		return "/content/financeiro/contaBancaria/incAltContaBancaria.jsf?faces-redirect=true";
	}
	public List<SelectItem> getRadiosTipoContaBancaria(){
		List<SelectItem> lista = new ArrayList<SelectItem>();
		lista.add(new SelectItem(1,"Empresarial"));
		lista.add(new SelectItem(2,"Funcionario"));
		return lista;
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
		setComboCategoriaTransacao(new ArrayList<SelectItem>());
		List<CategoriaTransacao> listaCategoriaReceita = carregaListaCategoriasReceita();
		
		for (int i = 0; i < listaCategoriaReceita.size(); i++) {
			if(listaCategoriaReceita.get(i).getTipo().equalsIgnoreCase("Receita")){
				comboCategoriaTransacao.add(new SelectItem(listaCategoriaReceita.get(i).getId(),listaCategoriaReceita.get(i).getNome()));
			}
		}
	}
	
	public void carregaComboCategoriaTelaIncTransferencia() {
		setComboCategoriaTransacao(new ArrayList<SelectItem>());
		List<CategoriaTransacao> listaCategoriaTransferencia = carregaListaCategoriasTransferencia();
		
		for (int i = 0; i < listaCategoriaTransferencia.size(); i++) {
			if(listaCategoriaTransferencia.get(i).getTipo().equalsIgnoreCase("Transferência")){
				comboCategoriaTransacao.add(new SelectItem(listaCategoriaTransferencia.get(i).getId(),listaCategoriaTransferencia.get(i).getNome()));
			}
		}
	}
	
	
	private boolean editarLancamento;
	
	public void editarTransferencia(){
		ILancamentosRepositories.saveAndFlush(transferencia.getLancamentos());
		ITransferenciaRepositories.saveAndFlush(transferencia);
		Utilidades.mensagemNaTela("Transferência atualizada com sucesso", "sucesso");
		setEditarLancamento(false);
	}
	public void editarReceita(){
		ILancamentosRepositories.saveAndFlush(contaAReceber.getLancamentos());
		IReceitaRepositories.saveAndFlush(contaAReceber);
		Utilidades.mensagemNaTela("Receita atualizada com sucesso", "sucesso");
		setEditarLancamento(false);
	}
	public void editarDespesa(){
		ILancamentosRepositories.saveAndFlush(contaAPagar.getLancamentos());
		IDespesaRepositories.saveAndFlush(contaAPagar);
		Utilidades.mensagemNaTela("Despesa atualizada com sucesso", "sucesso");
		setEditarLancamento(false);
	}
	
	public String chamaEditarLancamento(){
		switch(listaLancamentosSelecionados.get(0).getTipo()){
			case "Despesa":
				
				contaAPagar = IDespesaRepositories.findByLancamento(listaLancamentosSelecionados.get(0));
				
				conta = new ContaBancaria();
				comboContasCadastradas = new ArrayList<SelectItem>();
				for (ContaBancaria conta : listaContas) {
					comboContasCadastradas.add(new SelectItem(conta.getId(),conta.getNomeContaBancaria()));
				}
				
				carregaComboCategoriaTelaIncContaPagar();
				
				return "/content/financeiro/contaBancaria/incAltContasPagar.jsf?faces-redirect=true";
			case "Receita":
				Long il = listaLancamentosSelecionados.get(0).getId();
				Lancamentos l = ILancamentosRepositories.findOne(il);
				
				contaAReceber = new Receita(IReceitaRepositories.findByLancamento(l) );
					
				conta = new ContaBancaria();
				comboContasCadastradas = new ArrayList<SelectItem>();
				for (ContaBancaria conta : listaContas) {
					comboContasCadastradas.add(new SelectItem(conta.getId(),conta.getNomeContaBancaria()));
				}
				
				carregaComboCategoriaTelaIncContaReceber();
				
				return "/content/financeiro/contaBancaria/incAltContasReceber.jsf?faces-redirect=true";
			case "Transferência":
				
				transferencia = ITransferenciaRepositories.findByLancamento(listaLancamentosSelecionados.get(0));
				
				
				List<ContaBancaria> listaContasEmpresa = IContaRepositories.findByTipo('E');
				List<ContaBancaria> listaContas = IContaRepositories.findAll();
				
				listaContaTransferenciaOrigem = new ArrayList<SelectItem>();
				for(int i=0;i<listaContasEmpresa.size();i++){
					listaContaTransferenciaOrigem.add(new SelectItem(listaContasEmpresa.get(i).getId(),listaContasEmpresa.get(i).getNomeContaBancaria()));
				}
				
				if(listaContas.size()>0){
					listaContaTransferenciaDestino = new ArrayList<SelectItem>();
					for(int i=0;i<listaContas.size();i++){
						listaContaTransferenciaDestino.add(new SelectItem(listaContas.get(i).getId(),listaContas.get(i).getNomeContaBancaria()));
					}
				}
				
				carregaComboCategoriaTelaIncTransferencia();
				return "/content/financeiro/contaBancaria/incAltTransferencias.jsf?faces-redirect=true";
		}
		
		return "";
	}
	public void excluirLancamentosSelecionados(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		HistMovimentacao hist = new HistMovimentacao();
		for(Lancamentos l:listaLancamentosSelecionados){
			switch(l.getTipo()){
				case "Despesa":
					ILancamentosRepositories.saveAndFlush(l);
					Despesa despesa = IDespesaRepositories.findByLancamento(l);
					IDespesaRepositories.delete(despesa.getId());
					ILancamentosRepositories.delete(l.getId());
					
					Utilidades.mensagemNaTela("Lançamento " + despesa.getNomeDespesa() + " excluído com sucesso", "sucesso");
					
					hist = new HistMovimentacao(usuario, "Deleção", "Deleção do registro " + lancamentos.getNomeTransacao(), lancamentos.getValor(), new Date());
					
					break;
				case "Receita":
//					ILancamentosRepositories.saveAndFlush(l);
					Receita receitas = IReceitaRepositories.findByLancamento(l);
					IReceitaRepositories.saveAndFlush(receitas);
					IReceitaRepositories.delete(receitas.getId());
					ILancamentosRepositories.delete(l.getId());
					
					Utilidades.mensagemNaTela("Lançamento " + receitas.getNomeReceita() + " excluído com sucesso", "sucesso");
					
					hist = new HistMovimentacao(usuario, "Deleção", "Deleção do registro " + lancamentos.getNomeTransacao(), lancamentos.getValor(), new Date());
					break;
				case "Transferência":
					ILancamentosRepositories.saveAndFlush(l);
					Transferencia t = ITransferenciaRepositories.findByLancamento(l);
					ITransferenciaRepositories.delete(t.getId());
					ILancamentosRepositories.delete(l.getId());
					
					Utilidades.mensagemNaTela("Lançamento "+ t.getNomeTransferencia() +"  excluído com sucesso", "sucesso");
					
					hist = new HistMovimentacao(usuario, "Deleção", "Deleção do registro " + lancamentos.getNomeTransacao(), lancamentos.getValor(), new Date());
					break;
			}
		}
	}
	
	private int ano;
	
	public List<SelectItem> getListaAnos(){
		
		List<SelectItem> listinhaAnos = new ArrayList<SelectItem>();
		
		Date d = new Date();
		d = ILancamentosRepositories.findMaxYear();
		Calendar calendarBanco = Calendar.getInstance();
		calendarBanco.setTime(d);
		
		
		Date dAtual = new Date();
		Calendar calendarAtual = Calendar.getInstance();
		calendarAtual.setTime(dAtual);
		int anoAtual = calendarAtual.get(Calendar.YEAR);
		
		if(calendarBanco.get(Calendar.YEAR) - anoAtual > 0){
			for(int i=0;i<=calendarBanco.get(Calendar.YEAR) -anoAtual;i++){
				listinhaAnos.add(new SelectItem(anoAtual+i,""+(anoAtual+i)));
			}
		}else if(calendarBanco.get(Calendar.YEAR)  - anoAtual < 0){
			for(int i=0;i<=anoAtual-calendarBanco.get(Calendar.YEAR) ;i++){
				listinhaAnos.add(new SelectItem(calendarBanco.get(Calendar.YEAR) +i,""+calendarBanco.get(Calendar.YEAR) +i));
			}
		}else{
			listinhaAnos.add(new SelectItem(anoAtual,""+anoAtual));
		}
		
		return listinhaAnos;
	}

	public void atualizaTabelaFluxoCaixa(){
		listaCategoriaTransacao = carregaListaCategoriaFluxoCaixa();
	}
	
	public List<CategoriaTransacao> carregaListaCategoriaFluxoCaixa() {
		
		List<CategoriaTransacao> listaCategoriaTransacao = new ArrayList<CategoriaTransacao>();
		
		List<CategoriaTransacao> listaCategoriaTransacaoTemp = ICategoriaTransacaoRepositories.findAll();
		
		listaCategoriaTransacaoReceitas = new ArrayList<CategoriaTransacao>();
		for(CategoriaTransacao categoria: listaCategoriaTransacaoTemp){
			if(categoria.getTipo().equalsIgnoreCase("Receita")){
				listaCategoriaTransacaoReceitas.add(categoria);
			}
		}
		listaCategoriaTransacao.add(new CategoriaTransacao("Receita",listaCategoriaTransacaoReceitas,ILancamentosRepositories, ""+ano));
		
		
		
		listaCategoriaTransacaoDespesa = new ArrayList<CategoriaTransacao>();
		for(CategoriaTransacao categoria: listaCategoriaTransacaoTemp){
			if(categoria.getTipo().equalsIgnoreCase("Despesa")){
				listaCategoriaTransacaoDespesa.add(categoria);
			}
		}
		listaCategoriaTransacao.add(new CategoriaTransacao("Despesa",listaCategoriaTransacaoDespesa,ILancamentosRepositories,""+ano));

		
		listaCategoriaTransacaoTransferencia = new ArrayList<CategoriaTransacao>();
		for(CategoriaTransacao categoria: listaCategoriaTransacaoTemp){
			if(categoria.getTipo().equalsIgnoreCase("Transferência")){
				listaCategoriaTransacaoTransferencia.add(categoria);
			}
		}
		listaCategoriaTransacao.add(new CategoriaTransacao("Transferência",listaCategoriaTransacaoTransferencia,ILancamentosRepositories,""+ano));
		
		
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

	public List<CategoriaTransacao> carregaListaCategorias(){
		listaCategoriaTransacao = new ArrayList<CategoriaTransacao>();

		switch(numComboEditarCategoriaTransacao){
			case 1:
				listaCategoriaTransacao = carregaListaCategoriasReceita();
				break;
			case 2:
				listaCategoriaTransacao = carregaListaCategoriasDespesa();
				break;
			case 3:
				listaCategoriaTransacao = carregaListaCategoriasTransferencia();
				break;
			default:
				listaCategoriaTransacao = ICategoriaTransacaoRepositories.findAll();
				break;
		}
		setCategoriaTransacao(new CategoriaTransacao());
		return listaCategoriaTransacao;
	}
	
	public void incluirCategoriaTransacao(){
		
		if (numComboEditarCategoriaTransacao != 0) {
			
			boolean checkaInclusao=false;
			
			switch (numComboEditarCategoriaTransacao) {
				case 1:
					List<CategoriaTransacao> listaReceitas = carregaListaCategoriasReceita();
					for(CategoriaTransacao c:listaReceitas){
						if(c.getNome().equalsIgnoreCase(categoriaTransacao.getNome())){
							checkaInclusao = true;
							break;
						}
					}
					if(checkaInclusao){
						Utilidades.mensagemNaTela("Erro ao tentar incluir. \nJá existe uma categoria do tipo 'Receita' com este nome"
								+ " por favor digite outro nome.",
								"erro");
					}else{
						getCategoriaTransacao().setTipo("Receita");
						ICategoriaTransacaoRepositories.save(categoriaTransacao);
						listaCategoriaTransacao = carregaListaCategoriasReceita();
						Utilidades.mensagemNaTela("Categoria adicionada com sucesso","sucesso");
					}
					break;
				
				case 2:
					List<CategoriaTransacao> listaDespesa = carregaListaCategoriasDespesa();
					for(CategoriaTransacao c:listaDespesa){
						if(c.getNome().equalsIgnoreCase(categoriaTransacao.getNome())){
							checkaInclusao = true;
							break;
						}
					}
					if(checkaInclusao){
						Utilidades.mensagemNaTela("Erro ao tentar incluir. \nJá existe uma categoria do tipo 'Despesa' com este nome"
								+ " por favor digite outro nome.",
								"erro");
					}else{
						getCategoriaTransacao().setTipo("Despesa");
						ICategoriaTransacaoRepositories.save(categoriaTransacao);
						listaCategoriaTransacao = carregaListaCategoriasDespesa();
						Utilidades.mensagemNaTela("Categoria adicionada com sucesso","sucesso");
					}
					break;
				
				case 3:
					Utilidades.mensagemNaTela("Erro ao tentar incluir. \nNão é possível incluir "
							+ "ou excluir um novo tipo de categoria 'Transferência'",
						"erro");
			}
			categoriaTransacao = new CategoriaTransacao();
			categoriaTransacaoSelecionado = new CategoriaTransacao();
			
			numComboEditarCategoriaTransacao = 0;
		} else {
			Utilidades.mensagemNaTela(
							"Erro ao tentar adicionar.\nPor favor, selecione um tipo de categoria!",
							"erro");
		}
	}
	public void excluirCategoriaTransacao(){

		boolean temRegistro = false;
		for(Lancamentos lan : ILancamentosRepositories.findAll()){
			if(lan.getSubTipo().equals(categoriaTransacaoSelecionado.getNome())){
				temRegistro = true;
				break;
			}
		}
		if(temRegistro){
			Utilidades.mensagemNaTela("Erro ao tentar excluir.\nExistem Lançamentos com essa categoria."
					+ "\nDelete o Registro de Lançamentos e depois tente apagar a categoria", "erro");
		
		}else if(categoriaTransacaoSelecionado.getTipo().equals("Transferência") ){
			
			Utilidades.mensagemNaTela("Esta categoria não pode ser excluida. Ela é fundamental "
					+ "para o funcionamento do sistema", "erro");
		
		}else{
			ICategoriaTransacaoRepositories.delete(categoriaTransacaoSelecionado);
			categoriaTransacaoSelecionado = new CategoriaTransacao();
			listaCategoriaTransacao = ICategoriaTransacaoRepositories.findAll();
			numComboEditarCategoriaTransacao = 0;
			Utilidades.mensagemNaTela("Categoria Excluida com sucesso", "sucesso");
		}
		
	}
	
	public void ativaBotaExcluirCategoria(){
		setAtivaDesativaBotaExcluirCategoria(false);
	}
	
	private boolean ativaDesativaBotaExcluirCategoria = true;

	
	
	
	
	
	
	
	public String gerarBoleto() {
		
//		contaAReceber.getContaBancaria().getBanco();
//		contaAReceber.getContaBancaria().getCarteira();
		
		
		BoletoViewer boletoViewer = GeradorDeBoleto.boletoCriado(contaAReceber);

		byte[] pdfAsBytes = boletoViewer.getPdfAsByteArray();

		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

		try {

			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition","attachment; filename=boleto.pdf");

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
	public String abrirPaginaIncAltCategoriasFluxoCaixa() {
		categoriaTransacao = new CategoriaTransacao();
		categoriaTransacaoSelecionado = new CategoriaTransacao();
		setListaCategoriaTransacao(carregaListaCategorias());
		return "/content/financeiro/fluxoCaixa/incAltCategoriasFluxoCaixa.jsf?faces-redirect=true";
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
				|| numBancoSelecionado == 104) {
			listaCarteirasBoleto = Utilidades.getCarregaComboCarteirasBoleto(numBancoSelecionado);
			switch(numBancoSelecionado){
			case 1:
				trocaCarteiraBB();
				break;
			case 33:
				trocaCarteiraSantander();
				break;
			case 104:
				trocaCarteiraCXEconomica();
				break;
			}
		} else {
			numBancoSelecionado = 0;
		}

	}
	
	public void trocaCarteiraBB(){
		if(conta.getCarteira() == null || (!conta.getCarteira().equals("16") && !conta.getCarteira().equals("18"))){
			conta.setCarteira("16");
		}
		if(conta.getCarteira().equals("16")){
			setMascaraAgencia(9999);
			setMascaraConta(999999999L);
			setMascaraConvenio(999999);
			setMascaraVariacaoDeCarteira(999);
			setMascaraNossoNumero(99999L);
		}else if(conta.getCarteira().equals("18")){
			setMascaraAgencia(9999);
			setMascaraConta(99999999L);
			setMascaraConvenio(999999);
			setMascaraVariacaoDeCarteira(999);
			setMascaraNossoNumero(99999999999L);
		}
	}
	
	public void trocaCarteiraSantander(){
		if(conta.getCarteira() == null || (!conta.getCarteira().equals("COB") && !conta.getCarteira().equals("CSR"))){
			conta.setCarteira("COB");
		}
		if(conta.getCarteira().equals("COB")){
			setMascaraAgencia(999);
			setMascaraConta(99999999L);
			setMascaraNossoNumero(9999999L);
		}else if(conta.getCarteira().equals("CSR")){
			setMascaraAgencia(99999);
			setMascaraConta(9999999999L);
			setMascaraNossoNumero(999999999999L);
		}
	}
	
	public void trocaCarteiraCXEconomica(){
		if(conta.getCarteira() == null || (!conta.getCarteira().equals("CS") && !conta.getCarteira().equals("SR") && !conta.getCarteira().equals("SR14"))){
			conta.setCarteira("CS");
		}
		if(conta.getCarteira().equals("CS")){
			setMascaraAgencia(999);
			setMascaraConta(999999999L);
			setMascaraNossoNumero(999999999L);
		}else if(conta.getCarteira().equals("SR")){
			setMascaraAgencia(9999);
			setMascaraConta(999999999L);
			setMascaraNossoNumero(99999999L);
		}else if(conta.getCarteira().equals("SR14")){
			setMascaraAgencia(9999);
			setMascaraConta(99999L);
			setMascaraNossoNumero(99999999999999L);
		}
	}

	/*
	 * Método que carrega página de fluxo de caixa
	 */
	public String visualizarFluxoCaixa() {
		setAno(0);
		listaCategoriaTransacao = carregaListaCategoriaFluxoCaixa();
		
		return "/content/financeiro/fluxoCaixa/visuFluxoCaixa.jsf?faces-redirect=true";
	}
	
	
	public boolean isVerificaTipoConta(){
		if(getNumRadioSelecionadoTipoConta() == 1){
			conta.setTipo('E');
			return true;
		}
		conta.setTipo('F');
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Metodos CRUD
	public String incluirConta() {
		
		ContaBancaria contaExistente = IContaRepositories.findByNomeAndBanco(conta.getNomeContaBancaria(),conta.getBanco());
		if(contaExistente == null){

			for (SelectItem banco : listaBancos) {
				if(banco.getValue().equals(numBancoSelecionado)){
					conta.setBanco(banco.getLabel());
					break;
				}
			}
			IContaRepositories.save(conta);
			if(conta.getTipo() == 'E'){
				if(conta.getSaldoAtual().intValue() > 0){
					lancamentos = new Lancamentos(conta,"Receita","Inclusão de conta","Inclusão da conta "+conta.getNomeContaBancaria(),new Date(), conta.getSaldoAtual(),true);
				}else{
					lancamentos = new Lancamentos(conta,"Despesa","Inclusão de conta","Inclusão da conta "+conta.getNomeContaBancaria(),new Date(), conta.getSaldoAtual(),true);
				}
			
				ILancamentosRepositories.save(lancamentos);
			}
			
			return abrirPaginaConsultaConta();
		}
		
		Utilidades.mensagemNaTela("Já existe uma conta com esse nome desse banco, por favor digite ou nome para a sua conta.", "erro");
		return "";
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
	
	public List<String> carregaClientesInclusaoReceita(String query){
		List<Cliente> listaClientes = IClienteRepositories.findByName(query.toLowerCase());
		List<String> listaResultado = new ArrayList<String>();
		for(Cliente c : listaClientes){
			listaResultado.add(c.getNome());
		}
		return listaResultado;
	}
	public List<String> carregaFornecedoresInclusaoDespesa(String query){
		List<Fornecedor> listaFornecedores = IFornecedorRepositories.findByName(query.toLowerCase());
		List<String> listaResultado = new ArrayList<String>();
		for(Fornecedor f : listaFornecedores){
			listaResultado.add(f.getNomeFantasia());
		}
		return listaResultado;
	}
	
	public String incluirDespesa(){
		
		contaAPagar.setContaBancaria(IContaRepositories.findOne(conta.getId()));
		contaAPagar.getContaBancaria().setSaldoAtual(contaAPagar.getContaBancaria().getSaldoAtual().add(contaAPagar.getValorDespesa().negate()));
		contaAPagar.setDataDespesa(new Date());
		contaAPagar.setValorDespesa(contaAPagar.getValorDespesa().negate());

		for(int i=0;i<comboCategoriaTransacao.size();i++){
			if(comboCategoriaTransacao.get(i).getValue() == contaAPagar.getSubTipo().getId()){
				contaAPagar.getSubTipo().setNome(comboCategoriaTransacao.get(i).getLabel());
				break;
			}
		}
		contaAPagar.getSubTipo().setTipo("Despesa");
		IContaRepositories.save(contaAPagar.getContaBancaria());
		IFornecedorRepositories.save(contaAPagar.getFornecedor());
		
		//4675-9790
		//O segredo está no lançamento
		
		//Despesa tem repetição ?
		if(contaAPagar.getRecorrencia().isRepetir()){
			incluirRecorrenciaDespesa();
		}else{
			contaAPagar.setRecorrencia(new Recorrencia());
			IRecorrenciaRepositories.save(contaAPagar.getRecorrencia());
			lancamentos = new Lancamentos(contaAPagar.getContaBancaria(),"Despesa",contaAPagar.getSubTipo().getNome(), contaAPagar.getNomeDespesa(),contaAPagar.getDataDespesa(),contaAPagar.getValorDespesa(),contaAPagar.isSituacao());
			contaAPagar.setLancamentos(lancamentos);
			ILancamentosRepositories.save(contaAPagar.getLancamentos());
			IDespesaRepositories.save(contaAPagar);
			
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			
			HistMovimentacao hist = new HistMovimentacao(usuario,"Inclusão","Inclusão de Despesa "+contaAPagar.getNomeDespesa() ,contaAPagar.getValorDespesa(),new Date());
			IHistMovimentacaoRepositories.save(hist);
		}
		return abrirPaginaExtrato();
	}
	
	
	
	
	public void incluirRecorrenciaDespesa(){
		
		for(int i = 0 ;i<getComboFrequenciaRepeticao().size();i++){
			if(Integer.valueOf(getComboFrequenciaRepeticao().get(i).getValue().toString()) == numComboFrequencia){
				contaAPagar.getRecorrencia().setFrequencia(getComboFrequenciaRepeticao().get(i).getLabel());
				break;
			}
		}
		
		//Salva repetição na base
		IRecorrenciaRepositories.save(contaAPagar.getRecorrencia());
	
		Date d1 = contaAPagar.getDataVencimento();
		//Faz repetição em despesa e lançamentos
		for(int i=0;i<contaAPagar.getRecorrencia().getQtdOcorrencias();i++){
			
			//Verifica se a primeira Despesa
			if(i==0){
				if(contaAPagar.isSituacao()){
					lancamentos = new Lancamentos(contaAPagar.getContaBancaria(),"Despesa",contaAPagar.getSubTipo().getNome(), contaAPagar.getNomeDespesa(),contaAPagar.getDataDespesa(),contaAPagar.getValorDespesa(),contaAPagar.isSituacao());
					contaAPagar.setSituacao(false);
				}else{
					lancamentos = new Lancamentos(contaAPagar.getContaBancaria(),"Despesa",contaAPagar.getSubTipo().getNome(), contaAPagar.getNomeDespesa(),contaAPagar.getDataVencimento(),contaAPagar.getValorDespesa(),contaAPagar.isSituacao());
				}
			}else{
				
				Calendar c = Calendar.getInstance();
				c.setTime(d1);
				switch(numComboFrequencia){
				
					case 1: 
						c.set(Calendar.DATE, c.get(Calendar.DATE) + i);
						break;
					case 7: 
						c.set(Calendar.WEEK_OF_YEAR, c.get(Calendar.WEEK_OF_YEAR) + i);
						break;
					case 30:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + i);
						break;
					case 60:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*2));
						break;
					case 90:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*3));
						break;
					case 120:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*4));
						break;
					case 150:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*5));
						break;
					case 180:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*6));
						break;
					case 210:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*7));
						break;
					case 240:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*8));
						break;
					case 270:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*9));
						break;
					case 300:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*10));
						break;
					case 330:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*11));
						break;
					case 365:
						c.set(Calendar.YEAR, c.get(Calendar.YEAR)*i);
						break;
				}
				contaAPagar.setDataVencimento(c.getTime());
				
				lancamentos = new Lancamentos(contaAPagar.getContaBancaria(),"Despesa",contaAPagar.getSubTipo().getNome(), contaAPagar.getNomeDespesa(),contaAPagar.getDataVencimento(),contaAPagar.getValorDespesa(),contaAPagar.isSituacao());
			}
			contaAPagar.setLancamentos(lancamentos);
			ILancamentosRepositories.save(contaAPagar.getLancamentos());
			
			IContaRepositories.save(contaAPagar.getContaBancaria());
			
			if("".equals(contaAPagar.getFornecedor().getNomeFantasia())){
				IFornecedorRepositories.save(contaAPagar.getFornecedor());
			}
			
			IDespesaRepositories.save(contaAPagar);
			
			
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			
			HistMovimentacao hist = new HistMovimentacao(usuario,"Inclusão ("+(i+1)+"/"+contaAPagar.getRecorrencia().getQtdOcorrencias()+")","Inclusão de Despesa "+contaAPagar.getNomeDespesa() ,contaAPagar.getValorDespesa(),new Date());
			IHistMovimentacaoRepositories.save(hist);
			
			//re-instancia 
			contaAPagar = new Despesa(contaAPagar);
		}
	}
	
	public String incluirReceita(){
		
		contaAReceber.setContaBancaria(IContaRepositories.findOne(conta.getId()));
		contaAReceber.getContaBancaria().setSaldoAtual(contaAReceber.getContaBancaria().getSaldoAtual().add(contaAReceber.getValorReceita()));
		contaAReceber.setDataReceita(new Date());
		contaAReceber.setValorReceita(contaAReceber.getValorReceita());

		for(int i=0;i<comboCategoriaTransacao.size();i++){
			if(comboCategoriaTransacao.get(i).getValue() == contaAReceber.getSubTipo().getId()){
				contaAReceber.getSubTipo().setNome(comboCategoriaTransacao.get(i).getLabel());
				break;
			}
		}
		contaAReceber.getSubTipo().setTipo("Receita");
		
		IContaRepositories.save(contaAReceber.getContaBancaria());
		
		IClienteRepositories.save(contaAReceber.getCliente());
		
		
		//O segredo está no lançamento
		
		//Receita tem repetição ?
		if(contaAReceber.getRecorrencia().isRepetir()){
			incluirRecorrenciaReceita();
		}else{
			contaAReceber.setRecorrencia(new Recorrencia());
			IRecorrenciaRepositories.save(contaAReceber.getRecorrencia());
			lancamentos = new Lancamentos(contaAReceber.getContaBancaria(),"Receita",contaAReceber.getSubTipo().getNome(), contaAReceber.getNomeReceita(),contaAReceber.getDataReceita(),contaAReceber.getValorReceita(),contaAReceber.isSituacao());
			contaAReceber.setLancamentos(lancamentos);
			ILancamentosRepositories.save(contaAReceber.getLancamentos());
			contaAReceber.setSituacao(false);
			IReceitaRepositories.save(contaAReceber);
			
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			HistMovimentacao hist = new HistMovimentacao(usuario,"Inclusão","Inclusão de Receita "+contaAReceber.getNomeReceita() ,contaAReceber.getValorReceita(),new Date());
			IHistMovimentacaoRepositories.save(hist);
			
		}
		return abrirPaginaExtrato();
	}
	
	public void incluirRecorrenciaReceita(){
		for(int i = 0 ;i<getComboFrequenciaRepeticao().size();i++){
			if(Integer.valueOf(getComboFrequenciaRepeticao().get(i).getValue().toString()) == numComboFrequencia){
				contaAReceber.getRecorrencia().setFrequencia(getComboFrequenciaRepeticao().get(i).getLabel());
				break;
			}
		}
		
		//Salva repetição na base
		IRecorrenciaRepositories.save(contaAReceber.getRecorrencia());
	
		Date d1 = contaAReceber.getDataVencimento();
		//Faz repetição em despesa e lançamentos
		for(int i=0;i<contaAReceber.getRecorrencia().getQtdOcorrencias();i++){
			
			//Verifica se a primeira Despesa
			if(i==0){
				if(contaAReceber.isSituacao()){
					lancamentos = new Lancamentos(contaAReceber.getContaBancaria(),"Receita",contaAReceber.getSubTipo().getNome(), contaAReceber.getNomeReceita(),contaAReceber.getDataReceita(),contaAReceber.getValorReceita(),contaAReceber.isSituacao());
					contaAReceber.setSituacao(false);
				}else{
					lancamentos = new Lancamentos(contaAReceber.getContaBancaria(),"Receita",contaAReceber.getSubTipo().getNome(), contaAReceber.getNomeReceita(),contaAReceber.getDataVencimento(),contaAReceber.getValorReceita(),contaAReceber.isSituacao());
				}
				
			}else{
				
				Calendar c = Calendar.getInstance();
				c.setTime(d1);
				
				switch(numComboFrequencia){
					
					case 1: 
						c.set(Calendar.DATE, c.get(Calendar.DATE) + i);
						break;
					case 7: 
						c.set(Calendar.WEEK_OF_YEAR, c.get(Calendar.WEEK_OF_YEAR) + i);
						break;
					case 30:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH)+ i );
						break;
					case 60:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*2));
						break;
					case 90:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*3));
						break;
					case 120:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*4));
						break;
					case 150:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*5));
						break;
					case 180:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*6));
						break;
					case 210:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*7));
						break;
					case 240:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*8));
						break;
					case 270:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*9));
						break;
					case 300:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*10));
						break;
					case 330:
						c.set(Calendar.MONTH, c.get(Calendar.MONTH) + (i*11));
						break;
					case 365:
						c.set(Calendar.YEAR, c.get(Calendar.YEAR)+i);
						break;
				}
				
				contaAReceber.setDataVencimento(c.getTime());
				
				lancamentos = new Lancamentos(contaAReceber.getContaBancaria(),"Receita",contaAReceber.getSubTipo().getNome(), contaAReceber.getNomeReceita(),contaAReceber.getDataVencimento(),contaAReceber.getValorReceita(),contaAReceber.isSituacao());
				
			}
			contaAReceber.setLancamentos(lancamentos);
			ILancamentosRepositories.save(contaAReceber.getLancamentos());
			IContaRepositories.save(contaAReceber.getContaBancaria());
			IClienteRepositories.save(contaAReceber.getCliente());
			IReceitaRepositories.save(contaAReceber);
			
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			HistMovimentacao hist = new HistMovimentacao(usuario,"Inclusão ("+(i+1)+"/"+contaAReceber.getRecorrencia().getQtdOcorrencias()+")","Inclusão de Receita "+contaAReceber.getNomeReceita() ,contaAReceber.getValorReceita(),new Date());
			IHistMovimentacaoRepositories.save(hist);
			
			//re-instancia 
			contaAReceber = new Receita(contaAReceber);
		}
	}
	
	public String incluirTransferencia(){
		
		for(int i=0;i<getComboCategoriaTransacao().size();i++){
			
			if(getComboCategoriaTransacao().get(i).getValue().equals(transferencia.getSubTipo().getId())){
				transferencia.getSubTipo().setNome(getComboCategoriaTransacao().get(i).getLabel());
				break;
			}
			
		}
		
		transferencia.setContaOrigem(IContaRepositories.findOne(transferencia.getContaOrigem().getId()));
		transferencia.setContaDestino(IContaRepositories.findOne(transferencia.getContaDestino().getId()));
		
		if(transferencia.getSubTipo().getNome().equals("Transferência de entrada")){
			if(transferencia.getContaDestino().getTipo() == 'F'){
				Utilidades.mensagemNaTela("Erro. \nNão é possível retirar saldo da conta de um funcionário"
						+ " apenas de uma outra conta da empresa.", "erro");
			}else if(transferencia.getContaOrigem().getId() == transferencia.getContaDestino().getId()){
				Utilidades.mensagemNaTela("Erro. \nNão é possível transferir para a mesma conta", "erro");
			}else{
				//Remove saldo da conta Destino
				transferencia.getContaDestino().setSaldoAtual(transferencia.getContaDestino().getSaldoAtual().subtract(transferencia.getValorTransferencia()));
				//Adiciona saldo na conta origem
				transferencia.getContaOrigem().setSaldoAtual(transferencia.getContaOrigem().getSaldoAtual().add(transferencia.getValorTransferencia()));
				lancarSalvarTransferencia();
				
				FacesContext fc = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
				Usuario usuario = (Usuario) session.getAttribute("usuario");
				HistMovimentacao hist = new HistMovimentacao(usuario,"Inclusão","Inclusão de Transferência",transferencia.getValorTransferencia(),new Date());
				IHistMovimentacaoRepositories.save(hist);
				
				return abrirPaginaExtrato();
			}
		}else if(transferencia.getSubTipo().getNome().equals("Transferência de saída")){
			//Remove saldo da conta origem
			transferencia.getContaOrigem().setSaldoAtual(transferencia.getContaOrigem().getSaldoAtual().subtract(transferencia.getValorTransferencia()));
			//adiciona saldo da conta destino
			transferencia.getContaDestino().setSaldoAtual(transferencia.getContaDestino().getSaldoAtual().add(transferencia.getValorTransferencia()));
		
			lancarSalvarTransferencia();
			
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			HistMovimentacao hist = new HistMovimentacao(usuario,"Inclusão","Inclusão de Transferência",transferencia.getValorTransferencia(),new Date());
			IHistMovimentacaoRepositories.save(hist);
			
			return abrirPaginaExtrato();
		}
		return "";
	}
	
	public void lancarSalvarTransferencia(){
		Lancamentos l = new Lancamentos();
		
		l.setContaBancaria(transferencia.getContaOrigem());
		IContaRepositories.save(l.getContaBancaria());

		l.setTipo("Transferência");
		
		l.setSubTipo(transferencia.getSubTipo().getNome());
		
		l.setNomeTransacao(transferencia.getNomeTransferencia());
		
		if(transferencia.getSubTipo().getNome().contains("Entrada")){
			l.setValor(transferencia.getValorTransferencia());
		}else{
			l.setValor(transferencia.getValorTransferencia().negate());
		}
		if(l.getDataTransacao().compareTo(new Date()) == 0){
			l.setSituacao(true);
		}else{
			l.setSituacao(false);
		}
		transferencia.setLancamentos(l);
		ILancamentosRepositories.save(transferencia.getLancamentos());
		ITransferenciaRepositories.save(transferencia);
		Utilidades.mensagemNaTela("Transferência realizada com sucesso", "sucesso");
	}
	
	
	public String abrirPaginaIncTransferencia(){
		
		List<ContaBancaria> listaContasEmpresa = IContaRepositories.findByTipo('E');
		List<ContaBancaria> listaContasFuncionarios = IContaRepositories.findByTipo('F');
		List<ContaBancaria> listaContas = IContaRepositories.findAll();
		
		if(listaContasEmpresa.size() > 1 || (listaContasEmpresa.size() > 0 && listaContasFuncionarios.size() > 0) ){
		
			transferencia = new Transferencia();
			transferencia.setSubTipo(new CategoriaTransacao());
			transferencia.setContaOrigem(new ContaBancaria());
			transferencia.setContaDestino(new ContaBancaria());
			
			listaContaTransferenciaOrigem = new ArrayList<SelectItem>();
			listaContaTransferenciaOrigem.add(new SelectItem(0,"::SELECIONE::"));
			for(int i=0;i<listaContasEmpresa.size();i++){
				listaContaTransferenciaOrigem.add(new SelectItem(listaContasEmpresa.get(i).getId(),listaContasEmpresa.get(i).getNomeContaBancaria()));
			}
			
			if(listaContas.size()>0){
				listaContaTransferenciaDestino = new ArrayList<SelectItem>();
				listaContaTransferenciaDestino.add(new SelectItem(0,"::SELECIONE::"));
				for(int i=0;i<listaContas.size();i++){
					listaContaTransferenciaDestino.add(new SelectItem(listaContas.get(i).getId(),listaContas.get(i).getNomeContaBancaria()));
				}
			}
			
			carregaComboCategoriaTelaIncTransferencia();
			return "/content/financeiro/contaBancaria/incAltTransferencias.jsf?faces-redirect=true";
			
		}
		Utilidades.mensagemNaTela("Erro.\nÉ necessário incluir no mínimo 2 contas antes de realizar uma transferência. Sendo dessas, uma conta do tipo Empresa", "erro");
		return "";
		
	}
	
	private List<SelectItem> listaContaTransferenciaOrigem;
	private List<SelectItem> listaContaTransferenciaDestino;
	
	
	
	
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

	public List<CategoriaTransacao> getListaCategoriaTransacaoReceitas() {
		return listaCategoriaTransacaoReceitas;
	}

	public void setListaCategoriaTransacaoReceitas(
			List<CategoriaTransacao> listaCategoriaTransacaoReceitas) {
		this.listaCategoriaTransacaoReceitas = listaCategoriaTransacaoReceitas;
	}

	public List<CategoriaTransacao> getListaCategoriaTransacaoDespesa() {
		return listaCategoriaTransacaoDespesa;
	}

	public void setListaCategoriaTransacaoDespesa(
			List<CategoriaTransacao> listaCategoriaTransacaoDespesa) {
		this.listaCategoriaTransacaoDespesa = listaCategoriaTransacaoDespesa;
	}

	public List<CategoriaTransacao> getListaCategoriaTransacaoTransferencia() {
		return listaCategoriaTransacaoTransferencia;
	}

	public void setListaCategoriaTransacaoTransferencia(
			List<CategoriaTransacao> listaCategoriaTransacaoTransferencia) {
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
	public int getNumComboEditarCategoriaTransacao() {
		return numComboEditarCategoriaTransacao;
	}
	public void setNumComboEditarCategoriaTransacao(int numComboEditarCategoriaTransacao) {
		this.numComboEditarCategoriaTransacao = numComboEditarCategoriaTransacao;
	}
	public boolean isAtivaDesativaBotaExcluirCategoria() {
		return ativaDesativaBotaExcluirCategoria;
	}
	public void setAtivaDesativaBotaExcluirCategoria(
			boolean ativaDesativaBotaExcluirCategoria) {
		this.ativaDesativaBotaExcluirCategoria = ativaDesativaBotaExcluirCategoria;
	}
	public CategoriaTransacao getCategoriaTransacao() {
		return categoriaTransacao;
	}
	public void setCategoriaTransacao(CategoriaTransacao categoriaTransacao) {
		this.categoriaTransacao = categoriaTransacao;
	}
	public CategoriaTransacao getCategoriaTransacaoSelecionado() {
		return categoriaTransacaoSelecionado;
	}
	public void setCategoriaTransacaoSelecionado(
			CategoriaTransacao categoriaTransacaoSelecionado) {
		this.categoriaTransacaoSelecionado = categoriaTransacaoSelecionado;
	}
	public int getNumComboFrequencia() {
		return numComboFrequencia;
	}
	public void setNumComboFrequencia(int numComboFrequencia) {
		this.numComboFrequencia = numComboFrequencia;
	}
	public List<SelectItem> getComboFrequenciaRepeticao() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(1, "Diaria"));
		list.add(new SelectItem(7, "Semanal"));
		list.add(new SelectItem(30, "Mensal"));
		list.add(new SelectItem(60, "Bimestral"));
		list.add(new SelectItem(90, "Trimestral"));
		list.add(new SelectItem(120, "Quadrimestral"));
		list.add(new SelectItem(150, "A cada 5 meses"));
		list.add(new SelectItem(180, "Semestral"));
		list.add(new SelectItem(210, "A cada 7 meses"));
		list.add(new SelectItem(240, "A cada 8 meses"));
		list.add(new SelectItem(270, "A cada 9 meses"));
		list.add(new SelectItem(300, "A cada 10 meses"));
		list.add(new SelectItem(330, "A cada 11 meses"));
		list.add(new SelectItem(365, "Anual"));
		return list;
	}
	public String getMinDate(){
		return DataUtils.dataToString(new Date());
	}
	public LazyDataModel<Lancamentos> getLz() {
		return lz;
	}
	public void setLz(LazyDataModel<Lancamentos> lz) {
		this.lz = lz;
	}
	public int getNumRadioSelecionadoTipoConta() {
		return numRadioSelecionadoTipoConta;
	}
	public void setNumRadioSelecionadoTipoConta(int numRadioSelecionadoTipoConta) {
		this.numRadioSelecionadoTipoConta = numRadioSelecionadoTipoConta;
	}
	public Transferencia getTransferencia() {
		return transferencia;
	}
	public void setTransferencia(Transferencia transferencia) {
		this.transferencia = transferencia;
	}
	public List<SelectItem> getListaContaTransferenciaOrigem() {
		return listaContaTransferenciaOrigem;
	}
	public void setListaContaTransferenciaOrigem(
			List<SelectItem> listaContaTransferenciaOrigem) {
		this.listaContaTransferenciaOrigem = listaContaTransferenciaOrigem;
	}
	public List<SelectItem> getListaContaTransferenciaDestino() {
		return listaContaTransferenciaDestino;
	}
	public void setListaContaTransferenciaDestino(
			List<SelectItem> listaContaTransferenciaDestino) {
		this.listaContaTransferenciaDestino = listaContaTransferenciaDestino;
	}
	public boolean isEditarLancamento() {
		return editarLancamento;
	}
	public void setEditarLancamento(boolean editarLancamento) {
		this.editarLancamento = editarLancamento;
	}
	public boolean isRenderizaBotaoEnviarBoleto() {
		return renderizaBotaoEnviarBoleto;
	}
	public void setRenderizaBotaoEnviarBoleto(boolean renderizaBotaoEnviarBoleto) {
		this.renderizaBotaoEnviarBoleto = renderizaBotaoEnviarBoleto;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public List<HistMovimentacao> getListaHistorico() {
		return listaHistorico;
	}
	public void setListaHistorico(List<HistMovimentacao> listaHistorico) {
		this.listaHistorico = listaHistorico;
	}
	public Integer getMascaraAgencia() {
		return mascaraAgencia;
	}
	public void setMascaraAgencia(Integer mascaraAgencia) {
		this.mascaraAgencia = mascaraAgencia;
	}
	public Long getMascaraConta() {
		return mascaraConta;
	}
	public void setMascaraConta(Long mascaraConta) {
		this.mascaraConta = mascaraConta;
	}
	public Integer getMascaraConvenio() {
		return mascaraConvenio;
	}
	public void setMascaraConvenio(Integer mascaraConvenio) {
		this.mascaraConvenio = mascaraConvenio;
	}
	public Integer getMascaraVariacaoDeCarteira() {
		return mascaraVariacaoDeCarteira;
	}
	public void setMascaraVariacaoDeCarteira(Integer mascaraVariacaoDeCarteira) {
		this.mascaraVariacaoDeCarteira = mascaraVariacaoDeCarteira;
	}


}
