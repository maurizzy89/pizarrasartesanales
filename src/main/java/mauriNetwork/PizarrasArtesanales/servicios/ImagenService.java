package mauriNetwork.PizarrasArtesanales.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import mauriNetwork.PizarrasArtesanales.entidades.Imagen;
import mauriNetwork.PizarrasArtesanales.entidades.Pizarra;
import mauriNetwork.PizarrasArtesanales.repositorio.ImagenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImagenService {

    @Autowired
    ImagenRepositorio imagenRepositorio;
    @Autowired
    PizarraServicio pizarraServicio;

    public void save(Imagen imagen) {
        imagenRepositorio.save(imagen);
    }

    public void delete(int id) {
        imagenRepositorio.deleteById(id);
    }

    public Optional<Imagen> getOne(int id) {
        return imagenRepositorio.findById(id);
    }

    public boolean exists(int id) {
        return imagenRepositorio.existsById(id);
    }

//    public String modificarURL(String urlImagen) {
//        String[] urlDividida = urlImagen.split("/");
//        String urlNueva = "";
//
//        for (int i = 0; i < urlDividida.length; i++) {
//            if (i == 5) {
//                urlDividida[i] = urlDividida[i] + "/c_fill,f_auto,h_700,q_100:444,w_500";
//            }
//            if (i != urlDividida.length - 1) {
//                urlNueva = urlNueva + urlDividida[i] + "/";
//            } else {
//                urlNueva = urlNueva + urlDividida[i];
//            }
//        }
//        return urlNueva;
//    }
    public void modificarPortada(Long id, int idInt) {
        Pizarra pizarra = pizarraServicio.getReferenceById(id);
        List<Imagen> imagenes = pizarra.getImagenes();
        for (Imagen imagen : imagenes) {
            if (imagen.isPortada()) {
                imagen.setPortada(false);
            }
        }
        for (Imagen imagen : imagenes) {
            if (imagen.getId() == idInt) {
                imagen.setPortada(true);
            }
        }

    }
}
