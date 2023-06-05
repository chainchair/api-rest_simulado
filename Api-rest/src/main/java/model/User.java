package model;

import java.util.concurrent.atomic.AtomicInteger;  //importación de integer atomico(integer sincronizado)


// la clase usuario sirve para el manejo de los datos de los usuarios que se ingresan al sistema, dentro de ella se encuentran los atributos, metodos y el constructor de la clase, todo lo necesario para que se le puedan ingresar datos a los objetos tipo usuario.
public class User { //clase usuario
    
    //atributos
    private static final AtomicInteger count = new AtomicInteger(0); //para usar como contador
    private   int id=0; // numero de identificación del usuario(es un numero unico para cada usuario)
    private String names; // nombre conel que se registra el usuario
    private String email; // correo electronico
    private String phone; // telefono, debe tener 10 numeros

    //constructor
    public User(int id,String names, String email, String phone) { 
        this.id = count.incrementAndGet(); //aumenta el valor de count(atomicInteger) y lo obtiene para luego asignarlo a id.
        this.setId(id++); //asigna el id
        this. names = names; //asigna el nombre
        this.email = email; //asigna el email
        this.phone = phone; // asigna el numero de telefono
    }

    //metodos get y set para cada atributo del usuario.
    public int getId() { 
        return id;
    }

    public void setId(int id) {
       this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    //metodo para mostrar la representacion en string del objeto, se sobreescribe para que tenga un sentido, si no se sobre escribiera obtendriamos el nombre de la clase, sedpues un @ y luego el hash del objeto
    public String toString() {
        return  "|"+id+"|"+names+"|"+email+"|"+phone+"|"+'\''+"------------------------------------------------------------";
    }
}