package capela.projeto.data.service;


import static capela.projeto.web.exception.ControllerException.notFound;
import static capela.projeto.web.exception.ControllerException.*;
import static java.lang.String.*;
import static org.springframework.data.jpa.domain.Specification.*;
import static org.springframework.http.ResponseEntity.notFound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
public interface ServiceInterface<T, ID> {

    public T findById(ID id);
    public List<T> findAll();
    public Page<T> findAll(Pageable pageable);
    @Transactional
    public T save(T dto);
    @Transactional
    public T update(T dto);
    @Transactional
    public ResponseEntity<?> delete(ID id);

}
