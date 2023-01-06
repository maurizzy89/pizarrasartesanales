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

    @Query("SELECT p FROM Pizarra p WHERE p.tipo='DOBLE' ORDER BY p.id DESC")
    public List<Pizarra> listarPizarrasDoble();

    @Query("SELECT p FROM Pizarra p WHERE p.tipo='SIMPLE' ORDER BY p.id DESC")
    public List<Pizarra> listarPizarrasSimples();

    @Query("SELECT p FROM Pizarra p WHERE p.tipo='ESCOLAR' ORDER BY p.id DESC")
    public List<Pizarra> listarPizarrasEscolares();

    @Query("SELECT p FROM Pizarra p WHERE p.tipo='MACETERO' ORDER BY p.id DESC")
    public List<Pizarra> listarPizarrasMaceteras();

    @Query("SELECT p FROM Pizarra p WHERE p.superficie='MADERA' ORDER BY p.id DESC")
    public List<Pizarra> listarPizarrasMadera();

    @Query("SELECT p FROM Pizarra p WHERE p.superficie='CHAPA' ORDER BY p.id DESC")
    public List<Pizarra> listarPizarrasChapa();

    @Query("SELECT p FROM Pizarra p WHERE p.tamanio='CHICO' ORDER BY p.id DESC")
    public List<Pizarra> listarPizarrasChicas();

    @Query("SELECT p FROM Pizarra p WHERE p.tamanio='MEDIANO' ORDER BY p.id DESC")
    public List<Pizarra> listarPizarrasMedianas();

    @Query("SELECT p FROM Pizarra p WHERE p.tamanio='GRANDE' ORDER BY p.id DESC")
    public List<Pizarra> listarPizarrasGrandes();

    @Query("SELECT p FROM Pizarra p ORDER BY p.precio")
    public List<Pizarra> listarMenorAMayor();

    @Query("SELECT p FROM Pizarra p ORDER BY p.precio DESC")
    public List<Pizarra> listarMayorAMenor();

}
