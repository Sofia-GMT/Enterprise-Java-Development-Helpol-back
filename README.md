|**Proyecto final del curso de Ironhack Enterprise-Java-Development**|
|---|
|**Autora:** Sofía González|
|**Título del proyecto:** Helpol (Help + polymerase)|
|**Objetivo de la aplicación:** registrar pedidos de primers|
|**Enlace al repositorio con el frontend:** https://github.com/Sofia-GMT/Enterprise-Java-Development-Helpol-front.git |

## 1. Introducción teórica
### ¿Qué es un primer? ¿Para qué sirven?

De forma simplificada, los primers son moléculas de DNA formados por la concatenación de unidades llamas nucleótidos. A nivel químico, estos nucleótidos se diferencian por las bases nitrogenadas, distinguiéndose cuatro tipos principales identificables por las letras A, T, C y G.
Por tanto, desde un punto de vista computacional, podemos representar a los nucleótidos como cadenas de estos cuatro caracteres.

In molecular biology, a class of proteins called polymerases are frequently used. These are responsible for extending nucleotide chains, and at the beginning of this elongation they need to use primers.

En biología molecular se utilizan frecuentemente unas proteínas llamadas polimerasas, las cuales se encargan de elongar cadenas de nucleótidos a partir de estos primers. El uso de estas proteínas es fundamental para poder realizar técnicas como la retrotranscripción, la PCR o la concatenación de ambas, es decir, la RT-PCR que se ha mencionado frecuentemente en los medios de comunicación por utilizarse como 
técnica de diagnóstico para la infección por SARS-CoV-2.

## 2. Estructura de la aplicación

### 2.1. Esquema de los microservicios
![jpg](https://github.com/Sofia-GMT/Enterprise-Java-Development-Helpol-back/blob/199c27190853c61464f2304674e80d61028ffa53/final-microservices.jpg)

El frontend se comunica directamente con los microservicios user-service y primers-service, accediendo a sus bases de datos para poder tener el listado de usuarios registrados y el catálogo de primers respectivamente. 

Para poder realizar un pedido, este pasa a través del crud-service, en el cual se vuelve a validar al usuario y al par de primers seleccionados, depués se calcula el precio del pedido, en función de la longitud de las secuencias y la concentración seleccionada, y finalmente se 
transfiere la información al order-service para que guarde el pedido en su base de datos.

### 2.2. Diagrama de clases

![jpg](https://github.com/Sofia-GMT/Enterprise-Java-Development-Helpol-back/blob/dbb1092986e9db5884c702adcffa172ed2ab908f/final-class.jpg)

Se distinguen dos relaciones ONE TO MANY, estándo en el lado MANY la entidad ORDER, mientras que en el ONE están User y Primers.
Esto se debe a que un pedido (order) solo puede pertenecer a un usuario (userId) y solo puede contener un par de primers (primersId),
mientras que un usuario puede realizar varios pedidos y una pareja de primers puede estar solicitada en varios pedidos.

### 2.3. Diagrama de casos de uso

![jpg](https://github.com/Sofia-GMT/Enterprise-Java-Development-Helpol-back/blob/0abeeef9912ab9ceb05dbb47f7669de71dde2766/final-case.jpg)

Cuando un profesional accede a la aplicación puede utilizar la calculadora (CG% y temperatura), registrarse o identificarse en el sistema, si ya se había registrado previamente. Una vez identificado puede realizar un pedido con los primers que están en ese momento en la base de datos, o puede añadir un nuevo par de primers en la base de datos.

Cabe destacar que para que un profesional pueda realizar un pedido, es necesario que su usuario haya sido validado por la institución, puesto que es a esta a la que se cobra el coste, ya que es la que financia el proyecto de investigaci

Asímismo, el sistema de seguimiento del pedido es capaz de actualizar el estatus de este, cuyas opciones son procesado, enviado y recibido.

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

## 4. Alcance del proyecto

El sistema de pedidos, las bases de datos y la calculadora funcionan. Para el futuro se desea implementar un sistema de seguimiento del pedido y otro sistema que envíe a las instuciones un correo para que puedan validar más fácilmente a los usuarios.

## 5. Apunte final
Este proyecto se ha realizado en un intervalo de cinco d
