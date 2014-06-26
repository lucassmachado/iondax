package br.com.iondax.repositories.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.iondax.entities.financeiro.contabancaria.transacoes.transferencia.Transferencia;
import br.com.iondax.entities.financeiro.relatorios.Lancamentos;

public interface ITransferenciaRepositories extends JpaRepository<Transferencia, Long> {
	
	@Query("select t from Transferencia t where t.lancamentos= :id")
	public Transferencia findByLancamento(@Param("id") Lancamentos id);
	
}
