package br.com.iondax.repositories.financeiro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.iondax.entities.financeiro.fluxocaixa.CategoriaTransacao;

public interface ICategoriaTransacaoRepositories extends JpaRepository<CategoriaTransacao, Long>{
	
	@Query("select c from CategoriaTransacao c where c.tipo= :tipo")
	public List<CategoriaTransacao> findByTipo(@Param("tipo") String tipo);
	
}
