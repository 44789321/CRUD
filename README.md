#Pueba Telcel 

<p>
Dentro de la aplicación Telcel, este proyecto utiliza MongoDB para administrar usuarios y roles. Incluye entidades de usuario y roles, así como operaciones CRUD que se implementaron en Spring Boot para mejorar la gestión y la persistencia de datos. Los puntos de acceso RESTful se ofrecen para realizar tareas como crear, leer, actualizar y eliminar usuarios y roles, así como garantizar la integridad de los datos y la facilidad de acceso a MongoDB.

Lo que utilzamos para este proyecto:
 <li>Spring Boot</li>
 <li>Java 17 </li> 
 <li>MongoDB</li>
</p>

###Preubas Realizadas
•Verbo GET 
                - Poder consultar todos los usuarios.
                - Poder consultar un usuario mediante su ID.
               - Poder consultar un usuario mediante su NOMBRE.

•Verbo POST
                - Poder Insertar un usuario/role, la generación del ID debe ser automática.

•Verbo DELETE
                - Poder Eliminar un usuario/role.

•Verbo PUT
                - Poder Actualizar un usuario/role.

<p>
*Es importante mencionar que las pruebas realizadas se aplican tanto en user como en rol dando un total de 12 enpoints.*
</p>

### Comandos en acción para la realización de las pruebas
<p>
Para la realización de las pruebas fueron configuradas diferentes rutas de endpoint.

**Endpoints establecidos para Get Users: **

```1. http://localhost:8080/users```
```2. http://localhost:8080/users/{id}```
```3. http://localhost:8080/users/{firstName}/{lastName}```

*Estructura de ejemplo*:
```http://localhost:8080/users```
```http://localhost:8080/users/1```
```http://localhost:8080/users/Erick/Rosas```

**Endpoints establecidos para Get Rol: **
```4. http://localhost:8080/rol```
```5. http://localhost:8080/rol/{id}```
```6. http://localhost:8080/rol/name/{name}```

*Estructura de ejemplo*:
```http://localhost:8080/rol```
```http://localhost:8080/rol/1```
```http://localhost:8080/rol/name/Supervisor```

**Endpoints establecidos para Post Users/Rol: **
```7. http://localhost:8080/users```
```8. http://localhost:8080/rol```

**Endpoints establecidos para Put Users/Rol: **
```9. http://localhost:8080/users/{id}```
```10. http://localhost:8080/rol/{id}```

*Estructura de ejemplo*:
```http://localhost:8080/users/2```
```http://localhost:8080/rol/2```

**Endpoints establecidos para Delete Users/Rol: **
```11. http://localhost:8080/users/{id}```
```12. http://localhost:8080/rol/{id}```

*Estructura de ejemplo*:
```http://localhost:8080/users/2```
```http://localhost:8080/rol/2```
</p>

### Para Crear un nuevo usuario/rol:

Para crear a un nuevo usuario debemos seguir el siguiente formato que se proporciona como ejemplo, en este ejemplo podemos observar que podemos ingresar diferentes roles a un solo usuario, sin embargo, si queremos ingresar un solo rol a continuación se mostrara otro ejemplo: 
```json
{
  "firstName": "Denis",
  "lastName": "Gomez",
  "middleName": "Medina",
  "roles": [
    {"id": 1, "name": "Admin"},
    {"id": 2, "name": "Worker"},
    {"id": 3, "name": "Supervisor"},
    {"id": 4, "name": "Contador"}
  ]
}```

```json
{
  "firstName": "Denis",
  "lastName": "Gomez",
  "middleName": "Medina",
  "roles": [
    {"id": 1, "name": "Admin"}
  ]
}```
<p>
*Importante verificar los roles y los id del rol ya que sino coinciden o no existen lanzara un error hasta que coinda el id con su respectivo rol.*
</p>

Ejemplo para la creación del rol:
```json
{
    "name": "Economistas"
}
```
<p>
El id será genera automaticamente por lo que solo es necesario ingresar el nombre y el nuevo rol.
</p>

### Para la conexión de la Base de datos
<p>
Para la conexión con la base de datos debemos crear una base de datos llamada Telcel en el enlace predeterminado que nos otorga MongoDb el cual es  ***mongodb://localhost:27017* ** en dicha base de datos se realizara la creación de* **2*** colecciones, la primera colección será llamada ***Users*** y la segunda colección será llamada ***Rol***, dentro del repositorio podemos ***observar*** 2 archivos*** .json***, dichos archivos ***serán importados forzosamente en su respectiva colección*** como bien lo indica el nombre de cada uno.

La conexión es necesariamente en el puerto predeterminado ***mongodb://localhost:27017* ** para poder tener un correcto funcionamiento, de lo contrario deberan modificarse las propiedades, es decir, el archivo *application.properties*  debido a que la base de datos ya se encuentra configurada como podemos ver a continuación:
</p>

- spring.data.mongodb.uri=mongodb://localhost:27017/Telcel

<p>
La estructura debera quedar mas o menos de la siguiente forma:
|---Telcel
|----------Rol (importación de archivo Rol.json)
|----------Users (importación de archivo Users.json)
</p>


