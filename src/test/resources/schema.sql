create table cargoes (
    ref varchar(64) not null primary key,
    arrival datetime not null,
    departure datetime not null
) ;

create table containers (
    ref varchar(64) not null primary key,
    cargoRef varchar(64) not null
) ;

create table goods (
    ref varchar(64) not null primary key,
    type varchar(64) not null,
    containerRef varchar(64) not null,
    quantity int not null
) ;