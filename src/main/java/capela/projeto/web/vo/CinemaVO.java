package capela.projeto.web.vo;

import capela.projeto.data.entities.Cinema;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@JsonPropertyOrder({"idCinema","nome","cidade","estado","salas"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCinema", scope = CinemaVO.class)
public class CinemaVO implements Serializable {

    @JsonProperty("idCinema")
    private Long idCinema;
    @Size(min = 2, max = 100)
    @JsonProperty("nome")
    private String nome;
    @Size(min = 2, max = 100)
    @JsonProperty("cidade")
    private String cidade;
    @Size(min = 2, max = 100)
    @JsonProperty("estado")
    private String estado;
    @NotNull
    @Range(min = 0, max = 99)
    @JsonProperty("salas")
    private Integer salas;

    public static CinemaVO create(Cinema cinema){
        CinemaVO cinemaVO = new CinemaVO();
        cinemaVO.setIdCinema(cinema.getId());
        cinemaVO.setCidade(cinema.getCidade());
        cinemaVO.setEstado(cinema.getEstado());
        cinemaVO.setNome(cinema.getNome());
        cinemaVO.setSalas(cinema.getSalas());
        return cinemaVO;
    }

    public static CinemaVO fromId(Long id){
        CinemaVO cinemaVO = new CinemaVO();
        cinemaVO.idCinema = id;
        return cinemaVO;
    }
}
