package capela.projeto.data.service;

import capela.projeto.data.entities.Cinema;
import capela.projeto.data.repositories.CinemaDao;
import capela.projeto.web.vo.CinemaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import static capela.projeto.data.specifications.CinemaSpecification.idEq;
import static capela.projeto.data.specifications.CinemaSpecification.nomeEq;
import static capela.projeto.web.exception.ControllerException.conflict;
import static capela.projeto.web.exception.ControllerException.notFound;
import static org.springframework.data.jpa.domain.Specification.not;
import static org.springframework.data.jpa.domain.Specification.where;
import static org.springframework.http.ResponseEntity.noContent;

@Service
public class CinemaService implements ServiceInterface<CinemaVO,Long>{

    private final CinemaDao cinemaDao;

    @Autowired
    public CinemaService(CinemaDao cinemaDao){
        this.cinemaDao = cinemaDao;
    }

    private boolean isIdExists(Long id){
        AtomicBoolean isExists = new AtomicBoolean(false);
        this.cinemaDao.findOne(where(idEq(id)))
                .ifPresent(it -> { isExists.set(true); } );
        return isExists.get();
    }

    private boolean isNomeExists(String nome, Long id){
        AtomicBoolean isExists = new AtomicBoolean(false);
          this.cinemaDao.findOne(where(nomeEq(nome).and(not(idEq(id)))))
                .ifPresent(it -> { isExists.set(true); } );
          return isExists.get();
    }

    private CinemaVO convertToCinemaVO(Cinema cinema){
        return CinemaVO.create(cinema);
    }

    @Override
    public CinemaVO findById(Long id) {
        if(!this.isIdExists(id)){
            throw notFound("Não existe um cadastro de Cinema com o ID inserido.");
        }
        return CinemaVO.create(this.cinemaDao.findById(id).get());
    }

    @Override
    public List<CinemaVO> findAll() {
        return this.cinemaDao.findAll().stream().map(this::convertToCinemaVO).collect(Collectors.toList());
    }

    @Override
    public Page<CinemaVO> findAll(Pageable pageable) {
        return this.cinemaDao.findAll(pageable).map(this::convertToCinemaVO);
    }

    @Override
    public CinemaVO save(CinemaVO dto) {
        if(this.isNomeExists(dto.getNome(),dto.getId())){
            throw conflict("Já existe um Cinema com o mesmo Nome.");
        }
        return CinemaVO.create(this.cinemaDao.save(Cinema.create(dto)));
    }

    @Override
    public CinemaVO update(CinemaVO dto) {
        if(this.isNomeExists(dto.getNome(),dto.getId())){
            throw conflict("Já existe um Cinema com o mesmo Nome.");
        }
        return CinemaVO.create(this.cinemaDao.save(Cinema.create(dto)));
    }

    @Override
    public ResponseEntity<?>  delete(Long id) {
        return this.cinemaDao.findById(id)
                .map(it -> {
                    this.cinemaDao.delete(it);
                    return noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
