package capela.projeto.data.entities;

import capela.projeto.web.vo.SessaoVO;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.modelmapper.ModelMapper;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

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
    @Enumerated(EnumType.STRING)
    private DiaSemana diaSemana;
    @Column(name = "horario",nullable = false)
    private LocalTime horario;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Cinema cinema;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Filme filme;
    @NotNull
    @Range(min = 0, max = 99)
    @Column(name = "sala",nullable = false)
    private int sala;

    public static Sessao create(SessaoVO sessaoVO){
        Sessao sessao = new ModelMapper().map(sessaoVO, Sessao.class);
        sessao.setCinema(Cinema.create(sessaoVO.getCinema()));
        sessao.setFilme(Filme.create(sessaoVO.getFilme()));
        return sessao;
    }

}
