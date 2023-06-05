
package Main;
import controller.Server;
import java.util.Scanner;

import model.User;
public class Main {
    

    //valida que la url esté soportada por el servidor
    public static boolean validarUrl(){
        
        Scanner scan=new Scanner(System.in); //creacion de scan, objeto de tipo scanner
        System.out.println("ingrese la url"); 
        String Datos=scan.nextLine(); // obtencion de datos por teclado, recibe toda la linea(hasta que se hace enter)
        String[] DatosArr=Datos.split(":"); //divide los datos
        String[] DatosFinales; // array que se usar para almacenar los datos de la ultima parte de la url("8080/api/users")
        //validamos que la cantidad de datos no sea mañor o menor de la requerida
        if (DatosArr.length<3 || DatosArr.length>3){
            System.out.println("cantidad de datos invalida");
            System.out.println("intente denuevo");
            validarUrl();
        }
        //con el array DAtosFinales validamos que todo esté correcto
        else{ 
            DatosFinales=DatosArr[2].split("/"); //dividimos los datos que hay en DatosArr[2] y los guardamos en DatosFinales
            
            
            
            //usamos equals para comparar con cada parte de los datos obtenidos(protocolo,servidor, puerto y recurso), equals regresa true si es igual a algo. por eso para comparar si es diferente le damos un false.
            if ("http".equals(DatosArr[0]) ==false){
                System.out.println("protocolo no valido");
                return false;
            }
            else if("//localhost".equals(DatosArr[1])==false){
                System.out.println("servidor no valido");
                return false;
            }
            else if("8080".equals(DatosFinales[0])==false){
                System.out.println("puerto no valido");
                return false;
            }
            else if("api".equals(DatosFinales[1])==false || "users".equals(DatosFinales[2])==false){
                System.out.println("recurso no valido");
                return false;
            }                               
        }
        //respuesta de validacion correcta
        System.out.println("Url validada correctamente");
        //al ser booleando regresa true si está bien validado, en caso contrario regresa false
        return true;
    }
    //desplega el menu del sistema, se llama en cada iteracion
    public static void menu(){
        System.out.print("\033[H\033[2J");
        System.out.println("\nbienvenido al sistema de gestion de la base de datos");
        System.out.println("sistema creado por: samuel david serpa zapa, con asistencia del profesor alexander Narvaez");
        System.out.println("desplegando opciones disponibles\n");
        System.out.println("1. Leer usuarios existentes(GET)");
        System.out.println("2. Encontrar un usuario(GET)");
        System.out.println("3. Crear un usuario(POST)");
        System.out.println("4. Editar informacion de un usuario existente(PUT)");
        System.out.println("5. Eliminar un usuario(DELETE)");
        System.out.println("6. Salir");
    }
    
    public static boolean elegir(){
        Scanner scan =new Scanner(System.in); //creacion del objeto scan, tipo scanner
        int eleccion=Integer.parseInt(scan.nextLine()); //obtiene una linea desde el teclado, la convierte en entero y la guarda en eleccion
        String datosUsuario;
        int idUsuario;
        if(validarUrl()==false && eleccion!=6){
                    System.out.println("Url no valida");
                }
        else{
            //switch que procesa la eleccion del usuario y ejecuta peticiones al servidor 
            switch(eleccion){
            case 1:
                System.out.println("ha elegido la opcion 1");
                Server request = new Server("GET", "http://localhost:8080/api/users", "Content-Type: application/json", "");
                System.out.println(request);//respuesta del servidor
                break;
            case 2:
                System.out.println("ha elegido la opcion dos");
                System.out.println("ingrese el id del usuario");
                idUsuario=Integer.parseInt(scan.nextLine());
                request = new Server("GET", "http://localhost:8080/api/users", "Content-Type: application/json", "",idUsuario);
                System.out.println(request);//respuesta del servidor
                break;
            case 3:
                System.out.println("ha elegido la opcion tres");
                System.out.println("al ingresar los datos recuerde separarlos con ','");
                datosUsuario=scan.nextLine();
                request = new Server("POST", "http://localhost:8080/api/users", "Content-Type: application/json", datosUsuario);
                System.out.println(request);
                break;
            case 4:
                System.out.println("ha elegido la opcion cuatro");
                System.out.println("ingrese el id del usuario");
                idUsuario=Integer.parseInt(scan.nextLine());
                System.out.println("al ingresar los datos recuerde separarlos con ','");
                datosUsuario=scan.nextLine(); 
                
                request = new Server("PUT", "http://localhost:8080/api/users", "Content-Type: application/json",datosUsuario,idUsuario);
                System.out.println(request);//respuesta del servidor
                break;
            case 5:
                System.out.println("ha elegido la opcion cinco");
                System.out.println("ingrese el id del usuario");
                idUsuario=Integer.parseInt(scan.nextLine());
                request = new Server("DELETE", "http://localhost:8080/api/users", "Content-Type: application/json", "",idUsuario);
                System.out.println(request);//respuesta del servidor
                break;
                //caso para salir de la ejecicuon y cerrar el programa
            case 6:
                System.out.println("cerrando el programa");
                return false;
            default:
                System.out.println("entraste al default, revisa mejor la entrada");
              
            }   
        } return true;
    }
    public static void main(String[] args){
        boolean ejecucion=true; //booleano que determina si se ejecuta o no el programa
        
        Server request = new Server("POST", "http://localhost:8080/api/users", "Content-Type: application/json", "Yeshua,narva@gmail.com,322 5234567"); //peticion de post 

        System.out.println(request);//respuesta del servidor
        System.out.println("---------------------------------------------------");
        request = new Server("POST", "http://localhost:8080/api/users", "Content-Type: application/json", "Sophia,gug@gmail.com,302 6564761"); //peticion de post 
        System.out.println(request);//respuesta del servidor
        System.out.println("---------------------------------------------------");

        request = new Server("POST", "http://localhost:8080/api/users", "Content-Type: application/json", "Sarah,sarah@gmail.com,303 8300652"); //peticion de post 
        System.out.println(request);//respuesta del servidor
        System.out.println("---------------------------------------------------");

        //mientras ejecucion sea verdadero(true), desplegar el menu y elegir()
        while(ejecucion){
        menu();
        ejecucion= elegir();
        
        }
           
        
    }
}