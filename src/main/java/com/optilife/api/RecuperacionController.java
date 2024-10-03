import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.optilife.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import com.optilife.model.exception.UsuarioNoEncontradoException;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/recuperacion")
public class RecuperacionController {

    @Autowired
    private UsuarioService usuarioService; // Servicio que contiene la lógica de usuario

    @PostMapping("/solicitar")
    public ResponseEntity<String> solicitarRecuperacion(@RequestBody String email) {
        try {
            usuarioService.generarTokenRecuperacion(email); // Este método lanza Exception
            return ResponseEntity.ok("Enlace de recuperación enviado a su correo.");
        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity.badRequest().body("El correo electrónico no está registrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al generar el enlace de recuperación.");
        }
    }
}

