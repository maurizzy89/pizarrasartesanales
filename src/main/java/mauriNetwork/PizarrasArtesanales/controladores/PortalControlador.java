package mauriNetwork.PizarrasArtesanales.controladores;

import java.io.IOException;
import java.util.List;
import mauriNetwork.PizarrasArtesanales.entidades.Pizarra;
import mauriNetwork.PizarrasArtesanales.excepciones.MyException;
import mauriNetwork.PizarrasArtesanales.servicios.AdministradorServicio;
import mauriNetwork.PizarrasArtesanales.servicios.ImagenService;
import mauriNetwork.PizarrasArtesanales.servicios.PizarraServicio;
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
//

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private PizarraServicio pizarraServicio;
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
    public String listarMenorAMayorPrecio(ModelMap modelo) {
        List<Pizarra> pizarrasSimples = pizarraServicio.listarMenorAMayorPrecio();
        modelo.addAttribute("publicaciones", pizarrasSimples);
        modelo.addAttribute("titulo", "MENOR A MAYOR PRECIO");
        return "index.html";
    }

    @GetMapping("/pizarras_mayor_a_menor_precio")
    public String listarMayorAMenorPrecio(ModelMap modelo) {
        List<Pizarra> pizarrasSimples = pizarraServicio.listarMayorAMenorPrecio();
        modelo.addAttribute("publicaciones", pizarrasSimples);
        modelo.addAttribute("titulo", "MAYOR A MENOR PRECIO");
        return "index.html";
    }

    @GetMapping("/pizarras_simples")
    public String listarPizarrasSimples(ModelMap modelo) {
        List<Pizarra> pizarrasSimples = pizarraServicio.listarPizarrasSimples();
        modelo.addAttribute("publicaciones", pizarrasSimples);
        modelo.addAttribute("titulo", "PIZARRAS SIMPLES");
        return "index.html";
    }

    @GetMapping("/pizarras_dobles")
    public String listarPizarrasDoble(ModelMap modelo) {
        List<Pizarra> pizarrasDoble = pizarraServicio.listarPizarrasDoble();
        modelo.addAttribute("publicaciones", pizarrasDoble);
        modelo.addAttribute("titulo", "PIZARRAS DOBLE");
        return "index.html";
    }

    @GetMapping("/pizarras_escolares")
    public String listarPizarrasEscolares(ModelMap modelo) {
        List<Pizarra> pizarrasEscolares = pizarraServicio.listarPizarrasEscolares();
        modelo.addAttribute("publicaciones", pizarrasEscolares);
        modelo.addAttribute("titulo", "PIZARRAS ESCOLARES");
        return "index.html";
    }

    @GetMapping("/pizarras_maceteras")
    public String listarPizarrasMaceteras(ModelMap modelo) {
        List<Pizarra> pizarrasMaceteras = pizarraServicio.listarPizarrasMaceteras();
        modelo.addAttribute("publicaciones", pizarrasMaceteras);
        modelo.addAttribute("titulo", "PIZARRAS MACETERAS");
        return "index.html";
    }

    @GetMapping("/pizarras_madera")
    public String listarPizarrasMadera(ModelMap modelo) {
        List<Pizarra> pizarrasMadera = pizarraServicio.listarPizarrasMadera();
        modelo.addAttribute("publicaciones", pizarrasMadera);
        modelo.addAttribute("titulo", "CON SUPERFICIE DE MADERA");
        return "index.html";
    }

    @GetMapping("/pizarras_chapa")
    public String listarPizarrasChapa(ModelMap modelo) {
        List<Pizarra> pizarrasChapa = pizarraServicio.listarPizarrasChapa();
        modelo.addAttribute("publicaciones", pizarrasChapa);
        modelo.addAttribute("titulo", "CON SUPERFICIE DE CHAPA");
        return "index.html";
    }

    @GetMapping("/pizarras_chicas")
    public String listarPizarrasChicas(ModelMap modelo) {
        List<Pizarra> pizarrasChicas = pizarraServicio.listarPizarrasChicas();
        modelo.addAttribute("publicaciones", pizarrasChicas);
        modelo.addAttribute("titulo", "PIZARRAS CHICAS");
        return "index.html";
    }

    @GetMapping("/pizarras_medianas")
    public String listarPizarrasMedianas(ModelMap modelo) {
        List<Pizarra> pizarrasMedianas = pizarraServicio.listarPizarrasMedianas();
        modelo.addAttribute("publicaciones", pizarrasMedianas);
        modelo.addAttribute("titulo", "PIZARRAS MEDIANAS");
        return "index.html";
    }

    @GetMapping("/pizarras_grandes")
    public String listarPizarrasGrandes(ModelMap modelo) {
        List<Pizarra> pizarrasGrandes = pizarraServicio.listarPizarrasGrandes();
        modelo.addAttribute("publicaciones", pizarrasGrandes);
        modelo.addAttribute("titulo", "PIZARRAS GRANDES");
        return "index.html";
    }

    @GetMapping("/login")
    public String login(ModelMap modelo) {
        return "login.html";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "signIn.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(name = "nombreu") String nombreu, @RequestParam(name = "password") String password) throws MyException, IOException {
        administradorServicio.guardarUsuario(nombreu, password);
        return "index.html";
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
    @GetMapping("/pizarra/{id}")
    public String verPizarra(@PathVariable Long id, ModelMap modelo) {
        modelo.put("pizarra", pizarraServicio.getReferenceById(id));
        return "pizarra.html";
    }
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
