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

    // Servicio para hacer autenticacion de usuario con contraseña
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

    // Servicio para obtener un usuario con su identificador
    @GetMapping("/usuario/{identificacion}")
    public Usuario buscarUsuario(@PathVariable Integer identificacion) {
        Usuario u = repositorioUsuario.obtenerUsuario(identificacion);
        return u;
    }

    // Servicio para realizar el guardado de un usuario
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

    // Servicio para realizar la eliminación de su usuario por medio de su id
    @DeleteMapping("/eliminar-usuario/{idUsuario}")
    public boolean eliminarUsuario(@PathVariable Integer idUsuario) {
        Usuario usuarioEliminar = new Usuario();
        usuarioEliminar.setId(idUsuario);
        try {
            repositorioUsuario.delete(usuarioEliminar);
            return true;
        } catch (Error error) {
            System.out.println(error);
            return false;
        }
    }

    // Servico para actualizar un usuario con el cuerpo de datos enviado en la peticion put
    @PutMapping("/actualizar-usuario")
    public ResponseEntity actualizarUsuario(@RequestBody UsuarioDTO usuarioDto) {
        if (usuarioDto.getId() == null) {
            return ResponseEntity.badRequest().body("Para actualizar usuario debe proporcionar su id");
        }
        Usuario usuarioActualizar = new Usuario();
        BeanUtils.copyProperties(usuarioDto, usuarioActualizar);
        Usuario result = repositorioUsuario.save(usuarioActualizar);
        return  ResponseEntity.ok(result);
    }

}
