package mauriNetwork.PizarrasArtesanales.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import mauriNetwork.PizarrasArtesanales.entidades.Pizarra;
import mauriNetwork.PizarrasArtesanales.repositorio.AdministradorRepositorio;
import mauriNetwork.PizarrasArtesanales.entidades.Imagen;
import mauriNetwork.PizarrasArtesanales.excepciones.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mauriNetwork.PizarrasArtesanales.repositorio.PizarraRepositorio;

@Service
public class PizarraServicio {

    @Autowired
    private PizarraRepositorio pizarraRepositorio;
    @Autowired
    private AdministradorRepositorio administradorRepositorio;

    @Transactional    //se pone cuando el metodo hace alguna transaccion en la BD si el metodo se ejecuta bien, se realiza un commit a la BD y se aplican los cambios, si da algun error se hace un rollback (se vuelve atras) y no se aplica nada
    public Pizarra crearPizarra(Integer alto, Integer ancho, String tamanio, String tipo, String superficie, List<Imagen> imagenes, Integer precio, String descripcion) throws MyException {

        Pizarra publicacion = new Pizarra();

        publicacion.setAlto(alto);
        publicacion.setAncho(ancho);
        publicacion.setTamanio(tamanio);
        publicacion.setTipo(tipo);
        publicacion.setSuperficie(superficie);
        publicacion.setPrecio(precio);
        publicacion.setDescripcion(descripcion);
        publicacion.setImagenes(imagenes);

        pizarraRepositorio.save(publicacion);

        return publicacion;
    }

//    @Transactional
//    public void editarPizarra(Long id, Integer alto, Integer ancho, String tamanio, String tipo, String superficie, List<Imagen> imagenes, Integer precio, String descripcion) throws MyException {
//        validar(alto, ancho, tamanio, tipo, superficie, precio, descripcion);
//
//        Optional<Pizarra> respuestaPublicacion = pizarraRepositorio.findById(id);
//
//        if (respuestaPublicacion.isPresent()) {
//            Pizarra publicacion = respuestaPublicacion.get();
//
//            publicacion.setAlto(alto);
//            publicacion.setAncho(ancho);
//            publicacion.setTamanio(tamanio);
//            publicacion.setTipo(tipo);
//            publicacion.setSuperficie(superficie);
//            publicacion.setPrecio(precio);
//            publicacion.setDescripcion(descripcion);
//            publicacion.setImagenes(imagenes);
//
//            pizarraRepositorio.save(publicacion);
//        }
//    }
    @Transactional
    public void eliminarPizarra(Long id) {
        Optional<Pizarra> respuestaPublicacion = pizarraRepositorio.findById(id);

        if (respuestaPublicacion.isPresent()) {
            Pizarra publicacion = respuestaPublicacion.get();
            pizarraRepositorio.delete(publicacion);
        }
    }

    public List<Pizarra> listarPizarras() {
        List<Pizarra> ultimasPublicaciones = new ArrayList();

        ultimasPublicaciones = pizarraRepositorio.listarPizarras();

        return ultimasPublicaciones;
    }

    public void validar(Integer alto, Integer ancho, String tamanio, String tipo, String superficie, Integer precio, String descripcion) throws MyException {

        if (alto == null) {
            throw new MyException("La altura no puede estar vacia");
        }
        if (ancho == null) {
            throw new MyException("El ancho no puede estar vacio");
        }
        if (tamanio == null || tamanio.isEmpty()) {
            throw new MyException("Tenes que seleccionar el tamaño de la pizarra");
        }
        if (tipo == null || tipo.isEmpty()) {
            throw new MyException("Tenes que seleccionar el tipo de pizarra");
        }
        if (superficie == null || superficie.isEmpty()) {
            throw new MyException("Tenes que seleccionar la superficie de la pizarra");
        }
        if (precio == null) {
            throw new MyException("El precio no puede estar vacio");
        }
    }

    public Pizarra getReferenceById(Long id) {
        return pizarraRepositorio.getReferenceById(id);
    }

    @Transactional
    public void eliminarImagen(Long idPublicacion) {
        Optional<Pizarra> respuestaPublicacion = pizarraRepositorio.findById(idPublicacion);

        if (respuestaPublicacion.isPresent()) {
            Pizarra publicacion = respuestaPublicacion.get();

            publicacion.setImagenes(null);

            pizarraRepositorio.save(publicacion);
        }
    }
}
