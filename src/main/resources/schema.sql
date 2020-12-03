create table filme
(
    id         int identity,
    nome       varchar(100),
    genero     varchar(50),
    duracao    numeric(3),
    classif    numeric(2),
    lancamento date,
    sinopse    text
);