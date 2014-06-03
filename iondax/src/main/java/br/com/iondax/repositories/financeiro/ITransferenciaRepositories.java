package br.com.iondax.repositories.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.iondax.entities.financeiro.contabancaria.transacoes.transferencia.Transferencia;

public interface ITransferenciaRepositories extends JpaRepository<Transferencia, Long> {

}
