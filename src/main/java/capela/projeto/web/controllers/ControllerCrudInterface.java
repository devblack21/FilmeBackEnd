package capela.projeto.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public interface ControllerCrudInterface<T, ID> {

    @GetMapping(produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?> listar();
    @PostMapping( consumes = {"application/json","application/xml","application/x-yaml"},
            produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?> inserir(@Validated @RequestBody T req) ;
    @GetMapping(value = "/{id}", produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?> buscar(@PathVariable ID id) ;
    @PutMapping(value = "/{id}", consumes = {"application/json","application/xml","application/x-yaml"},
            produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?> alterar(@PathVariable ID id, @Validated @RequestBody T req);
    @DeleteMapping(value = "/{id}",consumes = {"application/json","application/xml","application/x-yaml"},
            produces = {"application/json","application/xml","application/x-yaml"})
    public ResponseEntity<?> excluir(@PathVariable ID id) ;
    @GetMapping("/paginated")
    public ResponseEntity<?> listaPaginada(@RequestParam(value = "page",defaultValue = "0") int page,
                                           @RequestParam(value = "limit",defaultValue = "12") int limit,
                                           @RequestParam(value = "direction",defaultValue = "asc") String direction);

}
