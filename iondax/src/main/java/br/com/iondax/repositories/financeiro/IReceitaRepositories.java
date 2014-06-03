package br.com.iondax.repositories.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.iondax.entities.financeiro.contabancaria.transacoes.receita.Receita;

public interface IReceitaRepositories extends JpaRepository<Receita,Long>{

}
