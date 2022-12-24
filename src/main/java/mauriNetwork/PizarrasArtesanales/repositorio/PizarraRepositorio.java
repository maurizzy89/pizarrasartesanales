package mauriNetwork.PizarrasArtesanales.repositorio;

import mauriNetwork.PizarrasArtesanales.entidades.Pizarra;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PizarraRepositorio extends JpaRepository<Pizarra, Long> {

    @Query("SELECT p FROM Pizarra p ORDER BY p.id DESC")
    public List<Pizarra> listarPizarras();

}
