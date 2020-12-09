package capela.projeto.web.exception;

import static java.util.stream.Collectors.*;
import static org.springframework.http.ResponseEntity.*;
import java.util.Map;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class ErrorHandler {
    @ExceptionHandler(ControllerException.class)
    public ResponseEntity<?> handleControllerException(ControllerException exception) {
        return status(exception.getStatus()).body(Map.of(
                "mensagem", exception.getLocalizedMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return badRequest().body(Map.of(
                "mensagem", "Não foi possível ler a requisição.\n"+exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        final var campos = exception.getBindingResult().getFieldErrors().stream()
                .filter(it -> it.getDefaultMessage() != null)
                .filter(it -> !it.getDefaultMessage().isBlank())
                .collect(groupingBy(FieldError::getField,
                        mapping(DefaultMessageSourceResolvable::getDefaultMessage, toList())));
        return badRequest().body(Map.of(
                "mensagem", "Requisição inválida.",
                "campos", campos));
    }
}
