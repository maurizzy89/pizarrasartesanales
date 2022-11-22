package mauriNetwork.PizarrasArtesanales.controladores;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import mauriNetwork.PizarrasArtesanales.entidades.Publicacion;
import mauriNetwork.PizarrasArtesanales.entidades.Administrador;
import mauriNetwork.PizarrasArtesanales.entidades.Imagen;
import mauriNetwork.PizarrasArtesanales.servicios.AdministradorServicio;
import mauriNetwork.PizarrasArtesanales.servicios.CloudinaryService;
import mauriNetwork.PizarrasArtesanales.servicios.ImagenService;
import mauriNetwork.PizarrasArtesanales.servicios.PublicacionServicio;
import mauriNetwork.headbangersCave.excepciones.MyException;
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
    private PublicacionServicio publicacionServicio;
    @Autowired
    private AdministradorServicio administradorServicio;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ImagenService imagenService;

    @GetMapping("/dashboard")
    public String index(ModelMap modelo) {
        List<Publicacion> ultimasPublicaciones = publicacionServicio.listarPublicaciones();
        modelo.addAttribute("publicaciones", ultimasPublicaciones);
        return "index.html";
    }

    @GetMapping("/añadir")
    public String añadir(Publicacion publicacion) {
        return "añadir_publicacion.html";
    }

    @PostMapping("/añadido")
    public String añadido(@RequestParam(name = "multipartFile") MultipartFile multipartFile, @RequestParam(name = "titulo") String titulo, @RequestParam(name = "descripcion") String descripcion, @RequestParam(name = "precio") Integer precio, ModelMap modelo) throws IOException, MyException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            try {
                publicacionServicio.crearPublicacion(titulo, descripcion, precio, null);
                modelo.put("exito", "Fuiste registrado correctamente, ya podes loguearte");
                List<Publicacion> ultimasPublicaciones = publicacionServicio.listarPublicaciones();
                modelo.addAttribute("publicaciones", ultimasPublicaciones);
                return "index.html";
            } catch (MyException e) {
                modelo.put("error", e.getMessage());
                return "añadir_publicacion.html";
            }

        } else {
            Map result = cloudinaryService.upload(multipartFile);
            Imagen imagen
                    = new Imagen(result.get("original_filename").toString(),
                            result.get("url").toString(),
                            result.get("public_id").toString());
            imagenService.save(imagen);
            publicacionServicio.crearPublicacion(titulo, descripcion, precio, imagen);
            modelo.put("exito", "Fuiste registrado correctamente, ya podes loguearte");
            List<Publicacion> ultimasPublicaciones = publicacionServicio.listarPublicaciones();
            modelo.addAttribute("publicaciones", ultimasPublicaciones);
            return "index.html";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, ModelMap modelo) {
        modelo.put("publicacion", publicacionServicio.getReferenceById(id));
        publicacionServicio.eliminarPublicacion(id);
        //vuelve a cargar la lista
        List<Publicacion> ultimasPublicaciones = publicacionServicio.listarPublicaciones();
        modelo.addAttribute("publicaciones", ultimasPublicaciones);
        return "index.html";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, ModelMap modelo) {
        modelo.put("publicacion", publicacionServicio.getReferenceById(id));
        return "añadir_publicacion.html";
    }

    @PostMapping("/editado")
    public String editado(Publicacion publicacion, @RequestParam(name = "multipartFile") MultipartFile multipartFile, @RequestParam(name = "titulo") String titulo, @RequestParam(name = "descripcion") String descripcion, @RequestParam(name = "precio") Integer precio, ModelMap modelo) throws IOException, MyException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            try {
                publicacionServicio.editarPublicacion(publicacion.getId(), titulo, descripcion, precio, null);
                modelo.put("exito", "Fuiste registrado correctamente, ya podes loguearte");
                List<Publicacion> ultimasPublicaciones = publicacionServicio.listarPublicaciones();
                modelo.addAttribute("publicaciones", ultimasPublicaciones);
                return "index.html";
            } catch (MyException e) {
                modelo.put("error", e.getMessage());
                return "añadir_publicacion.html";
            }

        } else {
            Map result = cloudinaryService.upload(multipartFile);
            Imagen imagen
                    = new Imagen(result.get("original_filename").toString(),
                            result.get("url").toString(),
                            result.get("public_id").toString());
            imagenService.save(imagen);
            publicacionServicio.editarPublicacion(publicacion.getId(), titulo, descripcion, precio, imagen);
            List<Publicacion> ultimasPublicaciones = publicacionServicio.listarPublicaciones();
            modelo.addAttribute("publicaciones", ultimasPublicaciones);
            return "index.html";
        }
    }
}
