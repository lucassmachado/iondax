package br.com.iondax.controller.view.usuario;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.iondax.entities.usuario.Usuario;
import br.com.iondax.repositories.IUsuarioRepository;

@Component
@ManagedBean(name="usuarioBean")
@SessionScoped
public class UsuarioBean {
	
	Usuario usuario = new Usuario();
	
	@Autowired
	IUsuarioRepository usuarioRepository;
	
	public UsuarioBean(){
		super();
		usuario.setStatusSistema(false);
	}
	
	public void logar(){
		if(usuario.getUsername().equals("Rafael") && usuario.getSenha().equals("123")){
			usuario.setNome("Rafael Silva Oliveira");
			usuario.setStatusSistema(true);
			
			
			//Testando Spring
			List<Usuario> listaUsusarios = usuarioRepository.findAll();
			for(Usuario u:listaUsusarios){
				System.out.println(u+"\n---------------------------\n\n");
			}
		
		
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
