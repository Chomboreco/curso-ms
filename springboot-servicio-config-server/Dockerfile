### Imagen y versión que se va a utilizar
FROM openjdk:8

### Por si guarda archivos como el log
VOLUME /tmp

### Puerto que vamos a utilizar
EXPOSE 8888

### Agregar un archivo, en nuestro el JAR de nuestro Microservicio y lo renombramos.
ADD ./target/springboot-servicio-config-server-0.0.1-SNAPSHOT.jar config-server.jar

### Punto de entrada. Comandos que se van a ejecutar al iniciar la imagen
ENTRYPOINT ["java", "-jar", "/config-server.jar"] 

### Crear la imagen
# $docker build -t config-server:v1 .

### Consultar las imagenes creadas
# $docker image

### Crear Network Docker
# $docker network create curso-ms

### Correr la imagen [puerto_equipo:puerto_imagen name network imagen:versión]
# $docker run -p 8888:8888 --name config-server --network curso-ms config-server:v1

### Listar los contenedores
# $docker container ls

### Ayuda lista de comandos
# $docker --help
