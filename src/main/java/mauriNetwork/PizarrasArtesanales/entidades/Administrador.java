package mauriNetwork.PizarrasArtesanales.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import mauriNetwork.PizarrasArtesanales.enumeraciones.Rol;

@Entity
@Table(name = "administrador")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreu;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany
    private List<Publicacion> publicacionesAdministrador;

}
