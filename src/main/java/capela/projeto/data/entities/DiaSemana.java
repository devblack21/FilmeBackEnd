package capela.projeto.data.entities;

import lombok.ToString;

@ToString
public enum DiaSemana {
    DOMINGO("Domingo"),
    SEGUNDA("Segunda-Feira"),
    TERCA("Terça-Feira"),
    QUARTA("Quarta-Feira"),
    QUINTA("Quinta-Feira"),
    SEXTA("Sexta-Feira"),
    SABADO("Sábado");

    private DiaSemana(String value){
        this.value = value;
    }

    private String value;
}
