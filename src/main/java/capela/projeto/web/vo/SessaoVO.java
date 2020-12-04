package capela.projeto.web.vo;

import capela.projeto.data.entities.Cinema;
import capela.projeto.data.entities.DiaSemana;
import capela.projeto.data.entities.Filme;
import capela.projeto.data.entities.Sessao;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.modelmapper.ModelMapper;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SessaoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 2, max = 100)
    private DiaSemana diaSemana;
    @NotBlank
    @Size(min = 2, max = 100)
    private LocalDateTime horario;
    private Cinema cinema;
    private Filme filme;
    @NotNull
    @Range(min = 0, max = 99)
    private int sala;

    public static SessaoVO create(Sessao sessao){
        return new ModelMapper().map(sessao, SessaoVO.class);
    }
}
