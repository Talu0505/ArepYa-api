package arepya.com.example.arepya;

import arepya.com.example.arepya.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import arepya.com.example.arepya.repositorios.UsuariosRepositorio;

@SpringBootApplication
public class ArepyaApplication {
	@Autowired
	private UsuariosRepositorio repositorioUsuario;
	public static void main(String[] args) {
		System.out.print("Prueba INICIADA");
		SpringApplication.run(ArepyaApplication.class, args);
	}

	@EventListener({ApplicationReadyEvent.class})
	void probarConsulta() {
		Usuario u = repositorioUsuario.obtenerUsuario(11);
		System.out.println(u.getNombreusuario());
	}

}
