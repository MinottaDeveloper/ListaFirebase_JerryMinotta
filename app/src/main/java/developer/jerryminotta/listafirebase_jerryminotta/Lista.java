package developer.jerryminotta.listafirebase_jerryminotta;

import java.util.ArrayList;

public class Lista {

    String titulo;
    ArrayList<Item> items;

    public Lista(){

    }

    public Lista(String titulo) {
        this.titulo = titulo;

        items = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
