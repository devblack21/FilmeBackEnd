package capela.projeto.data.service;

import capela.projeto.data.entities.Sessao;
import capela.projeto.data.repositories.SessaoDao;
import capela.projeto.web.vo.SessaoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import static capela.projeto.data.specifications.SessaoSpecification.idEq;
import static capela.projeto.web.exception.ControllerException.conflict;
import static capela.projeto.web.exception.ControllerException.notFound;
import static org.springframework.data.jpa.domain.Specification.not;
import static org.springframework.data.jpa.domain.Specification.where;
import static org.springframework.http.ResponseEntity.noContent;

@Service
public class SessaoService implements ServiceInterface<SessaoVO,Long>{

    private final SessaoDao sessaoDao;

    @Autowired
    public SessaoService(SessaoDao sessaoDao){
        this.sessaoDao = sessaoDao;
    }

    private boolean isIdExists(Long id){
        AtomicBoolean isExists = new AtomicBoolean(false);
        this.sessaoDao.findOne(where(idEq(id)))
                .ifPresent(it -> { isExists.set(true); } );
        return isExists.get();
    }

    private SessaoVO convertToFilmeVO(Sessao sessao){
        return SessaoVO.create(sessao);
    }

    @Override
    public SessaoVO findById(Long id) {
        if(!this.isIdExists(id)){
            throw notFound("Não existe um cadastro de Sessao com o ID inserido.");
        }
        return SessaoVO.create(this.sessaoDao.findById(id).get());
    }

    @Override
    public List<SessaoVO> findAll() {
        return this.sessaoDao.findAll().stream().map(this::convertToFilmeVO).collect(Collectors.toList());
    }

    @Override
    public Page<SessaoVO> findAll(Pageable pageable) {
        return this.sessaoDao.findAll(pageable).map(this::convertToFilmeVO);
    }

    @Override
    public SessaoVO save(SessaoVO dto) {

        return SessaoVO.create(this.sessaoDao.save(Sessao.create(dto)));
    }

    @Override
    public SessaoVO update(SessaoVO dto) {

        return SessaoVO.create(this.sessaoDao.save(Sessao.create(dto)));
    }

    @Override
    public ResponseEntity<?>  delete(Long id) {
        return this.sessaoDao.findById(id)
                .map(it -> {
                    this.sessaoDao.delete(it);
                    return noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
