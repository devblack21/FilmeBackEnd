package capela.projeto.web.controllers;

import capela.projeto.data.entities.DiaSemana;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weekend")
public class DiasSemanaController {

    @GetMapping
    public ResponseEntity<?> diasSemana(){
        return ResponseEntity.ok(DiaSemana.values());
    }
}
