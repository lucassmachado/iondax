package br.com.iondax.repositories.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.iondax.entities.financeiro.contabancaria.ContaBancaria;

public interface IContaRepositories extends JpaRepository<ContaBancaria, Long> {

}
