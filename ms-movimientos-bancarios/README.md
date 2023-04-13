# ms-movimientos-bancarios

Proyecto backend API REST SpringBoot


### Control de versiones

| Version | Etapa      | Fecha      |
| ------- | ---------- | ---------- |
| 1.0     | Desarrollo | 12/04/2023 |


### Aspectos Técnicos

Diseñado con las siguientes tecnologías|

* [Spring Boot] 2.7.10 Microservicio REST
* [Java] JDK 11
* [Maven] 3.0.2


### Configuración de aplicación

### Variables comunes

| Variable                 			    | Descripción             									| Por Defecto	|
| --------------------------------------| --------------------------------------------------------- | --------------|  
| SERVER_PORT                  			| Corresponde al puerto asignado al microservicio.      	| 8080      	|


### Variables particulares del proyecto

El servicio necesita configuraciones propias de la aplicación y que se configuren los datos necesarios para hacer las conexiones entre los diferentes servicios del proyecto de la ms-movimientos-bancarios en los ambientes correspondientes . Por lo cual, se definen las siguientes variables de entorno.

| Variable                 					| Descripción                                                          | Por Defecto        		        												|
| ------------------------------------------| -------------------------------------------------------------------- | -----------------------------------------------------------------------------------|  
| MOVIMIENTOS_BANCARIOS_POSV2_DRIVER_CLASS		    	| Corresponde al driver	para la conexion a la base de datos.		   | org.postgresql.Driver													|
| MOVIMIENTOS_BANCARIOS_POSV2_URL				        | Corresponde a la url para la conexion a la base de datos.		       | jdbc:postgresql://localhost:5432/sistema								|
| MOVIMIENTOS_BANCARIOS_POSV2_USERNAME			        | Corresponde al nombre de usuariopara la conexion a la base de datos. | postgres																|
| MOVIMIENTOS_BANCARIOS_POSV2_PASSWORD				    | Corresponde a la contraseña para la conexion a la base de datos.	   | 12345678																|


### Creación Base de Datos y Tablas base de datos.

| Se requiere la ejecución de un script para crear la base de datos y  generar las tablas e inserts correspondientes. El archivo se encuentra en: resources/sql/BaseDatos.sql							|
| proyecto:  ms-movimientos-bancarios	|
| https:  https://github.com/EdgarRicardoHernandez/ms-movimientos-bancarios.git  	|
| swagger: http://localhost:8081/ms-movimientos-bancarios/swagger-ui/index.html		|
1 Postman Collection: https://github.com/EdgarRicardoHernandez/ms-movimientos-bancarios/blob/main/ms-movimientos-bancarios/src/main/resources/PostmanCollection/ms-movimientos-bancarios.postman_collection.json	|