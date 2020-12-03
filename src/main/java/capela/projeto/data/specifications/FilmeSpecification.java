package capela.projeto.data.specifications;

import capela.projeto.data.entities.Filme;
import org.springframework.data.jpa.domain.Specification;

public final class FilmeSpecification {
    private FilmeSpecification() {
    }

    public static Specification<Filme> idEq(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Filme> nomeEq(String nome) {
        return (root, query, cb) -> cb.equal(cb.upper(root.get("nome")), nome.toUpperCase());
    }
}
