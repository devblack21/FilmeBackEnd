package capela.projeto.data.specifications;

import capela.projeto.data.entities.DiaSemana;
import capela.projeto.data.entities.Sessao;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalTime;

public final class SessaoSpecification {

    private SessaoSpecification() {}

    public static Specification<Sessao> idEq(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Sessao> nomeEq(String nome) {
        return (root, query, cb) -> cb.equal(cb.upper(root.get("nome")), nome.toUpperCase());
    }

    public static Specification<Sessao> horarioGreaterThan(LocalTime horario){
        return (root, query, cb) -> cb.greaterThan((root.get("horario")), horario);
    }

    public static Specification<Sessao> horarioLower(LocalTime horario){
        return (root, query, cb) -> cb.lessThan((root.get("horario")), horario);
    }

    public static Specification<Sessao> diaSemanaEq(DiaSemana diaSemana){
        return (root, query, cb) -> cb.equal(cb.upper(root.get("diaSemana")), diaSemana );
    }

    public static Specification<Sessao> salaEq(int sala){
        return (root, query, cb) -> cb.equal(cb.upper(root.get("sala")), sala );
    }

    public static Specification<Sessao> cinemaEq(long idCinema){
        return (root, query, cb) -> cb.equal(cb.upper(root.get("cinema")), idCinema );
    }
}
