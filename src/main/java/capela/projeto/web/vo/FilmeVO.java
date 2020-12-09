package capela.projeto.web.vo;

import capela.projeto.data.entities.Filme;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@JsonPropertyOrder({"idFilme","nome","genero","duracao","classificacao","lancamento","sinopse"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idFilme", scope = FilmeVO.class)
public class FilmeVO implements Serializable {

    @JsonProperty("idFilme")
    private Long idFilme;
    @NotEmpty
    @Size(min = 2, max = 100)
    @JsonProperty("nome")
    private String nome;
    @NotEmpty
    @Size(min = 2, max = 100)
    @JsonProperty("genero")
    private String genero;
    @NotNull
    @Range(min = 1, max = 999)
    @JsonProperty("duracao")
    private Integer duracao;
    @NotNull
    @Range(min = 0, max = 99)
    @JsonProperty("classificacao")
    private Integer classificacao;
    @NotNull
    @JsonProperty("lancamento")
    private LocalDate lancamento;
    @NotEmpty
    @Size(min = 2)
    @JsonProperty("sinopse")
    private String sinopse;


    public static FilmeVO fromId(Long id){
        FilmeVO filmeVO = new FilmeVO();
        filmeVO.idFilme = id;
        return filmeVO;
    }

    public static FilmeVO create(Filme filme){

        FilmeVO filmeVO = new FilmeVO();
        filmeVO.setIdFilme(filme.getId());
        filmeVO.setClassificacao(filme.getClassificacao());
        filmeVO.setDuracao(filme.getDuracao());
        filmeVO.setGenero(filme.getGenero());
        filmeVO.setLancamento(filme.getLancamento());
        filmeVO.setNome(filme.getNome());
        filmeVO.setSinopse(filme.getSinopse());
        return filmeVO;
    }

}
