package br.com.iondax.services.impl;

import org.springframework.stereotype.Service;

import br.com.iondax.services.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Override
	public String getMessage() {
		return "JSF 2 + Spring Integration";
	}
	
	
}
