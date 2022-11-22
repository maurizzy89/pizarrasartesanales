package mauriNetwork.PizarrasArtesanales.controladores;

import java.awt.image.BufferedImage;

import mauriNetwork.headbangersCave.excepciones.MyException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import mauriNetwork.PizarrasArtesanales.entidades.Administrador;
import mauriNetwork.PizarrasArtesanales.entidades.Imagen;
import mauriNetwork.PizarrasArtesanales.entidades.Publicacion;
import mauriNetwork.PizarrasArtesanales.servicios.AdministradorServicio;
import mauriNetwork.PizarrasArtesanales.servicios.ImagenService;
import mauriNetwork.PizarrasArtesanales.servicios.PublicacionServicio;
import mauriNetwork.PizarrasArtesanales.servicios.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
//

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private PublicacionServicio publicacionServicio;
    @Autowired
    private AdministradorServicio administradorServicio;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ImagenService imagenService;

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }

    @GetMapping("/")
    public String index(ModelMap modelo) {
        List<Publicacion> ultimasPublicaciones = publicacionServicio.listarPublicaciones();
        modelo.addAttribute("publicaciones", ultimasPublicaciones);
        return "index.html";
    }

    @GetMapping("/login")
    public String login(ModelMap modelo) {
        return "login.html";
    }

//    @PostMapping("/registro")
//    public String registro(@RequestParam(name = "nombreu") String nombreu, @RequestParam(name = "password") String password) throws MyException, IOException {
//            administradorServicio.guardarUsuario(nombreu, password);
//            return "publicaciones.html";
//        }
//
    @GetMapping("/publicaciones")
    public String listar(ModelMap modelo) {
        List<Publicacion> ultimasPublicaciones = publicacionServicio.listarPublicaciones();
        modelo.addAttribute("publicaciones", ultimasPublicaciones);
        return "publicaciones.html";
    }

//
//    @PostMapping("/guardar_creada")
//    public String guardarCreada(Publicacion publicacion, @RequestParam(name = "titulo") String titulo, @RequestParam(name = "descripcion") String descripcion, @RequestParam(name = "precio") Integer precio, @RequestParam(name = "imagen") Imagen imagen, ModelMap modelo) throws MyException, IOException, URISyntaxException {
//        try {
//            publicacionServicio.crearPublicacion(titulo, descripcion, precio, imagen);
//        } catch (MyException ex) {
//            modelo.put("error", ex.getMessage());
//            return "crear_publicacion.html";
//        }
//        modelo.put("exito", "Tu publicación fue cargada correctamente");
//        //vuelve a cargar la lista
//        List<Publicacion> publicaciones = publicacionServicio.listarPublicaciones();
//        modelo.addAttribute("publicaciones", publicaciones);
//        return "publicaciones.html";
//    }
//
//    @PostMapping("/guardar_editada")
//    public String guardarEditada(Publicacion publicacion, Long id, @RequestParam(name = "titulo") String titulo, @RequestParam(name = "descripcion") String descripcion, @RequestParam(name = "precio") Integer precio, @RequestParam(name = "imagen") Imagen imagen, ModelMap modelo) throws MyException, IOException, MalformedURLException, URISyntaxException {
//
//        try {
//            publicacionServicio.editarPublicacion(id, titulo, descripcion, precio, imagen);
//        } catch (MyException ex) {
//            modelo.put("error", ex.getMessage());
//            return "editar_publicacion.html";
//        }
//        modelo.put("exito", "La publicación fue editada correctamente");
//        //vuelve a cargar la lista
//        List<Publicacion> publicaciones = publicacionServicio.listarPublicaciones();
//        modelo.addAttribute("publicaciones", publicaciones);
//        return "publicaciones.html";
//    }
//
//    @GetMapping("/editar/{id}")
//    public String editar(@PathVariable Long id, ModelMap modelo) {
//        modelo.put("publicacion", publicacionServicio.getReferenceById(id));
//        return "editar_publicacion.html";
//    }
//
//    @GetMapping("/{id}")
//    public String eliminar(@PathVariable Long id, HttpSession session, ModelMap modelo) {
//        Administrador logueado = (Administrador) session.getAttribute("usuariosession");
//
//        modelo.put("publicacion", publicacionServicio.getReferenceById(id));
//        publicacionServicio.eliminarPublicacion(id);
//        //vuelve a cargar la lista
//        modelo.put("id", logueado.getId());
//        List<Publicacion> ultimasPublicaciones = publicacionServicio.listarPublicaciones();
//        modelo.addAttribute("publicaciones", ultimasPublicaciones);
//        return "publicaciones.html";
//    }
}
