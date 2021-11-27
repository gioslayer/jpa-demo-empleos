package dev.gio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import dev.gio.model.Categoria;
import dev.gio.repository.CategoriasRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Iniciando el repo");
		System.out.println(repo);
		System.out.println("Repo iniciado");
		//guardar();
		//buscarPorId();
		//modificar();
		eliminar();
	}

	private void guardar() {
		System.out.println("Insertando un registro de Categoría");
		Categoria cat = new Categoria();
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajos relacionados con finanzas y contabilidad");
		repo.save(cat);
		System.out.println(cat);
	}
	
	private void buscarPorId() {
		System.out.println("Busqueda por id");
		Optional<Categoria> optional = repo.findById(5);
		if(optional.isPresent())
			System.out.println(optional.get());
		else
			System.out.println("Registro no encontrado");
	}
	
	private void eliminar() {
		System.out.println("Elimina un registro");
		int idCategoria = 5;
		repo.deleteById(idCategoria);
	}
	
	private void modificar() {
		System.out.println("Datos actualizados de un registro");
		Optional<Categoria> optional = repo.findById(5);
		if(optional.isPresent()) {
			Categoria catTmp = optional.get();
			catTmp.setNombre("Ing. de software");
			catTmp.setDescripcion("Desarrollo de Sistemas Computacionales");
			repo.save(catTmp);
			System.out.println(optional.get());
		} else {
			System.out.println("Categoria no encontrada");
		}
	}
}
