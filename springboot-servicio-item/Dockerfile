### Imagen y versión que se va a utilizar
FROM openjdk:8

### Por si guarda archivos como el log
VOLUME /tmp

### Puerto que vamos a utilizar
EXPOSE 8002

### Agregar un archivo, en nuestro el JAR de nuestro Microservicio y lo renombramos.
ADD ./target/springboot-servicio-item-0.0.1-SNAPSHOT.jar servicio-item.jar

### Punto de entrada. Comandos que se van a ejecutar al iniciar la imagen
ENTRYPOINT ["java", "-jar", "/servicio-item.jar"]

### Crear la imagen
# $docker build -t servicio-item:v1 .

### Consultar las imagenes creadas
# $docker image

### Crear Network Docker
# $docker network create curso-ms

### Correr la imagen [puerto_equipo:puerto_imagen name network imagen:versión]
# $docker run -p 8761:8761 --name servicio-item --network curso-ms servicio-item:v1

### Listar los contenedores
# $docker container ls ### $docker ps -a

### Lista de imagenes
# $docker images

### Seguir el Log de una instanacia Docker
# $docker logs -f [nombre de la imagen]

### Ayuda lista de comandos
# $docker --help

### Descargar la imagen de MySQL
# $docker pull mysql:8

### Levantar MySQL
# $docker run -p 3306:3306 --name ms-mysql8 --network curso-ms -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=db_springboot_cloud -d mysql:8