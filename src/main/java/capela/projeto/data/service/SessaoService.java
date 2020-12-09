package capela.projeto.data.service;

import capela.projeto.data.entities.DiaSemana;
import capela.projeto.data.entities.Sessao;
import capela.projeto.data.repositories.SessaoDao;
import capela.projeto.web.vo.SessaoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import static capela.projeto.data.specifications.SessaoSpecification.*;
import static capela.projeto.web.exception.ControllerException.notFound;
import static org.springframework.data.jpa.domain.Specification.not;
import static org.springframework.data.jpa.domain.Specification.where;
import static org.springframework.http.ResponseEntity.noContent;

@Service
public class SessaoService implements ServiceInterface<SessaoVO,Long>{

    private final SessaoDao sessaoDao;
    private final FilmeService filmeService;
    private final CinemaService cinemaService;

    @Autowired
    public SessaoService(SessaoDao sessaoDao,FilmeService filmeService, CinemaService cinemaService){
        this.sessaoDao = sessaoDao;
        this.filmeService = filmeService;
        this.cinemaService = cinemaService;
    }

    private boolean isIdExists(Long id){
        AtomicBoolean isExists = new AtomicBoolean(false);
        this.sessaoDao.findOne(where(idEq(id)))
                .ifPresent(it -> { isExists.set(true); } );
        return isExists.get();
    }


    private boolean isSessãoNoPeriodoExists(DiaSemana diaSemana, LocalTime dataSelecionada, Long idCinema , int minutosFilme, int sala, Long idSessao){

        int hour = dataSelecionada.getHour();
        int minutes = dataSelecionada.getMinute();

        int hFilme = minutosFilme / 60;
        int mFilme = minutosFilme % 60;
        int horasIntervaloAnterior = 0;
        if(hour > hFilme)
             horasIntervaloAnterior =   hour - hFilme;
        else{
            horasIntervaloAnterior = hFilme - hour;
        }
        int minutosIntervaloAnterior =  0;
        if(minutes > mFilme)
            minutosIntervaloAnterior =   minutes - mFilme;
        else{
            minutosIntervaloAnterior = mFilme - minutes;
        }



        if(minutosIntervaloAnterior < 0){
            minutosIntervaloAnterior = 60 - minutosIntervaloAnterior ;
        }
        if (horasIntervaloAnterior < 0){
            horasIntervaloAnterior = 24 - horasIntervaloAnterior ;
        }

        int horasIntervaloPosterior = hFilme + hour;
        int minutosIntervaloPosterior = mFilme + minutes;
        if(minutosIntervaloPosterior > 59){
            minutosIntervaloPosterior = minutosIntervaloPosterior -  60;
            horasIntervaloPosterior = horasIntervaloPosterior + 1;
        }
        if (horasIntervaloPosterior > 23){
            horasIntervaloPosterior = horasIntervaloPosterior - 24;
        }

        AtomicBoolean isExists = new AtomicBoolean(false);
        this.sessaoDao.findOne(where(horarioGreaterThan( LocalTime.of(horasIntervaloAnterior,minutosIntervaloAnterior))
                .and(horarioLower(LocalTime.of(horasIntervaloPosterior,minutosIntervaloPosterior)))
                .and(cinemaEq(idCinema))
                .and(salaEq(sala))
                .and(not(idEq(idSessao)))
                .and(diaSemanaEq(diaSemana))))
                .ifPresent(it -> { isExists.set(true); } );

        return isExists.get();
    }

    private boolean isSala(int salas, int sala){
        return (sala > 0 && sala <= salas);
    }

    private SessaoVO convertTosSessaoVO(Sessao sessao){
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
        return this.sessaoDao.findAll().stream().map(this::convertTosSessaoVO).collect(Collectors.toList());
    }

    public List<SessaoVO> findAllCinema(Long idCinema) {
        return this.sessaoDao.findAll(where(cinemaEq(idCinema))).stream().map(this::convertTosSessaoVO).collect(Collectors.toList());
    }

    @Override
    public Page<SessaoVO> findAll(Pageable pageable) {
        return this.sessaoDao.findAll(pageable).map(this::convertTosSessaoVO);
    }

    @Override
    public SessaoVO save(SessaoVO dto) {
        dto.setFilme(this.filmeService.findById(dto.getFilme().getIdFilme()));
        dto.setCinema(this.cinemaService.findById(dto.getCinema().getIdCinema()));

        if(this.isSessãoNoPeriodoExists(dto.getDiaSemana(),dto.getHorario(),dto.getCinema().getIdCinema(),dto.getFilme().getDuracao(),dto.getSala(), dto.getIdSessao())){
            throw notFound("Não foi possivel cadastrar sessão nesse horario, existe uma outra sessão nesse intervalo de tempo nessa mesma sala.");
        }
        if(!this.isSala(dto.getCinema().getSalas(), dto.getSala())){
            throw notFound("numero de sala invalido.");
        }
        return SessaoVO.create(this.sessaoDao.save(Sessao.create(dto)));
    }

    @Override
    public SessaoVO update(SessaoVO dto) {

        dto.setFilme(this.filmeService.findById(dto.getFilme().getIdFilme()));
        dto.setCinema(this.cinemaService.findById(dto.getCinema().getIdCinema()));

        if(!this.isSala(dto.getCinema().getSalas(), dto.getSala())){
            throw notFound("numero de sala invalido.");
        }

        if(this.isSessãoNoPeriodoExists(dto.getDiaSemana(),dto.getHorario(),dto.getCinema().getIdCinema(),dto.getFilme().getDuracao(),dto.getSala(), dto.getIdSessao())){
            throw notFound("Não foi possivel cadastrar sessão nesse horario, existe uma outra sessão nesse intervalo de tempo nessa mesma sala.");
        }

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
