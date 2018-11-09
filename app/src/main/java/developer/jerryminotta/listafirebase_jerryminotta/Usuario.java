package developer.jerryminotta.listafirebase_jerryminotta;

public class Usuario {

    private String uid, nombre, correo, pass, listas;

    public Usuario(){

    }

    public Usuario(String uid, String nombre, String correo, String pass, String listas) {
        this.uid = uid;
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
        this.listas = listas;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getListas() {
        return listas;
    }

    public void setListas(String listas) {
        this.listas = listas;
    }
}
