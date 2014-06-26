package br.com.iondax.controller.boleto;

import java.math.BigDecimal;
import java.util.Date;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.Modalidade;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.SacadorAvalista;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeCobranca;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite;

import br.com.iondax.entities.financeiro.contabancaria.transacoes.receita.Receita;

public class GeradorDeBoleto {

	/*
	 * Método de exemplo
	 */
	public static BoletoViewer boletoCriado(Receita r) {

		Cedente cedente = new Cedente("PROJETO IONDAX TCC ", "23.766.548/0001-42");

		/*
		 * INFORMANDO DADOS SOBRE O SACADO.
		 */
		Sacado sacado = new Sacado(r.getCliente().getNome(), "501.176.547-44");

		// Informando o endereço do sacado.
		Endereco enderecoSac = new Endereco();
		enderecoSac.setUF(UnidadeFederativa.SP);
		enderecoSac.setLocalidade("Cotia");
		enderecoSac.setCep(new CEP("06702-824"));
		enderecoSac.setBairro("Chácara Roselândia");
		enderecoSac.setLogradouro("Rua Flores do Campo");
		enderecoSac.setNumero("102");
		sacado.addEndereco(enderecoSac);

		/*
		 * INFORMANDO DADOS SOBRE O SACADOR AVALISTA.
		 */
		SacadorAvalista sacadorAvalista = new SacadorAvalista("PROJETO IONDAX TCC", "23.766.548/0001-42");

		// Informando o endereço do sacador avalista.
		Endereco enderecoSacAval = new Endereco();
		enderecoSacAval.setUF(UnidadeFederativa.DF);
		enderecoSacAval.setLocalidade("Brasília");
		enderecoSacAval.setCep(new CEP("59000-000"));
		enderecoSacAval.setBairro("Grande Centro");
		enderecoSacAval.setLogradouro("Rua Eternamente Principal");
		enderecoSacAval.setNumero("001");
		sacadorAvalista.addEndereco(enderecoSacAval);

		/*
		 * INFORMANDO OS DADOS SOBRE O TÍTULO.
		 */

		org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria contaBancaria = null;
		// Informando dados sobre a conta bancária do título.
		if(r.getContaBancaria().getBanco().equals("Banco do Brasil")){
			contaBancaria = new org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria(BancosSuportados.BANCO_DO_BRASIL.create());
		}else if(r.getContaBancaria().getBanco().equals("Bradesco")){
			contaBancaria = new org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria(BancosSuportados.BANCO_BRADESCO.create());
		}else if(r.getContaBancaria().getBanco().equals("Santander")){
			contaBancaria = new org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria(BancosSuportados.BANCO_SANTANDER.create());
		}else if(r.getContaBancaria().getBanco().equals("Caixa Econômica Federeal")){
			contaBancaria = new org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria(BancosSuportados.CAIXA_ECONOMICA_FEDERAL.create());
		}else if(r.getContaBancaria().getBanco().equals("Nossa Caixa")){
			contaBancaria = new org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria(BancosSuportados.NOSSA_CAIXA.create());
		}else if(r.getContaBancaria().getBanco().equals("Itaú")){
			contaBancaria = new org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria(BancosSuportados.BANCO_ITAU.create());
		}
		
		contaBancaria.setNumeroDaConta(new NumeroDaConta(456, "0"));
		contaBancaria.setCarteira(new Carteira(2, TipoDeCobranca.SEM_REGISTRO));
		contaBancaria.setAgencia(new Agencia(1234, "1"));
		contaBancaria.setModalidade(new Modalidade(1));

		Titulo titulo = new Titulo(contaBancaria, sacado, cedente, sacadorAvalista);
		titulo.setNumeroDoDocumento("123456");
		titulo.setNossoNumero("99999999999999999");
		titulo.setDigitoDoNossoNumero("5");
		titulo.setValor(BigDecimal.valueOf(0.23));
		titulo.setDataDoDocumento(new Date());
		titulo.setDataDoVencimento(new Date());
		titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
		titulo.setAceite(Aceite.A);
		titulo.setDesconto(new BigDecimal(0.05));
		titulo.setDeducao(BigDecimal.ZERO);
		titulo.setMora(BigDecimal.ZERO);
		titulo.setAcrecimo(BigDecimal.ZERO);
		titulo.setValorCobrado(BigDecimal.ZERO);

		/*
		 * INFORMANDO OS DADOS SOBRE O BOLETO.
		 */
		Boleto boleto = new Boleto(titulo);

		boleto.setLocalPagamento("Pagável preferencialmente na Rede X ou em "
				+ "qualquer Banco até o Vencimento.");
		boleto.setInstrucaoAoSacado("Senhor sacado, sabemos sim que o valor "
				+ "cobrado não é o esperado, aproveite o DESCONTÃO!");
		boleto.setInstrucao1("PARA PAGAMENTO 1 até Hoje não cobrar nada!");
		boleto.setInstrucao2("PARA PAGAMENTO 2 até Amanhã Não cobre!");
		boleto.setInstrucao3("PARA PAGAMENTO 3 até Depois de amanhã, OK, não cobre.");
		boleto.setInstrucao4("PARA PAGAMENTO 4 até 04/xx/xxxx de 4 dias atrás COBRAR O VALOR DE: R$ 01,00");
		boleto.setInstrucao5("PARA PAGAMENTO 5 até 05/xx/xxxx COBRAR O VALOR DE: R$ 02,00");
		boleto.setInstrucao6("PARA PAGAMENTO 6 até 06/xx/xxxx COBRAR O VALOR DE: R$ 03,00");
		boleto.setInstrucao7("PARA PAGAMENTO 7 até xx/xx/xxxx COBRAR O VALOR QUE VOCÊ QUISER!");
		boleto.setInstrucao8("APÓS o Vencimento, Pagável Somente na Rede X.");


		/*
		 * GERANDO O BOLETO BANCÁRIO.
		 */
		// Instanciando um objeto "BoletoViewer", classe responsável pela
		// geração do boleto bancário.
		BoletoViewer boletoViewer = new BoletoViewer(boleto);

		return boletoViewer;
	}

}
