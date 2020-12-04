package capela.projeto.data.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import capela.projeto.web.vo.FilmeVO;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.modelmapper.ModelMapper;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "filmes")
public class Filme implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "nome",nullable = false)
    private String nome;
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "genero",nullable = false)
    private String genero;
    @NotNull
    @Range(min = 1, max = 999)
    @Column(name = "duracao",nullable = false)
    private Integer duracao;
    @NotNull
    @Range(min = 0, max = 99)
    @Column(name = "classif",nullable = false)
    private Integer classificacao;
    @NotNull
    @Column(name = "lancamento",nullable = false)
    private LocalDate lancamento;
    @NotBlank
    @Size(min = 2)
    @Column(name = "sinopse",nullable = false,length = 512)
    private String sinopse;

    public static Filme create(FilmeVO filmeVo){
        return new ModelMapper().map(filmeVo, Filme.class);
    }
}