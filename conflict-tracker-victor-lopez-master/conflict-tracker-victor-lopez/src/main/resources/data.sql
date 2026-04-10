-- COUNTRIES
INSERT INTO COUNTRY (CODE, NAME) VALUES ('ESP', 'Espanya');
INSERT INTO COUNTRY (CODE, NAME) VALUES ('ITA', 'Italia');
INSERT INTO COUNTRY (CODE, NAME) VALUES ('JPN', 'Japo');
INSERT INTO COUNTRY (CODE, NAME) VALUES ('KOR', 'Corea');
INSERT INTO COUNTRY (CODE, NAME) VALUES ('USA', 'Estats Units');
INSERT INTO COUNTRY (CODE, NAME) VALUES ('RUS', 'Russia');

-- CONFLICTS (IMPORTANTE: sin RESOLVED)
INSERT INTO CONFLICT (NAME, START_DATE, STATUS, DESCRIPTION, REGION) VALUES
                                                                         ('Guerra Espanya', '2026-02-20', 'ACTIVE', 'Guerra activa degut a la invasio de italia a espanya', 'Europa'),
                                                                         ('Conflicte Japo-Corea', '2025-11-10', 'ACTIVE', 'Tensions militars entre Japo i Corea', 'Asia'),
                                                                         ('Guerra Civil Italiana', '2024-06-15', 'ENDED', 'Conflicte intern a Italia', 'Europa'),
                                                                         ('Conflicte Mediterrani', '2025-03-05', 'ACTIVE', 'Disputes maritimes', 'Europa'),
                                                                         ('Guerra Tecnologica Global', '2023-09-01', 'ACTIVE', 'Conflicte tecnologico global', 'Global'),
                                                                         ('Conflicte Artic', '2025-01-20', 'FROZEN', 'Disputes a l Artic', 'Artic');

-- EVENTS
INSERT INTO EVENT (EVENT_DATE, DESCRIPTION, LOCATION, CONFLICT_ID) VALUES
                                                                       ('2026-02-20', 'Inici guerra Espanya-Italia', 'Espanya', 1),
                                                                       ('2025-11-10', 'Escalada Japo-Corea', 'Asia', 2),
                                                                       ('2024-06-15', 'Guerra civil italiana', 'Italia', 3),
                                                                       ('2025-03-05', 'Conflicte naval', 'Mediterrani', 4),
                                                                       ('2023-09-01', 'Guerra tecnologica', 'Global', 5),
                                                                       ('2025-01-20', 'Conflicte artico', 'Artic', 6);

-- FACTIONS
INSERT INTO FACTION (NAME, CONFLICT_ID) VALUES
                                            ('Espanya', 1),
                                            ('Italia', 1),

                                            ('Japo', 2),
                                            ('Corea', 2),

                                            ('Govern Italia', 3),
                                            ('Rebels', 3),

                                            ('Alianca Mediterrania', 4),
                                            ('Coalicio Naval', 4),

                                            ('Bloc Occidental', 5),
                                            ('Bloc Oriental', 5),

                                            ('Russia', 6),
                                            ('USA', 6);

-- RELACIONES
INSERT INTO CONFLICT_COUNTRY (CONFLICT_ID, COUNTRY_ID) VALUES
                                                           (1,1),(1,2),
                                                           (2,3),(2,4),
                                                           (3,2),
                                                           (4,1),(4,2),
                                                           (5,5),(5,6),
                                                           (6,6),(6,5);