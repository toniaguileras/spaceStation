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


-- room data
INSERT INTO spacestation.room (id, name, role)
VALUES ('2a2c8c0f-914a-4bfd-8389-b6687667e6d8', 'Space ship', 'ASTRONAUT');
INSERT INTO spacestation.room (id, name, role)
VALUES ('92b0d4e9-04aa-49dd-81ef-7a7e82d7cc59', 'Hall', 'ANY');
INSERT INTO spacestation.room (id, name, role)
VALUES ('9a359a58-7b0d-4bef-90ba-34f20aaabe6c', 'Toilets', 'ANY');
INSERT INTO spacestation.room (id, name, role)
VALUES ('b57e0c50-50ff-44f7-8097-8c02bc45ec42', 'Control room', 'ANALYST');

-- user data
INSERT INTO spacestation.user (id, active, created_on, log_in, log_out, name, surname, username, role, room_id)
VALUES ('375f5bc6-690a-4e0d-a280-6aaf051396f7', true, '2021-08-01 21:46:59', '2021-08-01 21:47:00',
        '2021-08-01 21:47:02', 'David', 'Dominguez', 'daviddominguez', 'CLEANER',
        'b57e0c50-50ff-44f7-8097-8c02bc45ec42');
INSERT INTO spacestation.user (id, active, created_on, log_in, log_out, name, surname, username, role, room_id)
VALUES ('9af37f31-6522-4cb4-b2df-a79fdea2cefb', true, '2021-07-31 19:39:21', '2021-07-31 19:39:23',
        '2021-07-31 19:39:25', 'Toni', 'Aguilera', 'toniaguilera', 'ASTRONAUT', '2a2c8c0f-914a-4bfd-8389-b6687667e6d8');
INSERT INTO spacestation.user (id, active, created_on, log_in, log_out, name, surname, username, role, room_id)
VALUES ('c724c360-e1f7-45a3-a5b9-025d3d37ac31', true, '2021-08-01 21:44:45', '2021-08-01 21:44:46',
        '2021-08-01 21:44:51', 'Angel', 'Ramirez', 'angelramirez', 'ANALYST', 'b57e0c50-50ff-44f7-8097-8c02bc45ec42');