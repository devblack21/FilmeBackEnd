package capela.projeto.web.controllers;

import static capela.projeto.data.specifications.FilmeSpecification.*;
import static capela.projeto.web.exception.ControllerException.notFound;
import static capela.projeto.web.exception.ControllerException.*;
import static java.lang.String.*;
import static org.springframework.data.jpa.domain.Specification.*;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.*;
import java.net.URI;
import java.util.Map;
import capela.projeto.data.entities.Filme;
import capela.projeto.data.repositories.FilmeDao;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmes")
public class FilmesController {
    private final FilmeDao filmeDao;

    public FilmesController(FilmeDao filmeDao) {
        this.filmeDao = filmeDao;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ok(filmeDao.findAll());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> inserir(@Validated @RequestBody Filme req) {
        filmeDao.findOne(where(nomeEq(req.getNome())))
                .ifPresent(it -> { throw conflict("Já existe um Filme com o mesmo Nome."); });

        req.setId(null);
        final var filme = filmeDao.save(req);
        return created(URI.create(format("/filmes/%d", filme.getId()))).body(Map.of(
                "id", filme.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        return filmeDao.findById(id).map(ResponseEntity::ok)
                .orElseThrow(() -> notFound("Filme não encontrado"));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> alterar(@PathVariable Long id, @Validated @RequestBody Filme req) {
        filmeDao.findOne(where(nomeEq(req.getNome()).and(not(idEq(id)))))
                .ifPresent(it -> { throw conflict("Já existe um Filme com o mesmo Nome."); });

        req.setId(id);
        filmeDao.save(req);
        return noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        return filmeDao.findById(id)
                .map(it -> {
                    filmeDao.delete(it);
                    return noContent().build();
                })
                .orElseGet(() -> notFound().build());
    }
}
