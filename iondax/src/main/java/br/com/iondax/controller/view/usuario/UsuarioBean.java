package br.com.iondax.controller.view.usuario;

//import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.iondax.entities.usuario.Usuario;
import br.com.iondax.repositories.usuario.IUsuarioRepositorio;

@Component
@ManagedBean(name = "usuarioBean")
@SessionScoped
public class UsuarioBean {

	private Usuario usuario;
	private String usuarioV = "Rafael";
	private String senhaV = "123";
	
	@Autowired
	IUsuarioRepositorio usuarioRepository;

	public UsuarioBean() {
		super();
	}

	public String deslogar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.invalidate();
		usuario = new Usuario();
		return "/index.jsf?faces-redirect=true";
	}
	

	public String logar() {
		usuario = new Usuario();
		usuario.setUsername(usuarioV);
		usuario.setSenha(senhaV);
		if ( (usuario.getUsername().equals("Rafael") || usuario.getUsername().equals("Zaira")) && usuario.getSenha().equals("123")) {
			
			if(getUsuarioV().equals("Rafael")){
				usuario.setNome("Rafael Silva Oliveira");
			}else if(getUsuarioV().equals("Zaira")){
				usuario.setNome("Zaira de Oliveira Feitosa");
			}
			usuario.setStatusSistema(true);
			
			setaUsuarioNaSessao(usuario);
		    
			// Testando Spring
			// List<Usuario> listaUsuarios = usuarioRepository.findAll();
			//
			// for(Usuario u:listaUsuarios){
			// System.out.println("ID:"+u.getId()+"\nUsername: "+u.getUsername()+"\nSenha: "+
			// u.getSenha()+"\n---------------------------\n\n");
			// }
		    
		    return "/content/bemVindo.jsf?faces-redirect=true";
		} else {
			usuario.setStatusSistema(false);
			return "index.jsf?faces-redirect=true";
		}
		
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
		this.usuario = pegaUsuarioNaSessao();
		
		if(usuario == null){
			return new Usuario();
		}
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

}
