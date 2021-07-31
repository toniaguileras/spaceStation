create table user
(
    id         binary(255) not null,
    active     bit,
    created_on datetime(6),
    log_in     datetime(6),
    log_out    datetime(6),
    name       varchar(255),
    surname    varchar(255),
    username   varchar(255),
    primary key (id)
) engine = InnoDB;

create table room
(
    id     binary(255) not null,
    name   varchar(255),
    people integer,
    role   integer,
    primary key (id)
) engine = InnoDB;