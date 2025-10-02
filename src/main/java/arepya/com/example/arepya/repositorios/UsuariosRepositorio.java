package arepya.com.example.arepya.repositorios;

import arepya.com.example.arepya.entidades.Usuario;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariosRepositorio extends CrudRepository<Usuario, Integer> {
    @Query("SELECT * FROM usuarios WHERE id=:id")
    public Usuario obtenerUsuario(@Param("id") Integer id);

    public Optional<Usuario> findByNombreusuario(@Param("nombreusuario") String nombreUsuario);

}
