package mauriNetwork.PizarrasArtesanales.servicios;

import mauriNetwork.headbangersCave.excepciones.MyException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import mauriNetwork.PizarrasArtesanales.entidades.Publicacion;
import mauriNetwork.PizarrasArtesanales.repositorio.AdministradorRepositorio;
import mauriNetwork.PizarrasArtesanales.repositorio.PublicacionRepositorio;
import mauriNetwork.PizarrasArtesanales.entidades.Imagen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicacionServicio {

    @Autowired
    private PublicacionRepositorio publicacionRepositorio;
    @Autowired
    private AdministradorRepositorio administradorRepositorio;

    @Transactional    //se pone cuando el metodo hace alguna transaccion en la BD si el metodo se ejecuta bien, se realiza un commit a la BD y se aplican los cambios, si da algun error se hace un rollback (se vuelve atras) y no se aplica nada
    public Publicacion crearPublicacion(String titulo, String descripcion, Integer precio, Imagen imagen) throws MyException {
        validar(titulo, descripcion, precio, imagen);

        Publicacion publicacion = new Publicacion();

        publicacion.setTitulo(titulo);
        publicacion.setDescripcion(descripcion);
        publicacion.setPrecio(precio);
        publicacion.setImagen(imagen);

        publicacionRepositorio.save(publicacion);

        return publicacion;
    }

    @Transactional
    public void editarPublicacion(Long id, String titulo, String descripcion, Integer precio, Imagen imagen) throws MyException {
        validar(titulo, descripcion, precio, imagen);

        Optional<Publicacion> respuestaPublicacion = publicacionRepositorio.findById(id);

        if (respuestaPublicacion.isPresent()) {
            Publicacion publicacion = respuestaPublicacion.get();

            publicacion.setTitulo(titulo);
            publicacion.setDescripcion(descripcion);
            publicacion.setPrecio(precio);
            publicacion.setImagen(imagen);

            publicacionRepositorio.save(publicacion);
        }
    }

    @Transactional
    public void eliminarPublicacion(Long id) {
        Optional<Publicacion> respuestaPublicacion = publicacionRepositorio.findById(id);

        if (respuestaPublicacion.isPresent()) {
            Publicacion publicacion = respuestaPublicacion.get();
            publicacionRepositorio.delete(publicacion);
        }
    }

    public List<Publicacion> listarPublicaciones() {
        List<Publicacion> ultimasPublicaciones = new ArrayList();

        ultimasPublicaciones = publicacionRepositorio.listarPublicaciones();

        return ultimasPublicaciones;
    }

    public void validar(String titulo, String descripcion, Integer precio, Imagen imagen) throws MyException {
        if (titulo == null || titulo.isEmpty()) {
            throw new MyException("El titulo no puede estar vacio");
        }

        if (descripcion == null || descripcion.isEmpty()) {
            throw new MyException("La descripcion no puede estar vacia");
        }

        if (precio == null) {
            throw new MyException("El precio no puede estar vacio");
        }

        if (imagen == null) {
            throw new MyException("Tenes que colocarle una imagen a la publicación");
        }

    }

    public Publicacion getReferenceById(Long id) {
        return publicacionRepositorio.getReferenceById(id);
    }

    @Transactional
    public void eliminarImagen(Long idPublicacion) {
        Optional<Publicacion> respuestaPublicacion = publicacionRepositorio.findById(idPublicacion);

        if (respuestaPublicacion.isPresent()) {
            Publicacion publicacion = respuestaPublicacion.get();

            publicacion.setImagen(null);

            publicacionRepositorio.save(publicacion);
        }
    }
}
