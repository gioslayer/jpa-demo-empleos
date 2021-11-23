package dev.gio.repository;

import org.springframework.data.repository.CrudRepository;

import dev.gio.model.Categoria;

public interface CategoriasRepository extends CrudRepository<Categoria, Integer> {
	
}
