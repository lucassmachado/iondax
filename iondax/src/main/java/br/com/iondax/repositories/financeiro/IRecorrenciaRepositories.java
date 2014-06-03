package br.com.iondax.repositories.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.iondax.entities.financeiro.contabancaria.transacoes.Recorrencia;

public interface IRecorrenciaRepositories extends JpaRepository<Recorrencia, Long>{

}
