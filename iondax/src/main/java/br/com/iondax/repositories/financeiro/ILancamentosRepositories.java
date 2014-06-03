package br.com.iondax.repositories.financeiro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.iondax.entities.financeiro.contabancaria.ContaBancaria;
import br.com.iondax.entities.financeiro.relatorios.Lancamentos;

public interface ILancamentosRepositories extends JpaRepository<Lancamentos, Long>{
	
	@Query("select l from Lancamentos l where l.contaBancaria = :contaBancaria and l.nomeTransacao not like '%Inclusão da conta%'")
	public List<Lancamentos> findByConta(@Param("contaBancaria") ContaBancaria id);
	
}
