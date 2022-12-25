package mauriNetwork.PizarrasArtesanales.controladores;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
;
import javax.imageio.ImageIO;
import mauriNetwork.PizarrasArtesanales.entidades.Pizarra;
import mauriNetwork.PizarrasArtesanales.entidades.Imagen;
import mauriNetwork.PizarrasArtesanales.excepciones.MyException;
import mauriNetwork.PizarrasArtesanales.servicios.AdministradorServicio;
import mauriNetwork.PizarrasArtesanales.servicios.CloudinaryService;
import mauriNetwork.PizarrasArtesanales.servicios.ImagenService;
import mauriNetwork.PizarrasArtesanales.servicios.PizarraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



@Controller
@RequestMapping("/admin")
public class AdministradorControlador {

    @Autowired
    private PizarraServicio pizarraServicio;
    @Autowired
    private AdministradorServicio administradorServicio;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ImagenService imagenService;

    @GetMapping("/dashboard")
    public String index(ModelMap modelo) {
        List<Pizarra> ultimasPublicaciones = pizarraServicio.listarPizarras();
        modelo.addAttribute("publicaciones", ultimasPublicaciones);
        return "index.html";
    }

    @GetMapping("/agregar")
    public String agregar(Pizarra pizarra) {
        return "agregar_pizarra.html";
    }

    @PostMapping("/agregado")
    public String agregado(Pizarra pizarra, @RequestParam(required = false) List<MultipartFile> multipartFiles, @RequestParam(required = false) Integer alto, @RequestParam(required = false) Integer ancho, @RequestParam(required = false) String tamanio, @RequestParam(required = false) String tipo, @RequestParam(required = false) String superficie, @RequestParam(required = false) Integer precio, @RequestParam(required = false) String descripcion, ModelMap modelo) throws Exception, IOException {
        List<Imagen> imagenes = new ArrayList();
        int cont = 0;
        try {
            pizarraServicio.validar(alto, ancho, tamanio, tipo, superficie, precio, descripcion);
            for (MultipartFile multipartFile : multipartFiles) {
                boolean portada = false;

                BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
                if (bi == null) {
                    modelo.put("Error", "Tenes que subir, al menos, una imagen");
                    return "agregar_pizarra.html";
                }
                Map result = cloudinaryService.upload(multipartFile);

                //para dejar como portada la primer imagen por defecto
                if (cont == 0) {
                    portada = true;
                }
                Imagen imagen
                        = new Imagen(result.get("original_filename").toString(),
                                result.get("url").toString(),
                                result.get("public_id").toString(),
                                portada);
                imagenService.save(imagen);
                imagenes.add(imagen);
                cont++;
            }
            pizarraServicio.crearPizarra(alto, ancho, tamanio, tipo, superficie, imagenes, precio, descripcion);
            modelo.put("exito", "La pizarra se publico correctamente");
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            return "agregar_pizarra.html";
        }
        List<Pizarra> ultimasPublicaciones = pizarraServicio.listarPizarras();
        modelo.addAttribute("publicaciones", ultimasPublicaciones);
        return "index.html";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, ModelMap modelo) {
        modelo.put("publicacion", pizarraServicio.getReferenceById(id));
        pizarraServicio.eliminarPizarra(id);
        //vuelve a cargar la lista
        List<Pizarra> ultimasPublicaciones = pizarraServicio.listarPizarras();
        modelo.addAttribute("publicaciones", ultimasPublicaciones);
        return "index.html";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, ModelMap modelo) {
        modelo.put("pizarra", pizarraServicio.getReferenceById(id));
        return "editar_pizarra.html";
    }

//    @PostMapping("/editar/{id}/modificar_portada/{id}")
//    public String modificarPortada(@PathVariable Long id, ModelMap modelo) {
//        imagenService.modificarPortada(id);
//
//        modelo.put("pizarra", pizarraServicio.getReferenceById(id));
//        return "pizarra.html";
//    }
    @PostMapping("/editado")
    public String editado(Pizarra pizarra,
            @RequestParam(name = "alto") Integer alto,
            @RequestParam(name = "ancho") Integer ancho,
            @RequestParam(name = "tamanio") String tamanio,
            @RequestParam(name = "tipo") String tipo,
            @RequestParam(name = "superficie") String superficie,
            @RequestParam(name = "precio") Integer precio,
            @RequestParam(name = "descripcion") String descripcion,
            @RequestParam(name = "multipartFiles") List<MultipartFile> multipartFiles,
            ModelMap modelo) throws IOException, MyException {

        pizarraServicio.editarPizarra(pizarra.getId(), alto, ancho, tamanio, tipo, superficie, precio, descripcion);
        modelo.put("exito", "Fuiste registrado correctamente, ya podes loguearte");
        List<Pizarra> ultimasPublicaciones = pizarraServicio.listarPizarras();
        modelo.addAttribute("publicaciones", ultimasPublicaciones);
        return "index.html";
    }
}
