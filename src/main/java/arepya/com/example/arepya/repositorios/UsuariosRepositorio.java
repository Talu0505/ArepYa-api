package arepya.com.example.arepya.repositorios;

import arepya.com.example.arepya.entidades.Usuario;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Se crea el repositorio de la tabla Usuarios que extiende a la clase CrudRepository para heredar las operaciones CRUD
// para poder crear, consultar, actualizar y eliminar información en la tabla
@Repository
public interface UsuariosRepositorio extends CrudRepository<Usuario, Integer> {
    // Se crea la consulta expecifica con formato SQL para obtener un usuario por su id
    @Query("SELECT * FROM usuarios WHERE id=:id")
    public Usuario obtenerUsuario(@Param("id") Integer id);

    // Tambien se hace uso de las propiedades del decorador Repository para crear las consultas por medio del nombramiento
    // de las funciones por atributo sin tener que especificar la sentencia SQL
    public Optional<Usuario> findByNombreusuario(@Param("nombreusuario") String nombreUsuario);

}
