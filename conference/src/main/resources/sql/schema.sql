create table demo (
	id IDENTITY PRIMARY KEY,
    data VARCHAR(100)
);

INSERT INTO demo (data) VALUES ('Hello, world!');
INSERT INTO demo (data) VALUES ('Hello, world again!');
INSERT INTO demo (data) VALUES ('Hello, world for the third time!');


create table conference (
    id int,
    name VARCHAR(100) not null,
    startDate datetime not null,
    endDate datetime not null,
    PRIMARY KEY (id)
);

create index startDate on conference (startDate);
create index endDate on conference (endDate);

INSERT INTO conference (id, name, startDate, endDate) VALUES (1, 'NFQ Akademijos atidarymas. Įvadas į šiuolaikinio programuotojo darbą', '2013-09-30 17:00:00', '2013-09-30 18:00:00');
INSERT INTO conference (id, name, startDate, endDate) VALUES (2, 'Projekto valdymas ir Agile', '2013-10-02 17:00:00', '2013-10-02 18:00:00');
INSERT INTO conference (id, name, startDate, endDate) VALUES (3, 'J2EE programavimui naudojami įrankiai', '2013-10-07 17:00:00', '2013-10-07 18:00:00');
INSERT INTO conference (id, name, startDate, endDate) VALUES (4, 'Web puslapių vartotojo sąsajos kūrimas (1-a dalis)', '2013-10-09 17:00:00', '2013-10-09 18:00:00');

COMMIT;