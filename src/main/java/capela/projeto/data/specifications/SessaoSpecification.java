package capela.projeto.data.specifications;

import capela.projeto.data.entities.Cinema;
import capela.projeto.data.entities.Sessao;
import org.springframework.data.jpa.domain.Specification;

public final class SessaoSpecification {
    private SessaoSpecification() {
    }

    public static Specification<Sessao> idEq(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

    public static Specification<Sessao> nomeEq(String nome) {
        return (root, query, cb) -> cb.equal(cb.upper(root.get("nome")), nome.toUpperCase());
    }
}
