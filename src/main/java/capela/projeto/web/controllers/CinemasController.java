package capela.projeto.web.controllers;

import capela.projeto.data.service.CinemaService;
import capela.projeto.web.vo.CinemaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.Map;
import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/cinemas")
public class CinemasController implements ControllerCrudInterface<CinemaVO,Long> {

    private final CinemaService cinemaService;

    @Autowired
    public CinemasController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @Override
    public ResponseEntity<?> listar() {
        return ok(this.cinemaService.findAll());
    }
    @Override
    public ResponseEntity<?> inserir(CinemaVO req) {
        req.setId(null);
        final var cinema = this.cinemaService.save(req);
        return created(URI.create(format("/cinemas/%d", cinema.getId()))).body(Map.of(
                "id", cinema.getId()));
    }

    @Override
    public ResponseEntity<?> buscar(Long id) {
        return ResponseEntity.ok(this.cinemaService.findById(id));
    }

    @Override
    public ResponseEntity<?> alterar(Long id, CinemaVO req) {
        req.setId(id);
        this.cinemaService.save(req);
        return noContent().build();
    }

    @Override
    public ResponseEntity<?> excluir( Long id) {
        return this.cinemaService.delete(id);
    }
}
