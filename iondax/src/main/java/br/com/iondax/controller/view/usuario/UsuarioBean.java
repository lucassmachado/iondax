package br.com.iondax.controller.view.usuario;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.iondax.entities.usuario.Usuario;
import br.com.iondax.services.IUsuarioService;

@Component
@ManagedBean(name="usuarioBean")
@SessionScoped
public class UsuarioBean {
	
	Usuario usuario = new Usuario();
	
	@Autowired
	IUsuarioService usuarioService;
	
	public UsuarioBean(){
		super();
		usuario.setStatusSistema(false);
	}
	
	public void logar(){
		if(usuario.getUsername().equals("Rafael") && usuario.getSenha().equals("123")){
			usuario.setNome("Rafael Silva Oliveira");
			usuario.setStatusSistema(true);
			
			
			//Testando Spring
			System.out.println(usuarioService.getMessage());
		
		
		}else{
			usuario.setStatusSistema(false);
		}
	}
	
	public String deslogar(){
		usuario.setStatusSistema(false);
		return "/content/index.jsf?faces-redirect=true";
	}
	
	public void alterarDadosCadastrais(){
		
	}
	
	public void atualizarCurriculo(){
		
	}
	
	//Getters e Setters
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
