package capela.projeto.data.convert;

import capela.projeto.data.entities.DiaSemana;
import org.springframework.core.convert.converter.Converter;

public class DiaSemanaConverter implements Converter<String, DiaSemana> {
    @Override
    public DiaSemana convert(String source) {
        return DiaSemana.valueOf(source.toUpperCase());
    }
}
