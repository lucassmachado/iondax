package br.com.iondax.repositories.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.iondax.entities.financeiro.contabancaria.transacoes.receita.Receita;
import br.com.iondax.entities.financeiro.relatorios.Lancamentos;

public interface IReceitaRepositories extends JpaRepository<Receita,Long>{
	
	@Query("select r from Receita r where r.lancamentos= :id")
	public Receita findByLancamento(@Param("id") Lancamentos id);
	
}
