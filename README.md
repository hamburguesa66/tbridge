# T-Bridge

API para mapear un modelo de tareas (issues, bugs, tasks) a una tarjeta de Trello.

## Instrucciones para ejecutar la aplicación.

Antes de comenzar, se requiere tener instalado.
* Maven (3.6.0 o superior).
* Java 8.

1. Clonar la rama 'master' del repositorio.

  ```
  git clone -b develop https://github.com/hamburguesa66/tbridge.git
  ```

2. Instalar las dependencias del proyecto.

  ```
  mvn install
  ```
  
3. Modificar el archivo `application.properties`, completnado la siguiente información:
* Clave de usuario para consultar la API.
* Token de usuario para consultar la API.
* ID del tablero de Trello.

4. Ejecutar la aplicación (debería estar disponible en el puerto 8080).

  ```
  mvn spring-boot:run
  ```

## Endpoints

1. POST "/"

> Permite crear una tarea y registrarla en Trello.
> Los parámetros disponibles son: 'type' (obligatorio, "issue|bug|task"), 'title', 'description' y 'category'.


> Ejemplo de una request para crear una tarea de tipo "Task":

 ```
{
	"type": "task",
	"title": "Clean the Rocket",
	"category": "Maintenance"
}
 ```
 