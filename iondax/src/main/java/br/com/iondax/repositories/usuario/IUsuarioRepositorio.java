package br.com.iondax.repositories.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.iondax.entities.usuario.Usuario;

public interface IUsuarioRepositorio extends JpaRepository<Usuario, Long> {

}
