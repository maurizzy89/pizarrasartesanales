package mauriNetwork.PizarrasArtesanales;

import mauriNetwork.PizarrasArtesanales.excepciones.MyException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizarrasArtesanalesApplication {

    public static void main(String[] args) throws MyException {
        SpringApplication.run(PizarrasArtesanalesApplication.class, args);
    }

}
