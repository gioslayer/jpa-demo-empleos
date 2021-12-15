package dev.gio;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import dev.gio.model.Categoria;
import dev.gio.model.Vacante;
import dev.gio.repository.CategoriasRepository;
import dev.gio.repository.VacantesRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepository repoCategorias;
	
	@Autowired
	private VacantesRepository repoVacantes;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("***** Iniciando el repo *****");
		System.out.println(repoVacantes.toString());
		System.out.println("***** Repo iniciado *****");
//		guardar();
//		buscarPorId();
//		modificar();
//		eliminar();
//		conteo();
//		eliminaTodos();
//		encontrarPorId();
//		buscarTodos();
//		existeId(20);
//		guardarTodos();
//		buscarTodosJPA();
//		borrarTodoEnBloque();
//		buscarTodosOrdenados();
//		buscarTodosPaginacion();
//		buscarTodosPaginacionOrdenados();
//		A partir de aqui se hacen los llamados a los metodos de Vacantes
		buscarVacantes();
		System.out.println("***** Repo Finalizado *****");
	}

	private void guardar() {
		System.out.println("Insertando un registro de Categoría");
		Categoria cat = new Categoria();
		cat.setNombre("Finanzas");
		cat.setDescripcion("Trabajos relacionados con finanzas y contabilidad");
		repoCategorias.save(cat);
		System.out.println(cat);
	}
	
	private void buscarPorId() {
		System.out.println("Busqueda por id");
		Optional<Categoria> optional = repoCategorias.findById(5);
		if(optional.isPresent())
			System.out.println(optional.get());
		else
			System.out.println("Registro no encontrado");
	}
	
	private void eliminar() {
		System.out.println("Elimina un registro");
		int idCategoria = 5;
		repoCategorias.deleteById(idCategoria);
	}
	
	private void modificar() {
		System.out.println("Datos actualizados de un registro");
		Optional<Categoria> optional = repoCategorias.findById(5);
		if(optional.isPresent()) {
			Categoria catTmp = optional.get();
			catTmp.setNombre("Ing. de software");
			catTmp.setDescripcion("Desarrollo de Sistemas Computacionales");
			repoCategorias.save(catTmp);
			System.out.println(optional.get());
		} else {
			System.out.println("Categoria no encontrada");
		}
	}
	
	private void conteo() {
		long count = repoCategorias.count();
		System.out.println("Total categorias: "+count);
	}
	
	private void eliminaTodos() {
		repoCategorias.deleteAll();
	}
	
	private void encontrarPorId() {
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(3);
		ids.add(8);
		ids.add(15);
		Iterable<Categoria> categorias = repoCategorias.findAllById(ids);
		for(Categoria cat : categorias) {
			System.out.println(cat);
		}
	}
	
	private void buscarTodos() {
		Iterable<Categoria> categorias = repoCategorias.findAll();
		for(Categoria cat : categorias) {
			System.out.println(cat);
		}
	}
	
	private void existeId(int id) {
		boolean existe = repoCategorias.existsById(id);
		System.out.println("Existe el registro: "+id+"? "+existe);
	}
	
	private void guardarTodos() {
		List<Categoria> categorias = getListaCategorias();
		repoCategorias.saveAll(categorias);
	}
	
	private List<Categoria> getListaCategorias(){
		LinkedList<Categoria> lista = new LinkedList<Categoria>();
		
		Categoria cat1 = new Categoria();
		cat1.setNombre("Programador de blockChain");
		cat1.setDescripcion("Trabajos relacionados con Bitcoin y Criptomonedas");
		
		Categoria cat2 = new Categoria();
		cat2.setNombre("Soldador/Pintura");
		cat2.setDescripcion("Trabajos relacionados con Soldar, pintar y enderezado");
		
		Categoria cat3 = new Categoria();
		cat3.setNombre("Ingeniero Industrial");
		cat3.setDescripcion("Trabajos relacionados con Ingenieria Industrial");
		
		lista.add(cat1);
		lista.add(cat2);
		lista.add(cat3);
		
		return lista;
		
	}
	
	private void buscarTodosJPA() {
		List<Categoria> categorias = repoCategorias.findAll();
		for(Categoria cat : categorias) {
			System.out.println(cat.getId()+" "+cat.getNombre());
		}
	}
	
//	Elimina todo de tal tabla
	private void borrarTodoEnBloque() {
		repoCategorias.deleteAllInBatch();
	}
	
	private void buscarTodosOrdenados() {
//		List<Categoria> categorias = repo.findAll(Sort.by("nombre").descending());
		List<Categoria> categorias = repoCategorias.findAll(Sort.by("nombre"));
		for(Categoria cat : categorias) {
			System.out.println(cat.getId()+" "+cat.getNombre());
		}
	}
	
	private void buscarTodosPaginacion() {
		Page<Categoria> page = repoCategorias.findAll(PageRequest.of(0, 5));
		System.out.println("Total Registros: " + page.getTotalElements());
		System.out.println("Total Paginas: " + page.getTotalPages());
		for(Categoria cat : page.getContent()) {
			System.out.println(cat.getId() + " " + cat.getNombre());
		}
	}

	private void buscarTodosPaginacionOrdenados() {
		Page<Categoria> page = repoCategorias.findAll(PageRequest.of(0, 5, Sort.by("nombre")));
		System.out.println("Total Registros: " + page.getTotalElements());
		System.out.println("Total Paginas: " + page.getTotalPages());
		for(Categoria cat : page.getContent()) {
			System.out.println(cat.getId() + " " + cat.getNombre());
		}
	}
	
	private void buscarVacantes() {
		List<Vacante> lista = repoVacantes.findAll();
		for(Vacante v : lista) {
			System.out.println(v.getId() + " " + v.getNombre());
		}
	}
}
