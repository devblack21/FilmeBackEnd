package capela.projeto.web.controllers;

import capela.projeto.data.service.SessaoService;
import capela.projeto.web.vo.SessaoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Map;
import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(value = "/sessoes")
public class SessaoController implements ControllerCrudInterface<SessaoVO,Long> {

    private final SessaoService sessaoService;

    @Autowired
    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @Override
    public ResponseEntity<List> listar() {
        return ok(this.sessaoService.findAll());
    }

    @GetMapping(value = "/list/{id}",produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?> listarPorCinema(@PathVariable Long id){
        return ok(this.sessaoService.findAllCinema(id));
    }

    public ResponseEntity<?> inserir(@RequestBody SessaoVO req) {
        req.setIdSessao(null);
        final var sessao = this.sessaoService.save(req);
        return created(URI.create(format("/sessoes/%d", sessao.getIdSessao()))).body(Map.of(
                "id", sessao.getIdSessao()));
    }

    @Override
    public ResponseEntity<?> buscar(Long id) {
        return ResponseEntity.ok(this.sessaoService.findById(id));
    }

    @Override
    public ResponseEntity<?> alterar(Long id,@RequestBody SessaoVO req) {
        req.setIdSessao(id);
        this.sessaoService.update(req);
        return noContent().build();
    }

    @Override
    public ResponseEntity<?> excluir( Long id) {
        return this.sessaoService.delete(id);
    }

    @Override
    public ResponseEntity<?> listaPaginada(int page, int limit, String direction) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection,"diaSemana"));
        Page<SessaoVO> sessoes = this.sessaoService.findAll(pageable);
        return ResponseEntity.ok(sessoes);
    }
}
