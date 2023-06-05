package controller; //paquete al que pertenece el archivo


//importa archivos que seran usados
import Service.JPAUsers;
import Service.JPAUsersImpl;
import model.User;

//importa librerias
import java.util.ArrayList;
import java.util.List;

// clase que simulara el funcionamieto del servidor 
public class Server {
    //atributos de la clase
    private String method;
    private String url = "http://localhost:8080/api/users";
    private final String versionProtocol = "HTTP/1.1";
    private String headers;

    private String body;
    private int id;//para consultas por Id

    @Autowired
    private JPAUsers user;//principio de Inversión de Dependencias (IoD)

    //constructor de la clase
    public Server(String method, String url, String headers, String body) {
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.body = body;
        this.user = new JPAUsersImpl();//una referencia q se ha creado en el constructor de la clase Server JPAUsersImpl
        


        //validamos que tipo de Método nos llegó al servidor
        switch (method) {
            case "GET":
                if (url.equals("http://localhost:8080/api/users")) {
                    GetUsers();
                }
                System.out.println("code: 200");
                break;
            case "POST":
                if (url.equals("http://localhost:8080/api/users")) {
                    PostUser(body);
                }
                System.out.println("code: 201");
                break;


            default:
                System.out.println("Método no soportado");
                break;
        }


    }//fin si

    //constructor, soporta instrucciones con diferenciacion por id
    public Server(String method, String url, String headers, String body, int id) {
        this.method = method;
        this.url = url;
        this.headers = headers;
        this.body = body;
        this.id = id;
        this.user = new JPAUsersImpl();//una referencia q se ha creado en el constructor de la clase Server JPAUsersImpl

        switch (method) {
            case "PUT":
                if (url.equals("http://localhost:8080/api/users")) {
                    PutUserById(body, id);
                }
                break;
            case "DELETE":
                if (url.equals("http://localhost:8080/api/users")) {
                    DeleteUserById(id);
                }
                break;
            case "GET":
                if (url.equals("http://localhost:8080/api/users")) {
                    GetUserById(id);
                }
                break;
            default:
                System.out.println("Método no soportado");
                break;
        }


    }//end constructor

    //obtiene e imprime todos los usuarios
    String GetUsers() {
        user.readAll();
        String response = versionProtocol + " 200 Ok\r\n" +
                "Content-Type: application/json\r\n" +
                "Content-Length: " + body.length() + "\r\n" +
                "\r\n" +
                body;
        return response;

    }
    //obtiene e imprime un solo usuario(si es encontrado)
    String GetUserById(int id) {
        String response = versionProtocol + " 200 OK\n" +
                "Content-Type: application/json\r\n" +
                "Content-Length: " + id + "\r\n";
        user.findById(id);
        return response;
    }
    
    //crea un nuevo usuario
    public String PostUser(@RequestBody String body) {
        user.create(body);//acá estamos haciendo la inyección de dependencias

        String response = versionProtocol + " 201 Created\r\n" +
                "Content-Type: application/json\r\n" +
                "Content-Length: " + body.length() + "\r\n" +
                "\r\n" +
                body;
        return response;
    }

    //eliminar usuario segun su numero de identificacion
    private void DeleteUserById(int i) {
        user.deleteById(id);
    }
    //cambia los datos de un usuario segun su numero de identificacion
    private void PutUserById(@RequestBody String body, @RequestBody int i) {
        user.updateById(body, id);
    }


    @Override
    //metodo para mostrar la representacion en string del objeto, se sobreescribe para que tenga un sentido, si no se sobre escribiera obtendriamos el nombre de la clase, sedpues un @ y luego el hash del objeto
    public String toString() {
        return "Server{" +
                "url='" + url + '\'' +
                ", headers='" + headers + '\'' +
                ", method='" + method + '\'' +
                ",\n body='" + body + '\'' +

                '}';

    }


}