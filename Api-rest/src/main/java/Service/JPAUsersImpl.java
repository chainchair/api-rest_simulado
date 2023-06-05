package Service;

import model.User; //importa la clase Users desde el paquete model

import java.util.ArrayList;  
import java.util.List;


public class JPAUsersImpl implements JPAUsers {
    private static List<User> users = new ArrayList<User>(); //array list donde se guardaran los objetos de tipo usuario(tiene un tamaño variable)
    public static User user; // creación de un objeto de tipo usuario 

    /*valida que el numero sea un numero valido
      que cumpla con el requisito de comenzar con un numero entre 300 y 399(inclusive) que seria el operador
      y que su parte final contenga 7 numeros, todo esta evaluado entre los valore de 5.000.000 y 9.999.999(aunque los operadores usen diferentes rangos de numeros y unos usen casi todos los disponibles en el rango de 7 digitos tambien hay otros que usan mucho menos, asi que elegui un rango arbitrario) 
    */
    public boolean validarCorreo(String correo){
        String[] dominios={"gmail.com","outlook.com","yahoo.com","eafit.edu.co"};
        if(correo.contains("@")){
            for(String dominio:dominios){
                if(correo.contains(dominio)){
                    return true;
                }
            }
            
        }
    return false;
    }
    public boolean validarnumero(String numero){
        String[] numerosInt=numero.split(" ");
        if (Integer.parseInt(numerosInt[0])>=300 && Integer.parseInt(numerosInt[0])<=399){
            System.out.println("operador evaluado correctamente\n");
            if(Integer.parseInt(numerosInt[1])>=5000000 && Integer.parseInt(numerosInt[1])<=9999999){
                System.out.println("numero evaluado correctamente\n");
                return true;
            }
        }
        
    return false;
    }
    @Override
    // obtiene los datos ingresados, los guarda en un nuevo objeto de tipo usuario y los envia a la lista de usuarios(users) 
    public void create(String body) { 

        String[] data = body.split(","); // divide el string que recibe(body) y lo guarda en un arreglo
        int id=users.size(); // obtiene la cantidad de usuarios y lo guarda en id
        data[0]=data[0].toLowerCase(); //convierte el nombre en minusculas
        
        //si validar numero y  validar correo son verdaderos crear usuario
        //if(validarnumero(data[1])){
            user = new User(id+1, data[0], data[1], data[2]); // crea un nuevo usuario, asigna los datos del array accediendo a ellos segun su posicion data[i]
            String response = "Se ha creado el usuario con exito y sus datos son:\n" + "Id:" + user.getId() + "|" + " Nombres: " + user.getNames() + "|" +
                    "Email: " + user.getEmail() + "|" + "Telefono: " + user.getPhone(); //respuesta del servidor
            id = users.size()+ 1; //incrementa el valor de id
            user.setId(id);// asigna el id aumentado en 1 a usuario
            users.add(user);//guardamos el objeto tipo usuario al array list
            System.out.println(response); //imprimimos la respuesta del servidor
        /*}
        else{
            System.out.println("no se pudo crear el usuario");
        }*/
        
    }


    @Override
    //metodo para leer la lista de usuarios
    public List<User> readAll() {
        printUsers(users);
        return users;
    }

    @Override
    //metodo para cambiar todos los datos de un usuario recibiendo su numero de identificación(id)
    public void updateById(String body, int id) throws IndexOutOfBoundsException {

        for (User usuario : users) { // for each, usuario es la variable y users es la lista de usuarios sobre la que se itera 
            if (usuario.getId() == id) { // recorre la lista hasta encontrar un usuario con id igual a la ingresada
                String[] data = body.split(","); //obtiene los nuevos datos ingresados, los divide y los guarda en el arreglo data
                int cantidadDeDatos= data.length; //obtiene la cantidad de datos ingresada para saber si se ingresaron mas o menos de los requeridos
                if(cantidadDeDatos<3){ // menos datos de los necesarios
                    System.out.println("cantidad de datos menor a la requerida");
                }
                else if(cantidadDeDatos>3){ //mas datos de los necesarios para el sistema
                    System.out.println("mas datos de los necesarios");
                }
                else{
                user.setNames(data[0]);
                user.setEmail(data[1]);
                user.setPhone(data[2]);
                users.set(id, user);
                System.out.println("Se ha actualizado el usuario con exito y sus datos son:\n" + "Id:" + user.getId() + ", " + " Nombres: " + user.getNames() + ", " +"Email: " + user.getEmail() + ", " + "Teléfono: " + user.getPhone());
                }break;
            }
    }
    }
    @Override
    public void deleteById(int id) throws IndexOutOfBoundsException { //borrar usuario obteniendo su numero de identificacion(id)
        boolean encontrado = false;
        for (User usuario : users) { // for each, usuario es la variable y users es la lista de usuarios sobre la que se itera 
        //users.removeIf(p -> p.getId() == id); 
        if (usuario.getId() == id) { //si usuario es encontrado
        encontrado = true; //encontrado verdadero
        users.remove(usuario); //eliminar usuario

        break; //romper ciclo
        }
        }//end for
        if(encontrado ==false) //si no se encontro el usuario
    {
        System.out.println("No se ha encontrado el usuario con el id: " + id); // mensaje negativo
    };
        System.out.println("Se ha eliminado el usuario con Id:" + id); //mensaje afirmativo
    }

    @Override
    public void findAll() {

    }

    @Override
    public void findById(int id) { //encontrar usuario segun su numero de identificacion(id)
        for (User usuario : users) {  // for each, usuario es la variable y users es la lista de usuarios sobre la que se itera
            if (usuario.getId() == id) {  //si usuario es encontrado
                System.out.println("Se ha encontrado el usuario con Id:" + id); //mensaje afirmativo se encontro el usuario
                System.out.println(usuario.toString()); //imprime los datos del usuario
            }//end if
        }//end for
    }
    // recorrer e imprimir la lista de usuarios
    static void printUsers(List<User> users) { 
        System.out.println("Lista de usuarios:\n");
        System.out.println("|id|nombre|    correo    | telefono |");
        for (int i = 0; i < users.size(); i++) {
            System.out.println("\n|" + users.get(i).getId() + " |" + users.get(i).getNames() +"|"+users.get(i).getEmail() + "|" + users.get(i).getPhone() + "|");
            //System.out.println("");
        }
    }
}