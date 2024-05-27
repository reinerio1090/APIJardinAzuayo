# APIJardinAzuayo

### funcionalidades

-   Listar todos las incidencias.
-   Guardar las incidencias.
-   Modificar las incidencias.
-   Buscar incidencias por identificador de usuario de soporte.
-   Buscar incidencias entre fechas
-   Buscar incidencias pendientes

## Pasos y requisitos de despliegue

-  PostgreSQL 11
-  Descargar y ejecución de sentencias SQL para creación de base e inserción de datos

   https://github.com/reinerio1090/APIJardinAzuayo/commit/33ce1ee2f13b7dec6cd181ceea16d1ca5db06697
  
- Colección de Postman con los endpoint para el consumo en servidor local

  [Incidencias J.A..postman_collection.json](https://github.com/reinerio1090/APIJardinAzuayo/files/15448759/Incidencias.J.A.postman_collection.json)

- Cambiar configuraciones de conexion a base de datos en el archivo ubicado en:

   src/main/resources/application.properties

      spring.datasource.url=jdbc:postgresql://localhost:5432/BDJA
      spring.datasource.username=postgres
      spring.datasource.password=Sistemas2024

- instalar las dependencias de Maven

      mvn clean install

- Ejecutar la Aplicación

      mvn spring-boot:run

    

