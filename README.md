# Consulta de pico y placa


El presente proyecto permite realizar la consulta por número de placa y comprobar si un vehículo puede circular en una fecha o día determinado; Por lo que la aplicación permite ingresar la placa y la fecha actual con la hora.

**Backend**

**Prerrequisitos**

Instalar:
- Docker: Para ejecutar el contenedor MySQL.
- Java Development Kit (JDK 17): Para compilar y ejecutar la aplicación Spring Boot.
- Maven: Para gestionar las dependencias del proyecto.

**Contenedor MySQL**

Abrir la terminal, descargar y ejecutar el contenedor MySQL usando el comando:
  - docker run -d --name **nombre_contenedor_mysql** -p **3306:3306** -e MYSQL_ROOT_PASSWORD=**contraseña** -e MYSQL_DATABASE=**nombre_base_datos** mysql:latest
  
**Configuracion del proyecto**

- Clonar el repositorio de este proyecto y abrirlo en el IDE de tu preferencia
- Dentro del archivo application.properties, asegúrate de que los datos coincidan con los datos especificados en el contenedor:
     spring.datasource.url=jdbc:mysql://localhost:**3306**/**nombre_base_datos**
     spring.datasource.username=**root**
     spring.datasource.password=**contraseña**
   
**Compilación y ejecución**
- Abre el terminal y dirigirse al directorio raíz del proyecto. Luego ejecuta el siguiente comando para compilar el proyecto:
    - mvn clean install
- Una vez compilado el proyecto sin ningún error, inicia la aplicación Spring Boot ejecutando el siguiente comando:
    - mvn spring-boot:run

**Frontend**

**Prerrequisitos**

Instala los siguientes componentes:

- Node.js: Para ejecutar la aplicación Angular.
- Angular CLI:Para gestionar y construir el proyecto Angular.

**Instala las dependencias del proyecto**

Ejecuta el siguiente comando en la raíz del directorio del proyecto denominado check-pico-placa:
  - npm install

**Ejecutar la aplicación**

Ahora, ejecuta el siguiente comando:
  - ng serve

Esto iniciará el servidor de desarrollo y la aplicación estará disponible en **http://localhost:4200.**
