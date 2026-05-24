-- Artistas
INSERT INTO artista (id, nacionalidad, fecha_nacimiento, email) VALUES (1, 'Estadounidense', '1989-12-13', 'taylor@swiftmusic.com');
INSERT INTO artista (id, nacionalidad, fecha_nacimiento, email) VALUES (2, 'Puertorriqueña', '1994-03-10', 'badbunny@rimas.com');
INSERT INTO artista (id, nacionalidad, fecha_nacimiento, email) VALUES (3, 'Colombiana',     '1977-02-02', 'shakira@shakira.com');
INSERT INTO artista (id, nacionalidad, fecha_nacimiento, email) VALUES (4, 'Española',       '1992-09-25', 'rosalia@motomami.com');
INSERT INTO artista (id, nacionalidad, fecha_nacimiento, email) VALUES (5, 'Colombiana',     '1985-05-07', 'jbalvin@jose.com');
INSERT INTO artista (id, nacionalidad, fecha_nacimiento, email) VALUES (6, 'Estadounidense', '2001-12-18', 'billie@darkroom.com');
INSERT INTO artista (id, nacionalidad, fecha_nacimiento, email) VALUES (7, 'Británica',      '1991-02-17', 'ed@sheeranmusic.com');
INSERT INTO artista (id, nacionalidad, fecha_nacimiento, email) VALUES (8, 'Albanesa',       '1995-08-22', 'dua@warnerlipa.com');
INSERT INTO artista (id, nacionalidad, fecha_nacimiento, email) VALUES (9, 'Canadiense',     '1990-02-16', 'weeknd@xoentertainment.com');
INSERT INTO artista (id, nacionalidad, fecha_nacimiento, email) VALUES (10,'Colombiana',     '1991-02-14', 'karolg@interscope.com');

-- Albums
INSERT INTO album (id, titulo, genero, fecha_estreno, artista_id) VALUES (1,  '1989',                  'Pop',            '2014-10-27', 1);
INSERT INTO album (id, titulo, genero, fecha_estreno, artista_id) VALUES (2,  'Un Verano Sin Ti',      'Reggaeton',      '2022-05-06', 2);
INSERT INTO album (id, titulo, genero, fecha_estreno, artista_id) VALUES (3,  'El Dorado',             'Pop Latino',     '2017-05-26', 3);
INSERT INTO album (id, titulo, genero, fecha_estreno, artista_id) VALUES (4,  'Motomami',              'Flamenco',       '2022-03-18', 4);
INSERT INTO album (id, titulo, genero, fecha_estreno, artista_id) VALUES (5,  'Jose',                  'Reggaeton',      '2021-10-29', 5);
INSERT INTO album (id, titulo, genero, fecha_estreno, artista_id) VALUES (6,  'Happier Than Ever',     'Pop',            '2021-07-30', 6);
INSERT INTO album (id, titulo, genero, fecha_estreno, artista_id) VALUES (7,  'Divide',                'Pop',            '2017-03-03', 7);
INSERT INTO album (id, titulo, genero, fecha_estreno, artista_id) VALUES (8,  'Future Nostalgia',      'Dance Pop',      '2020-03-27', 8);
INSERT INTO album (id, titulo, genero, fecha_estreno, artista_id) VALUES (9,  'After Hours',           'R&B',            '2020-03-20', 9);
INSERT INTO album (id, titulo, genero, fecha_estreno, artista_id) VALUES (10, 'Manana Sera Bonito',    'Reggaeton',      '2023-02-24', 10);

-- Canciones
INSERT INTO cancion (id, nombre, duracion, album_id) VALUES (1,  'Shake It Off',       3.55, 1);
INSERT INTO cancion (id, nombre, duracion, album_id) VALUES (2,  'Me Porto Bonito',    2.98, 2);
INSERT INTO cancion (id, nombre, duracion, album_id) VALUES (3,  'Chantaje',           3.73, 3);
INSERT INTO cancion (id, nombre, duracion, album_id) VALUES (4,  'SAOKO',              2.87, 4);
INSERT INTO cancion (id, nombre, duracion, album_id) VALUES (5,  'In Da Getto',        3.05, 5);
INSERT INTO cancion (id, nombre, duracion, album_id) VALUES (6,  'Happier Than Ever',  4.87, 6);
INSERT INTO cancion (id, nombre, duracion, album_id) VALUES (7,  'Shape of You',       3.53, 7);
INSERT INTO cancion (id, nombre, duracion, album_id) VALUES (8,  'Levitating',         3.23, 8);
INSERT INTO cancion (id, nombre, duracion, album_id) VALUES (9,  'Blinding Lights',    3.20, 9);
INSERT INTO cancion (id, nombre, duracion, album_id) VALUES (10, 'CAIRO',              3.58, 10);
