package capela.projeto.web.vo;

import capela.projeto.data.entities.Filme;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.modelmapper.ModelMapper;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FilmeVO implements Serializable {

    private Long id;
    @NotEmpty
    @Size(min = 2, max = 100)
    private String nome;
    @NotEmpty
    @Size(min = 2, max = 100)
    private String genero;
    @NotNull
    @Range(min = 1, max = 999)
    private Integer duracao;
    @NotNull
    @Range(min = 0, max = 99)
    private Integer classificacao;
    @NotNull
    private LocalDate lancamento;
    @NotEmpty
    @Size(min = 2)
    private String sinopse;

    public static FilmeVO create(Filme filme){
        return new ModelMapper().map(filme, FilmeVO.class);
    }

}
