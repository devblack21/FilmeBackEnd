package capela.projeto.data.specifications;

import capela.projeto.data.entities.Cinema;
import org.springframework.data.jpa.domain.Specification;

public final class CinemaSpecification {
    private CinemaSpecification() {
    }

    public static Specification<Cinema> idEq(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Cinema> nomeEq(String nome) {
        return (root, query, cb) -> cb.equal(cb.upper(root.get("nome")), nome.toUpperCase());
    }
}
