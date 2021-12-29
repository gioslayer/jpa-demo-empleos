package dev.gio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gio.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
