package mauriNetwork.PizarrasArtesanales.repositorio;

import java.util.List;
import mauriNetwork.PizarrasArtesanales.entidades.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, Integer>{
   List<Imagen> findByOrderById();  
}
