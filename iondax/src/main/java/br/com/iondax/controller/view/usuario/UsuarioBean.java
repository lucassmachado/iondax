package br.com.iondax.controller.view.usuario;

//import java.util.List;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.iondax.entities.usuario.Usuario;
import br.com.iondax.repositories.usuario.IUsuarioRepositories;

@Component
@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean {

	private Usuario usuario;
	private String usuarioV;
	private String senhaV;
	
	
	private boolean vaiCadastrar = false;
	private boolean buscaRealizada = false;
	private String mensagem;
	
	
	
	
	@Autowired
	IUsuarioRepositories IUsuarioRepositories;

	public UsuarioBean() {
		super();
	}

	public String deslogar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.invalidate();
		this.usuario.setStatusSistema(false);
		IUsuarioRepositories.saveAndFlush(this.usuario);
		this.usuario = new Usuario();
		return "/index.jsf?faces-redirect=true";
	}
	

	public String logar() {
		this.usuario = new Usuario();
		this.usuario.setUsername(usuarioV);
		this.usuario.setSenha(senhaV);
		
		List<Usuario> listaUsuarios = IUsuarioRepositories.findAll();
		buscaRealizada = true;
		
		if(listaUsuarios.size()>0){
			for(int i=0;i<listaUsuarios.size();i++){
				if (listaUsuarios.get(i).getUsername().equals(this.usuario.getUsername()) && listaUsuarios.get(i).getSenha().equals(this.usuario.getSenha())) {
					this.usuario = listaUsuarios.get(i);
					this.usuario.setStatusSistema(true);
					IUsuarioRepositories.saveAndFlush(this.usuario);
					setaUsuarioNaSessao(this.usuario);
				    break;
				}
			}
			if(this.usuario.getId() == null){
				
			setMensagem("Não encontramos o seu perfil, clique abaixo para se cadastrar.");
			this.usuario.setStatusSistema(false);
			return "/index.jsf?faces-redirect=true";
			}
			return "/content/bemVindo.jsf?faces-redirect=true";
		}else {
			setMensagem("Não existe usuários cadastrados, clique abaixo para se cadastrar.");
			this.usuario.setStatusSistema(false);
			return "/index.jsf?faces-redirect=true";
		}
		
	}
	
	public void chamaCadastrar(){
		this.usuario = new Usuario(this.usuario);
		this.usuario.setUsername(usuarioV);
		this.usuario.setSenha(senhaV);
		vaiCadastrar = true;
//		usuario = new Usuario();
	}
	public String cadastrarUsuario(){
		this.usuario.setStatusSistema(false);
		IUsuarioRepositories.save(this.usuario);
		vaiCadastrar = false;
		buscaRealizada = false;
		return "/content/bemVindo.jsf?faces-redirect=true";
	}
	
	public void setaUsuarioNaSessao(Usuario usuario){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
	    session.setAttribute("usuario",usuario);
	}
	
	public Usuario pegaUsuarioNaSessao(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		usuario = (Usuario)session.getAttribute("usuario");
		return usuario;
	}
	// Getters e Setters
	public Usuario getUsuario() {
//		this.usuario = pegaUsuarioNaSessao();
//		
//		if(usuario == null){
//			return new Usuario();
//		}
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getUsuarioV() {
		return usuarioV;
	}

	public void setUsuarioV(String usuarioV) {
		this.usuarioV = usuarioV;
	}

	public String getSenhaV() {
		return senhaV;
	}

	public void setSenhaV(String senhaV) {
		this.senhaV = senhaV;
	}

	public boolean isVaiCadastrar() {
		return vaiCadastrar;
	}

	public void setVaiCadastrar(boolean vaiCadastrar) {
		this.vaiCadastrar = vaiCadastrar;
	}

	public boolean isBuscaRealizada() {
		return buscaRealizada;
	}

	public void setBuscaRealizada(boolean buscaRealizada) {
		this.buscaRealizada = buscaRealizada;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
