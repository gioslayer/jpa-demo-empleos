package dev.gio;

import java.util.Date;
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
import dev.gio.model.Perfil;
import dev.gio.model.Usuario;
import dev.gio.model.Vacante;
import dev.gio.repository.CategoriasRepository;
import dev.gio.repository.PerfilesRepository;
import dev.gio.repository.UsuariosRepository;
import dev.gio.repository.VacantesRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	@Autowired
	private CategoriasRepository repoCategorias;
	
	@Autowired
	private VacantesRepository repoVacantes;
	
	@Autowired
	private UsuariosRepository repoUsuarios;
	
	@Autowired
	private PerfilesRepository repoPerfiles;
	
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
//		buscarVacantes();
//		guardarVacante();
//		crearPerfilesAplicacion();
//		crearUsuariocConDosPerfil();
		buscarUsuario();
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
			System.out.println(v.getId() + " " + v.getNombre() + " -> "+v.getCategoria().getNombre());
		}
	}
	
	private void guardarVacante() {
		Vacante vacante = new Vacante();
		Categoria categoria = new Categoria();
		vacante.setNombre("Profesor de Matemáticas");
		vacante.setDescripcion("Escuela primaria solicita profesor con altos conocimientos para impartir la materia de matemáticas");
		vacante.setFecha(new Date());
		vacante.setSalario(15000.0);
		vacante.setEstatus("Aprobada");
		vacante.setDestacado(0);
		vacante.setImagen("escuela.png");
		vacante.setDetalles("<h1>Los requisitos para profesor de matemáticas");
		categoria.setId(15);
		vacante.setCategoria(categoria);
		repoVacantes.save(vacante);
	}
	
	/**
	 * Método que regresa una lista de objetos de tipo Perfil que representa los diferentes PERFILES
	 * o ROLES que tenemos en nuestra aplicación de empleos
	 * @return
	 */
	private List<Perfil> getPerfilesAplicacion(){
		
		List<Perfil> lista = new LinkedList<Perfil>();
		
		Perfil supervisor = new Perfil();
		Perfil administrador = new Perfil();
		Perfil usuario = new Perfil();
		
		
		supervisor.setPerfil("SUPERVISOR");
		administrador.setPerfil("ADMINISTRADOR");
		usuario.setPerfil("USUARIO");
		
		lista.add(supervisor);
		lista.add(administrador);
		lista.add(usuario);
		
		return lista;
	}
	
	private void crearPerfilesAplicacion() {	
		repoPerfiles.saveAll(getPerfilesAplicacion());
	}
	
	/**
	 * Crear usuario con dos perfiles SUPERVISOR y ADMINISTRADOR
	 */
	private void crearUsuariocConDosPerfil() {
		Usuario user = new Usuario();
		user.setNombre("Giovanni Peña Cedillo");
		user.setEmail("giovanni_slayer@hotmail.com");
		user.setFechaRegistro(new Date());
		user.setUsername("Megaslayer");
		user.setPassword("1234");
		user.setEstatus(1);
		
		Perfil perf1 = new Perfil();
		perf1.setId(2);
		
		Perfil perf2 = new Perfil();
		perf2.setId(3);
		
		user.agregar(perf1);
		user.agregar(perf2);
		
		repoUsuarios.save(user);
	}
	
	/**
	 * Metodo para buscar un usuario y desplegar sus perfiles
	 */
	private void buscarUsuario() {
		Optional<Usuario> optional = repoUsuarios.findById(1);
		if(optional.isPresent()) {
			Usuario u = optional.get();
			System.out.println("Usuario: "+u.getNombre());
			System.out.println("Perfiles asignados: ");
			for(Perfil p : u.getPerfiles()) {
				System.out.println(p.getPerfil());
			}
		}else {
			System.out.println("Usuario no encontrado");
		}
	}
}
