package capela.projeto.data.repositories;

import capela.projeto.data.entities.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FilmeDao extends JpaRepository<Filme, Long>, JpaSpecificationExecutor<Filme> {
}
