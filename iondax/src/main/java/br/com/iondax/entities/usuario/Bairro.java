package br.com.iondax.entities.usuario;

public class Bairro {
	
	private Long id;
	private String descBairro;
	
	public Bairro(){
		super();
	}

	public Bairro(Bairro b){
		super();
		this.id = b.getId();
		this.descBairro = b.getDescBairro();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescBairro() {
		return descBairro;
	}

	public void setDescBairro(String descBairro) {
		this.descBairro = descBairro;
	}
	
}
