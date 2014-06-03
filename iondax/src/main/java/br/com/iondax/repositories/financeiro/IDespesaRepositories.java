package br.com.iondax.repositories.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.iondax.entities.financeiro.contabancaria.transacoes.despesa.Despesa;

public interface IDespesaRepositories extends JpaRepository<Despesa, Long>{

}
