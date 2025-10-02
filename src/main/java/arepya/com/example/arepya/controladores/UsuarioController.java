package arepya.com.example.arepya.controladores;

import arepya.com.example.arepya.entidades.Usuario;
import arepya.com.example.arepya.entidades.UsuarioDTO;
import arepya.com.example.arepya.repositorios.UsuariosRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuariosRepositorio repositorioUsuario;
    @PostMapping("/autenticar-usuario")
    public ResponseEntity autenticarUsuario(@RequestBody UsuarioDTO usuarioAutenticar) {
        Optional<Usuario> consultaUsuario = repositorioUsuario.findByNombreusuario(usuarioAutenticar.getNombreusuario());
        if (consultaUsuario.isPresent()) {
            Usuario usuarioEncontrado = consultaUsuario.get();
            if (usuarioEncontrado.getContraseña().equals(usuarioAutenticar.getContraseña())) {
                return ResponseEntity.ok(HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/usuario/{identificacion}")
    public Usuario buscarUsuario(@PathVariable Integer identificacion) {
        Usuario u = repositorioUsuario.obtenerUsuario(identificacion);
        return u;
    }
    @PostMapping("/guardar-usuario")
    public Usuario guardarUsuario(@RequestBody UsuarioDTO usuarioNuevo) {
        System.out.println(usuarioNuevo.getNombreusuario());
        Usuario u = new Usuario();
        BeanUtils.copyProperties(usuarioNuevo, u);
        System.out.println("usuario NUEVO: ");
        System.out.print(u.getNombre());
        Usuario respuesta = repositorioUsuario.save(u);
        return respuesta;
    }

}
