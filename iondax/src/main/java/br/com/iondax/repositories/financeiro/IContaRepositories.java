package br.com.iondax.repositories.financeiro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.iondax.entities.financeiro.contabancaria.ContaBancaria;

public interface IContaRepositories extends JpaRepository<ContaBancaria, Long> {
	
	@Query("select c from ContaBancaria c where c.tipo= :tipo")
	public List<ContaBancaria> findByTipo(@Param("tipo") char tipo);
	
	@Query("select c from ContaBancaria c where c.nomeContaBancaria= :nome and c.banco= :banco")
	public ContaBancaria findByNomeAndBanco(@Param("nome") String nome, @Param("banco") String banco);
	
}
