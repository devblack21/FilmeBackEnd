package capela.projeto.data.entities;

import capela.projeto.web.vo.SessaoVO;
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
@Entity
@Table(name = "sessoes")
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 2, max = 100)
    @Enumerated(EnumType.STRING)
    private DiaSemana diaSemana;
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "horario",nullable = false)
    private LocalDateTime horario;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Cinema cinema;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Filme filme;
    @NotNull
    @Range(min = 0, max = 99)
    @Column(name = "sala",nullable = false)
    private int sala;

    public static Sessao create(SessaoVO sessaoVO){
        return new ModelMapper().map(sessaoVO, Sessao.class);
    }

}
