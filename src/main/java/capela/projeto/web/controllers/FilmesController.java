package capela.projeto.web.controllers;

import capela.projeto.data.service.FilmeService;
import capela.projeto.web.vo.FilmeVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Map;
import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/filmes")
public class FilmesController implements ControllerCrudInterface<FilmeVO,Long> {

    private final FilmeService filmeService;

    public FilmesController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @Override
    public ResponseEntity<?> listar() {
        return ok(this.filmeService.findAll());
    }
    @Override
    public ResponseEntity<?> inserir(FilmeVO req) {
        req.setId(null);
        final var filme = this.filmeService.save(req);
        return created(URI.create(format("/filmes/%d", filme.getId()))).body(Map.of(
                "id", filme.getId()));
    }

    @Override
    public ResponseEntity<?> buscar(Long id) {
        return ResponseEntity.ok(this.filmeService.findById(id));
    }

    @Override
    public ResponseEntity<?> alterar(Long id, FilmeVO req) {
        req.setId(id);
        this.filmeService.save(req);
        return noContent().build();
    }

    @Override
    public ResponseEntity<?> excluir( Long id) {
        return this.filmeService.delete(id);
    }
}
