package capela.projeto.web.controllers;

import capela.projeto.data.service.FilmeService;;
import capela.projeto.web.vo.FilmeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Map;
import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/filmes"
)
public class FilmesController implements ControllerCrudInterface<FilmeVO,Long> {

    private final FilmeService filmeService;

    @Autowired
    public FilmesController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @Override
    public ResponseEntity<?> listar() {

        return ok(this.filmeService.findAll());
    }

    @Override
    public ResponseEntity<?> inserir(FilmeVO req) {
        req.setIdFilme(null);
        final var filme = this.filmeService.save(req);
        return created(URI.create(format("/filmes/%d", filme.getIdFilme()))).body(Map.of(
                "id", filme.getIdFilme()));
    }

    @Override
    public ResponseEntity<?> buscar(Long id) {
        return ResponseEntity.ok(this.filmeService.findById(id));
    }

    @Override
    public ResponseEntity<?> alterar(Long id, FilmeVO req) {
        req.setIdFilme(id);
        this.filmeService.update(req);
        return noContent().build();
    }

    @Override
    public ResponseEntity<?> excluir( Long id) {
        return this.filmeService.delete(id);
    }

    @Override
    public ResponseEntity<?> listaPaginada(int page, int limit, String direction) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection,"nome"));
        Page<FilmeVO> filmes = this.filmeService.findAll(pageable);
        return ResponseEntity.ok(filmes);
    }
}
