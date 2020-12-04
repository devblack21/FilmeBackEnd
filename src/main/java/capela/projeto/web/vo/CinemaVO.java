package capela.projeto.web.vo;

import capela.projeto.data.entities.Cinema;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.modelmapper.ModelMapper;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CinemaVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 2, max = 100)
    private String nome;
    @NotBlank
    @Size(min = 2, max = 100)
    private String cidade;
    @NotBlank
    @Size(min = 2, max = 100)
    private String estado;
    @NotNull
    @Range(min = 0, max = 99)
    private Integer salas;

    public static CinemaVO create(Cinema cinema){
        return new ModelMapper().map(cinema, CinemaVO.class);
    }


}
