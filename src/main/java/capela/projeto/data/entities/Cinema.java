package capela.projeto.data.entities;

import capela.projeto.web.vo.CinemaVO;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.modelmapper.ModelMapper;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "cinemas")
public class Cinema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "nome",nullable = false)
    private String nome;
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "cidade",nullable = false)
    private String cidade;
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "estado",nullable = false)
    private String estado;
    @NotNull
    @Range(min = 0, max = 99)
    @Column(name = "salas",nullable = false)
    private Integer salas;

    public static Cinema create(CinemaVO cinemaVO){
        return new ModelMapper().map(cinemaVO, Cinema.class);
    }

}
