package mauriNetwork.PizarrasArtesanales.repositorio;

import mauriNetwork.PizarrasArtesanales.entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepositorio extends JpaRepository<Administrador, Long> {

    @Query("SELECT a FROM Administrador a WHERE a.nombreu = :nombreu")
    public Administrador buscarPorUsuario(@Param("nombreu") String nombreu);
}
