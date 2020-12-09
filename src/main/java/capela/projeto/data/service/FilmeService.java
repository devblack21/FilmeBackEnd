package capela.projeto.data.service;

import capela.projeto.data.entities.Filme;
import capela.projeto.data.repositories.FilmeDao;
import capela.projeto.web.vo.FilmeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import static capela.projeto.data.specifications.FilmeSpecification.*;
import static capela.projeto.web.exception.ControllerException.conflict;
import static org.springframework.data.jpa.domain.Specification.not;
import static org.springframework.data.jpa.domain.Specification.where;
import static capela.projeto.web.exception.ControllerException.notFound;
import static org.springframework.http.ResponseEntity.noContent;

@Service
public class FilmeService implements ServiceInterface<FilmeVO,Long>{

    private final FilmeDao filmeDao;

    @Autowired
    public FilmeService(FilmeDao filmeDao){
        this.filmeDao = filmeDao;
    }

    private boolean isIdExists(Long id){
        AtomicBoolean isExists = new AtomicBoolean(false);
        this.filmeDao.findOne(where(idEq(id)))
                .ifPresent(it -> { isExists.set(true); } );
        return isExists.get();
    }

    private boolean isNomeExists(String nome){
        AtomicBoolean isExists = new AtomicBoolean(false);
          this.filmeDao.findOne(where(nomeEq(nome)))
                .ifPresent(it -> { isExists.set(true); } );

        System.out.println(isExists);
          return isExists.get();
    }

    private boolean isNomeExistsUp(String nome, Long id){
        AtomicBoolean isExists = new AtomicBoolean(false);
        this.filmeDao.findOne(where(nomeEq(nome)).and(not(idEq(id))))
                .ifPresent(it -> { isExists.set(true); } );

        System.out.println(isExists);
        return isExists.get();
    }

    private FilmeVO convertToFilmeVO(Filme filme){
        return FilmeVO.create(filme);
    }

    @Override
    public FilmeVO findById(Long id) {
        if(!this.isIdExists(id)){
            throw notFound("Não existe um cadastro de filme com o ID inserido.");
        }
        return FilmeVO.create(this.filmeDao.findById(id).get());
    }

    @Override
    public List<FilmeVO> findAll() {
        return this.filmeDao.findAll().stream().map(this::convertToFilmeVO).collect(Collectors.toList());
    }

    @Override
    public Page<FilmeVO> findAll(Pageable pageable) {
        return this.filmeDao.findAll(pageable).map(this::convertToFilmeVO);
    }

    @Override
    public FilmeVO save(FilmeVO dto) {
        if(this.isNomeExists(dto.getNome())){
            throw conflict("Já existe um Filme com o mesmo Nome.");
        }
        return FilmeVO.create(this.filmeDao.save(Filme.create(dto)));
    }

    @Override
    public FilmeVO update(FilmeVO dto) {
        if(this.isNomeExistsUp(dto.getNome(),dto.getIdFilme())){
            throw conflict("Já existe um Filme com o mesmo Nome.");
        }
        return FilmeVO.create(this.filmeDao.save(Filme.create(dto)));
    }

    @Override
    public ResponseEntity<?>  delete(Long id) {
        return this.filmeDao.findById(id)
                .map(it -> {
                    this.filmeDao.delete(it);
                    return noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
