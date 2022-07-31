|**Proyecto final del curso de Ironhack Enterprise-Java-Development**|
|---|
|**Autora:** Sofía González|
|**Título del proyecto:** Helpol|
|**Objetivo de la aplicación:** registrar pedidos de primers|
|**Enlace al repositorio con el frontend:** https://github.com/Sofia-GMT/Enterprise-Java-Development-Helpol-front.git |

## 1. Introducción teórica
### 1.1. ¿Qué es un primer? ¿Para qué sirven?
### 1.2. Motivación para realizar el proyecto

## 2. Estructura de la aplicación
### 2.1. Esquema de los microservicios
### 2.2. Diagrama de clases
### 2.3. Diagrama de casos de uso

## 3. Pasos para correr la aplicación

### 3.1. Para el backend: 

#### 3.1.1. Crear las bases de datos en MySQL
```
  -- GRANT ALL PRIVILEGES TO IRONHACKER
  
GRANT ALL PRIVILEGES ON user_final_project.* TO 'ironhacker'@'localhost';
GRANT ALL PRIVILEGES ON user_final_project_test.* TO 'ironhacker'@'localhost';

GRANT ALL PRIVILEGES ON primers_final_project.* TO 'ironhacker'@'localhost';
GRANT ALL PRIVILEGES ON primers_final_project_test.* TO 'ironhacker'@'localhost';

GRANT ALL PRIVILEGES ON order_final_project.* TO 'ironhacker'@'localhost';
GRANT ALL PRIVILEGES ON order_final_project_test.* TO 'ironhacker'@'localhost';
```
```

  -- USER SERVICE --
  
CREATE SCHEMA user_final_project_test;
CREATE SCHEMA user_final_project;
USE user_final_project;

CREATE TABLE user(
	id INTEGER NOT NULL AUTO_INCREMENT,
    id_institution INTEGER,
    name VARCHAR(255),
    password VARCHAR(255),
    status_user VARCHAR(255),
    PRIMARY KEY(id)
);

INSERT INTO user(id_institution, name, password, status_user) VALUES
(001, "Sonia Rodriguez", "contraseña", "VALIDATED"),
(002, "Alfonso Gomez", "contraseña", "PENDING"),
(003, "Candela Lopez", "contraseña", "REJECTED");
```
```

	-- PRIMERS SERVICE --
    

CREATE SCHEMA primers_final_project_test;
CREATE SCHEMA primers_final_project;
USE primers_final_project;

CREATE TABLE primers(
	id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    forward_sequence VARCHAR(255),
    reverse_sequence VARCHAR(255),
    PRIMARY KEY(id)
);

INSERT INTO primers(name, forward_sequence, reverse_sequence) VALUES
("MYC", "AAAATTTTTCCCCCC", "TTTCCCCCCAAAAAAA"),
("OCT4", "TTTTTTTTTGGGGC", "GGGGGGTTTTTTTAAAA"),
("ADHR", "TTTTTTCCCCCCCCA", "TGGGGGGGGGGTTTTAC");
```
```

	-- ORDER SERVICE
    
CREATE SCHEMA order_final_project_test;
CREATE SCHEMA order_final_project;
USE order_final_project;

CREATE TABLE `order`(
	id INTEGER NOT NULL AUTO_INCREMENT,
    status_order VARCHAR(255),
    price DECIMAL,
    primers_id INTEGER,
    user_id INTEGER,
    PRIMARY KEY(id)
);
```
```
	-- PRESENTATION

USE primers_final_project;
SELECT * FROM primers;

USE order_final_project;
SELECT * FROM orders;

USE user_final_project;
SELECT * FROM user;
```


#### 3.1.2. Corremos los microservicios
Empezando por el eureka-server, hay que correr cada subproyecto para que funcionen todos los microservicios. 
Para ello hay que ejecutar la siguiente línea de código.
```
# Abriendo una terminal en la ubicación del microservicio
spring-boot:run
```
### 3.2. Para el frontend
```
# Abriendo una terminal en la ubicación del proyecto de Angular
npm install
ng serve
```

## Nota adicional
