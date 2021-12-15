package dev.gio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gio.model.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

}
