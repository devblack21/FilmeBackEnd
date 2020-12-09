package capela.projeto.web.vo;

import capela.projeto.data.entities.DiaSemana;
import capela.projeto.data.entities.Sessao;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import java.io.Serializable;
import java.time.LocalTime;

@JsonPropertyOrder({"idSessao","diaSemana","horario","cinema","filme","sala"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SessaoVO implements Serializable {

    @JsonProperty("idSessao")
    private Long idSessao;
    @JsonProperty("diaSemana")
    private DiaSemana diaSemana;
    @JsonProperty("horario")
    @JsonFormat(pattern = "HH:mm", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private LocalTime horario;
    @JsonProperty("cinema")
    private CinemaVO cinema;
    @JsonProperty("filme")
    private FilmeVO filme;
    @Range(min = 0, max = 99)
    @JsonProperty("sala")
    private int sala;

    @JsonCreator
    public SessaoVO(@JsonProperty("cinemaid") Long idCinema, @JsonProperty("filmeid") Long filmeId)
    {
        cinema = CinemaVO.fromId(idCinema);
        filme = FilmeVO.fromId(filmeId);
    }

   @JsonIgnore
    public void setCinema(Long id){

        cinema = CinemaVO.fromId(id);
    }

    public void setCinema(CinemaVO cinema){
        this.cinema = cinema;
    }

    @JsonIgnore
    public void setFilme(Long id){

        filme = FilmeVO.fromId(id);
    }

    public void setFilme(FilmeVO filme){

        this.filme = filme;
    }

    public static SessaoVO create(Sessao sessao){
        SessaoVO sessaoVO = new SessaoVO();
        sessaoVO.setFilme(FilmeVO.create(sessao.getFilme()));
        sessaoVO.setCinema(CinemaVO.create(sessao.getCinema()));
        sessaoVO.setFilme(FilmeVO.create(sessao.getFilme()));
        sessaoVO.setIdSessao(sessao.getId());
        sessaoVO.setDiaSemana(sessao.getDiaSemana());
        sessaoVO.setHorario(sessao.getHorario());
        sessaoVO.setSala(sessao.getSala());
        return sessaoVO;
    }
}
