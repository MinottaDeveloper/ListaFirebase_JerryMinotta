package developer.jerryminotta.listafirebase_jerryminotta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ItemListas extends AppCompatActivity {

    ArrayList<Item>items;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_listas);

        items= new ArrayList<>();
        recyclerView= findViewById(R.id.rv_listaItems);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        llenarItems();

        UserListAdapter userListAdapter = new UserListAdapter(items);
        recyclerView.setAdapter(userListAdapter);

    }

    private void llenarItems() {

        items.add(new Item("","", false));
    }
}
