create table filmes
(
    id         bigint primary key auto_increment,
    nome       varchar(100) not null,
    genero     varchar(50) not null,
    duracao    numeric(3) not null,
    classif    numeric(2) not null,
    lancamento date not null,
    sinopse    text
);

create table cinemas 
(
	 id        bigint primary key auto_increment,
    nome       varchar(100) not null, 
    cidade     varchar(50) not null,
    estado    varchar(50) not null,
    salas    int not null
);

create table sessoes 
(
	 id        bigint primary key auto_increment,
    dia_semana       varchar(40) not null, 
    horario     time not null,
    cinema_id   bigint not null,
    filme_id   bigint not null,
    sala int not null,
    foreign key (cinema_id) references cinemas(id),
    foreign key (filme_id) references filmes(id)
);
