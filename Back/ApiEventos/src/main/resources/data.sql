INSERT INTO role (nombre) VALUES ('Admin');
INSERT INTO role (nombre) VALUES ('User');

INSERT INTO usuario (usuario, password, email, nombre, apellidos, id_role, activo) VALUES ('admin1', 'password1', 'admin1@example.com', 'Admin', 'One', 1, true);
INSERT INTO usuario (usuario, password, email, nombre, apellidos, id_role, activo) VALUES ('user1', 'password2', 'user1@example.com', 'User', 'One', 2, true);

INSERT INTO ofertante (id) VALUES (1);

INSERT INTO demandante (id) VALUES (2);

INSERT INTO admin (id) VALUES (1);

INSERT INTO categoria (name) VALUES ("Deporte");
INSERT INTO categoria (name) VALUES ("Baile");

INSERT INTO oferta (title, descripcion, date, localizacion, price, estado, id_ofertante, id_admin_check)
VALUES ('Oferta 1', 'Descripcion Oferta 1', '2023-05-20', 'Localizacion 1', 100.0, 'Activa', 1, 1);
INSERT INTO oferta (title, descripcion, date, localizacion, price, estado, id_ofertante, id_admin_check)
VALUES ('Oferta 2', 'Descripcion Oferta 2', '2023-05-21', 'Localizacion 2', 200.0, 'Activa', 1, 1);

INSERT INTO demanda (title, descripcion, estado, id_demandante, id_admin_check)
VALUES ('Demanda 1', 'Descripcion Demanda 1', 'Pendiente', 2, 1);
INSERT INTO demanda (title, descripcion, estado, id_demandante, id_admin_check)
VALUES ('Demanda 2', 'Descripcion Demanda 2', 'Pendiente', 2, 1);

INSERT INTO post (descripcion, id_demanda, id_oferta, id_usuario)
VALUES ('Post Descripcion 1', 1, NULL, 2);
INSERT INTO post (descripcion, id_demanda, id_oferta, id_usuario)
VALUES ('Post Descripcion 2', NULL, 1, 2);

INSERT INTO oferta_demandante (id_oferta, id_demandante) VALUES (1, 2);
INSERT INTO oferta_demandante (id_oferta, id_demandante) VALUES (2, 2);

INSERT INTO competencia (name) VALUES ('Competencia 1');
INSERT INTO competencia (name) VALUES ('Competencia 2');

INSERT INTO competencia_ofertante (id_competencia, id_ofertante, estado, id_admin) VALUES (1, 1, 'Activa', 1);
INSERT INTO competencia_ofertante (id_competencia, id_ofertante, estado, id_admin) VALUES (2, 1, 'Activa', 1);

INSERT INTO comentario (calificacion, descripcion, id_oferta, id_demandante) VALUES (5, 'Buen servicio', 1, 2);
INSERT INTO comentario (calificacion, descripcion, id_oferta, id_demandante) VALUES (4, 'Satisfactorio', 2, 2);

INSERT INTO oferta_categoria (oferta_id, categoria_id) VALUES (1, 1);
INSERT INTO oferta_categoria (oferta_id, categoria_id) VALUES (2, 2);

INSERT INTO demanda_categoria (demanda_id, categoria_id) VALUES (1, 1);
INSERT INTO demanda_categoria (demanda_id, categoria_id) VALUES (2, 2);

INSERT INTO competencia_categoria (competencia_id, categoria_id) VALUES (1, 1);
INSERT INTO competencia_categoria (competencia_id, categoria_id) VALUES (2, 2);
