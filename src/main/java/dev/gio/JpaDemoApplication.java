package dev.gio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
		System.out.println("iniciando el repo");
		System.out.println(repo);
	}

	public void guardar() {
		System.out.println("Insertar un registro");
	}
	
	public void eliminar() {
		System.out.println("Elimina un registro");
	}
}
