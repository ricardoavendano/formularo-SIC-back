/*-------------------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
  idusuario VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  intento INT NOT NULL,
  PRIMARY KEY (idusuario));
 
INSERT INTO usuario (idusuario, password, intento) VALUES 
('usuario1', 'dXN1YXJpbzE=', 0);
/*-------------------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS marcapc;
CREATE TABLE marcapc (
  idmarcapc INT NOT NULL AUTO_INCREMENT,
  marca VARCHAR(100) NOT NULL,
  PRIMARY KEY (idmarcapc));

INSERT INTO marcapc (marca) VALUES
('MacBook'),
('HP'),
('Lenovo'),
('Toshiba');
/*-------------------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS encuesta;
CREATE TABLE encuesta (
  idencuesta INT NOT NULL AUTO_INCREMENT,
  documento INT NOT NULL,
  email VARCHAR(100) NOT NULL,
  comentario VARCHAR(500) NOT NULL,
  fecharespuesta TIMESTAMP,
  idmarcapcPK INT NOT NULL,
  idusuarioPK VARCHAR(45) NULL,
  PRIMARY KEY (idencuesta));

ALTER TABLE encuesta 
ADD CONSTRAINT idusuarioPK
  FOREIGN KEY (idusuarioPK)
  REFERENCES usuario (idusuario);
 
ALTER TABLE encuesta 
ADD CONSTRAINT idmarcapcPK
    FOREIGN KEY (idmarcapcPK)
    REFERENCES marcapc (idmarcapc);
/*-------------------------------------------------------------------------------------------*/