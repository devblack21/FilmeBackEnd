package capela.projeto.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public interface ControllerCrudInterface<T, ID> {

    @GetMapping
    public ResponseEntity<?> listar();
    @PostMapping
    public ResponseEntity<?> inserir(@Validated @RequestBody T req) ;
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable ID id) ;
    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable ID id, @Validated @RequestBody T req);
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable ID id) ;

}
