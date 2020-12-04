package capela.projeto.data.repositories;

import capela.projeto.data.entities.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CinemaDao extends JpaRepository<Cinema, Long>, JpaSpecificationExecutor<Cinema> {
}
