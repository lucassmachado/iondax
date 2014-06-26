package br.com.iondax.repositories.fornecedor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.iondax.entities.fornecedor.Fornecedor;

public interface IFornecedorRepositories extends JpaRepository<Fornecedor, Long>{
	
	@Query("select f from Fornecedor f where f.nomeFantasia like %:nome%")
	public List<Fornecedor> findByName(@Param("nome") String nome);

}
