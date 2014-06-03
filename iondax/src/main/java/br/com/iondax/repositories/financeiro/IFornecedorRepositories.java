package br.com.iondax.repositories.financeiro;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.iondax.entities.fornecedor.Fornecedor;

public interface IFornecedorRepositories extends JpaRepository<Fornecedor, Long>{

}
