package capela.projeto.data.repositories;

import capela.projeto.data.entities.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SessaoDao extends JpaRepository<Sessao, Long>, JpaSpecificationExecutor<Sessao> {
}
