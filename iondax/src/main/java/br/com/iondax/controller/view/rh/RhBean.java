package br.com.iondax.controller.view.rh;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.iondax.entities.rh.AssistenciaMedica;
import br.com.iondax.entities.rh.Funcionario;
import br.com.iondax.entities.rh.ValeTransporte;
import br.com.iondax.entities.usuario.Usuario;

@ManagedBean(name="rhBean")
@SessionScoped
public class RhBean {
	
	private Usuario usuario;
	
	//CONSULTA DE FUNCIONARIO
	private List<Usuario> listaFuncionarios;
	//FIM CONSULTA DE FUNCIONARIO
	
	
	
	
	
	//INCLUSAO DE FUNCIONARIO
	
	private List<ValeTransporte> listaValeTransportes;
	private List<ValeTransporte> listaValeTransportesSelecionados;
	private ValeTransporte transporte;
	private List<SelectItem> comboTransportes;
	private boolean desativaIncluir;
	private boolean desativaExcluir;
	private BigDecimal totalVlrTransporte;
	private AssistenciaMedica beneficioMedico;
	
	
	//Pagina CONSULTA
	public List<Usuario> carregaListaFuncionarios(){
		listaFuncionarios = new ArrayList<Usuario>();
		listaFuncionarios.add(new Usuario());
		listaFuncionarios.get(0).setFuncionario(new Funcionario());
		listaFuncionarios.get(0).setNome("Rafael");
		listaFuncionarios.get(0).getFuncionario().setRe(new Long(1));
		listaFuncionarios.get(0).getFuncionario().setFilial(new Long(1));
		listaFuncionarios.get(0).getFuncionario().setDescFilial("Cotia");
		listaFuncionarios.get(0).getFuncionario().setSetor("Desenvolvimento/Programação");
		listaFuncionarios.get(0).getFuncionario().setDataAdmissao(new Date());
		
		return listaFuncionarios;
	}
	
	public String consultarFuncionario(){
		carregaListaFuncionarios();
		return "/content/rh/funcionario/conFuncionario.jsf?faces-redirect=true";
	}
	
	
	
	
	
	
	
	
	
	//Pagina INCLUSÃO
	public String cadastrarFuncionario(){
		usuario = new Usuario();
		
		//Vale Transporte
		preencherComboTransportes();
		preencheListaBenefificioTransporte();
		setDesativaExcluir(true);
		calcularValorDeTransporte();
		listaValeTransportesSelecionados = new ArrayList<ValeTransporte>();
		setDesativaIncluir(true);
		//FIM Vale Transporte
		
		return "/content/rh/funcionario/incFuncionario.jsf?faces-redirect=true";
	}
	//Vale Transporte
	public void calcularValorDeTransporte(){
		totalVlrTransporte = BigDecimal.ZERO;
		for(int i=0;i<listaValeTransportes.size();i++){
			totalVlrTransporte = totalVlrTransporte.add(listaValeTransportes.get(i).getValorTransporte().multiply(new BigDecimal("22")));
		}
	}
	public void preencherComboTransportes(){
		comboTransportes = new ArrayList<SelectItem>();
		comboTransportes.add(new SelectItem("Carro","Carro"));
		comboTransportes.add(new SelectItem("Ônibus","Ônibus"));
		comboTransportes.add(new SelectItem("Trem/Metrô","Trem/Metrô"));
	}
	//preencheListaBenefificioTransporte vai buscar informações do banco de dados, na hora de alterar funcionario.
	public void preencheListaBenefificioTransporte(){
		listaValeTransportes = new ArrayList<ValeTransporte>();
	 	
		transporte = new ValeTransporte();
		transporte.setCodBeneficioTransporte(Long.parseLong(1+""));
		transporte.setDescTransporte("Trem/Metrô");
		transporte.setValorTransporte(new BigDecimal("3.50"));
		listaValeTransportes.add(transporte);
		
		transporte = new ValeTransporte();
		transporte.setCodBeneficioTransporte(Long.parseLong(2+""));
		transporte.setDescTransporte("Ônibus");
		transporte.setValorTransporte(new BigDecimal("3.00"));
		listaValeTransportes.add(transporte);
		
		transporte = new ValeTransporte();
	}
	public void excluirTransporte(){
		for(int i=listaValeTransportes.size()-1;i>=0;i--){
			interno : for(int j=0;j<listaValeTransportesSelecionados.size();j++){
				if(listaValeTransportesSelecionados.get(j).getCodBeneficioTransporte().intValue() ==  listaValeTransportes.get(i).getCodBeneficioTransporte().intValue()){
					listaValeTransportes.remove(i);
					break interno;
				}
			}
		}
		setDesativaExcluir(true);
		calcularValorDeTransporte();
	}
	public void incluirTransporte(){
		transporte.setCodBeneficioTransporte(Long.parseLong((listaValeTransportes.size()+1)+""));
		listaValeTransportes.add(transporte);
		transporte = new ValeTransporte();
		setDesativaIncluir(true);
	}
	public void selecionarTodosTansportes(){
		if(listaValeTransportesSelecionados.size() == 0){
			setDesativaExcluir(true);
		}else{
			setDesativaExcluir(false);
		}
	}
	public void ativaBtnExcluirTransporte(){
		setDesativaExcluir(false);
	}
	public void desativaBtnExcluirTransporte(){
		if(listaValeTransportesSelecionados.size() == 0){
			setDesativaExcluir(true);
		}
	}
	public void ativarDesativarBtnIncluir(){
		if((getTransporte().getValorTransporte().toString().equals("0") || getTransporte().getValorTransporte().toString().equals("0.00"))){
			setDesativaIncluir(true);
		}else{
			setDesativaIncluir(false);
		}
	}
	public String confirmarInclusaoFuncionario(){
		System.out.println(usuario.getCpf());
		return "/content/index.jsf?faces-redirect=true";
	}
	//Fim Vale Transporte
	
	
	//	GETTERS E SETTERS
	
	//INCLUSAO DE FUNCIONARIO
	public ValeTransporte getTransporte() {
		return transporte;
	}
	public void setTransporte(ValeTransporte transporte) {
		this.transporte = transporte;
	}
	public List<SelectItem> getComboTransportes (){
		return comboTransportes;
	}
	public void setComboTransportes(List<SelectItem> comboTransportes) {
		this.comboTransportes = comboTransportes;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public boolean isDesativaExcluir() {
		return desativaExcluir;
	}
	public void setDesativaExcluir(boolean desativaExcluir) {
		this.desativaExcluir = desativaExcluir;
	}
	public BigDecimal getTotalVlrTransporte() {
		return totalVlrTransporte;
	}
	public void setTotalVlrTransporte(BigDecimal totalVlrTransporte) {
		this.totalVlrTransporte = totalVlrTransporte;
	}
	public AssistenciaMedica getBeneficioMedico() {
		return beneficioMedico;
	}
	public void setBeneficioMedico(AssistenciaMedica beneficioMedico) {
		this.beneficioMedico = beneficioMedico;
	}
	public List<ValeTransporte> getListaValeTransportes() {
		return listaValeTransportes;
	}
	public void setListaValeTransportes(List<ValeTransporte> listaValeTransportes) {
		this.listaValeTransportes = listaValeTransportes;
	}
	public List<ValeTransporte> getListaValeTransportesSelecionados() {
		return listaValeTransportesSelecionados;
	}
	public void setListaValeTransportesSelecionados(
			List<ValeTransporte> listaValeTransportesSelecionados) {
		this.listaValeTransportesSelecionados = listaValeTransportesSelecionados;
	}
	public boolean isDesativaIncluir() {
		return desativaIncluir;
	}
	public void setDesativaIncluir(boolean desativaIncluir) {
		this.desativaIncluir = desativaIncluir;
	}
	//FIM INCLUSAO DE FUNCIONARIO
	
	
	//CONSULTAR FUNCIONARIO
	public List<Usuario> getListaFuncionarios() {
		return listaFuncionarios;
	}
	public void setListaFuncionarios(List<Usuario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}
	
}