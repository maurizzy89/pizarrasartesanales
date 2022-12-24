package mauriNetwork.PizarrasArtesanales.servicios;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import mauriNetwork.PizarrasArtesanales.entidades.Administrador;
import mauriNetwork.PizarrasArtesanales.enumeraciones.Rol;
import mauriNetwork.PizarrasArtesanales.excepciones.MyException;
import mauriNetwork.PizarrasArtesanales.repositorio.AdministradorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class AdministradorServicio implements UserDetailsService {

    @Autowired
    public AdministradorRepositorio administradorRepositorio;
    @Autowired
    public PizarraServicio pizarraServicio;

    //hacer un metodo para modificar contraseña
//    @Transactional
//    public void modificarImagenDePerfil(Long id, Imagen imagen) {
//        Optional<Administrador> respuestaUsuario = administradorRepositorio.findById(id);
//
//        if (respuestaUsuario.isPresent()) {
//            Administrador administrador = respuestaUsuario.get();
//
//            administradorRepositorio.save(administrador);
//        }
//    }
    
    @Override
    public UserDetails loadUserByUsername(String nombreu) throws UsernameNotFoundException {
        Administrador administrador = administradorRepositorio.buscarPorUsuario(nombreu);
        if (administrador != null) {

            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + administrador.getRol().toString());

            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("administradorsession", administrador);

            return new User(administrador.getNombreu(), administrador.getPassword(), permisos);
        } else {
            return null;
        }
    }

    public Administrador registrar(String nombreu, String password) throws MyException {
        Administrador administrador = new Administrador();
        administrador.setNombreu(nombreu);
        administrador.setPassword(new BCryptPasswordEncoder().encode(password));

        return administrador;
    }

    @Transactional
    public void guardarUsuario(String nombreu, String password) throws MyException {
        Administrador administrador = registrar(nombreu, password);
        administrador.setRol(Rol.ADMIN);

        administradorRepositorio.save(administrador);
    }
}
