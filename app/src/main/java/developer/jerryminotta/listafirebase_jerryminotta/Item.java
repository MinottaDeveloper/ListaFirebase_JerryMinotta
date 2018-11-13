package developer.jerryminotta.listafirebase_jerryminotta;

public class Item {

    String nombre, descripcion;
    boolean completado;

    public Item(String nombre, String descripcion, boolean completado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.completado = completado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
}
