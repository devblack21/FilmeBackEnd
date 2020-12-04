package capela.projeto.data.specifications;

import capela.projeto.data.entities.Cinema;
import capela.projeto.data.entities.DiaSemana;
import capela.projeto.data.entities.Sessao;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public final class SessaoSpecification {
    private SessaoSpecification() {
    }

    public static Specification<Sessao> idEq(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Sessao> nomeEq(String nome) {
        return (root, query, cb) -> cb.equal(cb.upper(root.get("nome")), nome.toUpperCase());
    }

    public static Specification<Sessao> isSessaoBeetwenHour(DiaSemana diaSemana, LocalDateTime dataSelecionada, Long idCinema , int minutosFilme, int sala){


        //pegar o valor da data selecionada somar com o tempo de filme em minutos
        //pegar o valor da data selecionada subtrair com o tempo de filme em minutos
        //logo vamos ter o intervalo necessario para verificar se tem uma sessão cadastrada naquela sala durante esse intervalo de tempo

        return null;
    }
}
