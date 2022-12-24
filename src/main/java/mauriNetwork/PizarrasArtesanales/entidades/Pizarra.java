package mauriNetwork.PizarrasArtesanales.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pizarras")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pizarra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Integer alto;
    private Integer ancho;
    private String tamanio;
    private String tipo;
    private String superficie;
    private Integer precio;
    private String descripcion;

    @OneToMany
    private List<Imagen> imagenes;

}
