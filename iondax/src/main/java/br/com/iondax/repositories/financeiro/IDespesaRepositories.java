package br.com.iondax.repositories.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.iondax.entities.financeiro.contabancaria.transacoes.despesa.Despesa;
import br.com.iondax.entities.financeiro.relatorios.Lancamentos;

public interface IDespesaRepositories extends JpaRepository<Despesa, Long>{
	
	@Query("select d from Despesa d where d.lancamentos= :id")
	public Despesa findByLancamento(@Param("id") Lancamentos id);
	
}
