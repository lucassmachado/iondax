package br.com.iondax.repositories.financeiro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.iondax.entities.venda.Cliente;

public interface IClienteRepositories extends JpaRepository<Cliente, Long>{
	
	@Query("select c from Cliente c where c.nome LIKE %:nome%")
	public List<Cliente> findByName(@Param("nome") String nome);
	
}
