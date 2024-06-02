CREATE TABLE IF NOT EXISTS role (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      nombre VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuario (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         usuario VARCHAR(255) UNIQUE NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         email VARCHAR(255) UNIQUE NOT NULL,
                         nombre VARCHAR(255) NOT NULL,
                         apellidos VARCHAR(255) NOT NULL,
                         id_role BIGINT,
                         activo BOOLEAN NOT NULL,
                         FOREIGN KEY (id_role) REFERENCES role(id)
);

CREATE TABLE IF NOT EXISTS ofertante (
                           id BIGINT PRIMARY KEY,
                           FOREIGN KEY (id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS demandante (
                            id BIGINT PRIMARY KEY,
                            FOREIGN KEY (id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS admin (
                       id BIGINT PRIMARY KEY,
                       FOREIGN KEY (id) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS categoria (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS oferta (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(255),
                        descripcion TEXT,
                        date DATE,
                        localizacion VARCHAR(255),
                        price DOUBLE,
                        estado VARCHAR(255),
                        id_ofertante BIGINT,
                        id_admin_check BIGINT,
                        FOREIGN KEY (id_ofertante) REFERENCES ofertante(id),
                        FOREIGN KEY (id_admin_check) REFERENCES admin(id)
);

CREATE TABLE IF NOT EXISTS demanda (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(255),
                         descripcion TEXT,
                         estado VARCHAR(255),
                         id_demandante BIGINT,
                         id_admin_check BIGINT,
                         FOREIGN KEY (id_demandante) REFERENCES demandante(id),
                         FOREIGN KEY (id_admin_check) REFERENCES admin(id)
);

CREATE TABLE IF NOT EXISTS post (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      descripcion TEXT,
                      id_demanda BIGINT,
                      id_oferta BIGINT,
                      id_usuario BIGINT NOT NULL,
                      FOREIGN KEY (id_demanda) REFERENCES demanda(id),
                      FOREIGN KEY (id_oferta) REFERENCES oferta(id),
                      FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS oferta_demandante (
                                   id_oferta BIGINT NOT NULL,
                                   id_demandante BIGINT NOT NULL,
                                   PRIMARY KEY (id_oferta, id_demandante),
                                   FOREIGN KEY (id_oferta) REFERENCES oferta(id),
                                   FOREIGN KEY (id_demandante) REFERENCES demandante(id)
);

CREATE TABLE IF NOT EXISTS competencia (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS competencia_ofertante (
                                       id_competencia BIGINT NOT NULL,
                                       id_ofertante BIGINT NOT NULL,
                                       estado VARCHAR(255),
                                       id_admin BIGINT,
                                       PRIMARY KEY (id_competencia, id_ofertante),
                                       FOREIGN KEY (id_competencia) REFERENCES competencia(id),
                                       FOREIGN KEY (id_ofertante) REFERENCES ofertante(id),
                                       FOREIGN KEY (id_admin) REFERENCES admin(id)
);

CREATE TABLE IF NOT EXISTS comentario (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            calificacion INTEGER,
                            descripcion TEXT,
                            id_oferta BIGINT,
                            id_demandante BIGINT,
                            FOREIGN KEY (id_oferta, id_demandante) REFERENCES oferta_demandante(id_oferta, id_demandante)
);

CREATE TABLE IF NOT EXISTS oferta_categoria (
                                  oferta_id BIGINT NOT NULL,
                                  categoria_id BIGINT NOT NULL,
                                  PRIMARY KEY (oferta_id, categoria_id),
                                  FOREIGN KEY (oferta_id) REFERENCES oferta(id),
                                  FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);

CREATE TABLE IF NOT EXISTS demanda_categoria (
                                   demanda_id BIGINT NOT NULL,
                                   categoria_id BIGINT NOT NULL,
                                   PRIMARY KEY (demanda_id, categoria_id),
                                   FOREIGN KEY (demanda_id) REFERENCES demanda(id),
                                   FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);

CREATE TABLE IF NOT EXISTS competencia_categoria (
                                       competencia_id BIGINT NOT NULL,
                                       categoria_id BIGINT NOT NULL,
                                       PRIMARY KEY (competencia_id, categoria_id),
                                       FOREIGN KEY (competencia_id) REFERENCES competencia(id),
                                       FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);
