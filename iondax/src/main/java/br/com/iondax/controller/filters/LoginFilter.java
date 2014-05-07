package br.com.iondax.controller.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import br.com.iondax.entities.usuario.Usuario;

public class LoginFilter implements Filter {

    public LoginFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		//Captura o ManagedBean chamado “usuario”
        Usuario usuario = (Usuario) ((HttpServletRequest) request).getSession().getAttribute("usuario");
        //Verifica se nosso ManagedBean ainda não 
        //foi instanciado ou caso a
        //variável loggedIn seja false, assim saberemos que  
        // o usuário não está logado
        if (  usuario == null && verificaSePassaRequest(
        		((HttpServletRequest) request).getRequestURI()
        												)//Fecha parametro
			)//Fecha If
        	{
	        
            //Redirecionamos o usuário imediatamente 
            //para a página de login.xhtml
	        RequestDispatcher rd = ((HttpServletRequest) request).getRequestDispatcher("/index.jsf?faces-redirect=true");
//          ((HttpServletResponse) response).sendRedirect(contextPath + "/index.jsf");
	        rd.forward(request, response);
        } else {
               //Caso ele esteja logado, apenas deixamos 
               //que o fluxo continue
             chain.doFilter(request, response);
        }
	}
	
	public boolean verificaSePassaRequest(String url){
		return !url.endsWith(".css") && !url.endsWith(".js") && !url.endsWith(".jpg") && !url.contains(".jpg;jsessionid")  
	            && !url.endsWith(".gif") && !url.contains("javax.faces.resource") && !url.endsWith("iondax/index.jsf");
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
