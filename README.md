<h1 align="center"> simulacion de servidor en java </h1>

![image2](pics/API-Rest.png)

## índice
* [main](#main)
* [service](#service)
* [controller](#controller)
* [model](#model)

## introducción
proyecto desarrollado como trabajo final de la clase leguajes de programación del pregrado en ingenieria de sistemas. Api-rest simula un servidor donde se le pueden hacer peticiones CRUD para trabajar con una base de datos, todo en un entorno local.

## funcionalidades del proyecto
* ### leer usuarios en la base de datos 
<ul><img src="gif/verUsuarios2.gif" alt="programa ejecutando la primera función" width=800></ul>

* ### buscar usuario con id 
<ul><img src="gif/función2.gif" alt="programa ejecutando la segunda función" width=800></ul>

* ### crear usuario 
<ul><img src="gif/función3.gif" alt="programa ejecutando la tercera función" width=800></ul>

* ### editar información de usuario con id 
<ul><img src="gif/función4.gif" alt="programa ejecutando la cuarta función" width=800></ul>

* ### eliminar un usuario 
<ul><img src="gif/función5.gif" alt="programa ejecutando la quinta función" width=800></ul>

* ### detener ejecución 
<ul><img src="gif/función6.gif" alt="programa ejecutando la sexta función" width=800></ul>

## main

## service
<ul><h3> JPAUsers </h3></ul>

<ul>
  Interfaz que pertenece al paquete service y contiene los métodos abtractos(CRUD) que se implementan en la clase JPAUsersImpl
</ul>
<ul><h3> JPAUsersImpl </h3></ul>

* ### la clase JPAUsersImpl pertenece al paquete Service e implementa los metodos CRUD del sistema
  + #### create
  	es un metodo de tipo void que obtiene los datos ingresados, los divide, los guarda en un nuevo objeto de tipo usuario y los agrega a la lista de usaurios
  + #### UpdateById 
  	es un metodo de tipo void que recibe un numero de identificacion(Id) el cual va a buscar en la lista de usuarios hasta encontrar un usuario con id igual a el ingresado, si lo encuentra pide mas datos, para poder reemplazar los datos "viejos" por los que recibirá, todo separado por comas 
			
			Ejemplo: juan,juanito@gmail.com,3024042021
	tambien valida si se ingresaron mas o menos datos de los necesarios, si se ingresaron una cantidad erronea de datos (la correcta es 3) no se hace el cambio y se imprime un mensaje de error, si se ingresó la cantidad correcta de datos se hace el cambio y se imprimen los nuevos datos del usuario en formato de tabla, en caso de no encontrarse el usuario no se hace nada.
  + #### deleteById 
  	es un metodo de tipo void que recibe un numero de identificacion(Id) el cual va a buscar en la lista de usuarios hasta encontrar un usuario con id igual a el ingresado, si lo encuentra entonces la variable booleana "encontrado" que al inicio es falsa se vuelve verdadera y se elimina el usuario de la lista de usuarios, si no encontramos en usuario, o lo que es lo mismo, al finalizar el metodo la variable "encontrado" sigue siendo falsa como al inicio se lanza un mensaje de error "No se ha encontrado el usuario con id "+ la id del usuario
  + #### findById 
	tambien es void y recibe un Id, el cual usa para encontrar un usaurio en la lista de usuarios y luego imprimirlo
  + #### printUsers 
	imprime todos los usuarios en la lista de usuarios y les da un formato de tipo tabla
	
	| nombre | email        | numero    |
	|--------|--------------|-----------|
	| pepe   | p@gmail.com  | 3024566767|
	| sofia  | sof@gmail.com| 3051234509|

  
## controller
<ul><h3> server </h3></ul>
<ul>
la clase server pertenece al paquete controller y es la que simula el funcionamiento del servidor, tiene en sus atributos de tipo string los siguientes: method, url, versionprotocol, headers, body y de tipo int tiene el Id. sus metodos en general llaman a una función de la clase JPAimpl y dan una respuesta del servidor, los metodos de la clase son:
</ul> 

* #### getUsersById
	obtiene e imprime todos los usuarios ademas de la respuesta del servidor 
* #### getUsersById
	obtiene e imprime un solo usuario(si es encontrado) y la respuesta del servidor
* #### PostUser
	crea un nuevo usuario e imprime la respuesta del servidor
* #### DeleteById
	eliminar usuario segun su numero de identificacion e imprime la respuesta del servidor
* #### PutUserById
	cambia los datos de un usuario si el numero recibido coincide con su numero de identificacion e imprime la respuesta del servidor

## customer
<ul><h3> ClienteHTTP</h3></ul>
<ul> pertenece al paquete customer y contiene cuatro atributos tipo String: "url", "headers","method","body"</ul>

## model
<ul><h3> User</h3></ul>  
<ul>  la clase usuario pertenece al paquete model y sirve para manejar los datos de los usuarios que se ingresan al sistema, dentro de ella se encuentran los atributos, metodos y el constructor de la clase.
los metodos se constituyen casi completamente de getters y setters de los atributos de la clase, ademas de un toString que sirve para mostrar la representacion en string del objeto. </ul>
