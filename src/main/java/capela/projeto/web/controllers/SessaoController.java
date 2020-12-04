package capela.projeto.web.controllers;

import capela.projeto.data.service.SessaoService;
import capela.projeto.web.vo.SessaoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.Map;
import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/sessoes")
public class SessaoController implements ControllerCrudInterface<SessaoVO,Long> {

    private final SessaoService sessaoService;

    @Autowired
    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @Override
    public ResponseEntity<?> listar() {
        return ok(this.sessaoService.findAll());
    }
    @Override
    public ResponseEntity<?> inserir(SessaoVO req) {
        req.setId(null);
        final var sessao = this.sessaoService.save(req);
        return created(URI.create(format("/sessoes/%d", sessao.getId()))).body(Map.of(
                "id", sessao.getId()));
    }

    @Override
    public ResponseEntity<?> buscar(Long id) {
        return ResponseEntity.ok(this.sessaoService.findById(id));
    }

    @Override
    public ResponseEntity<?> alterar(Long id, SessaoVO req) {
        req.setId(id);
        this.sessaoService.update(req);
        return noContent().build();
    }

    @Override
    public ResponseEntity<?> excluir( Long id) {
        return this.sessaoService.delete(id);
    }

    @Override
    public ResponseEntity<?> listaPaginada(int page, int limit, String direction) {
        return null;
    }
}
