package br.com.iondax.repositories.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.iondax.entities.financeiro.relatorios.HistMovimentacao;

public interface IHistMovimentacaoRepositories extends JpaRepository<HistMovimentacao, Long>{

}
