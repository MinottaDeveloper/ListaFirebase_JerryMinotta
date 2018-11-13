package developer.jerryminotta.listafirebase_jerryminotta;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeListas extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseAuth auth;
    TextView tv_bienvenido;

    String idLocal;

    Usuario usuario;

    FirebaseUser firebaseUser;

    ImageButton btn_logOut, btn_agregarLista;
    EditText et_nombreLista;


    TextView output;

    ArrayList<Lista> listas;
    RecyclerView recyclerView;
    ListaAdapter listaAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_listas);

        output = findViewById(R.id.output);

        //mainList= findViewById(R.id.main_list);

        firebaseDatabase= FirebaseDatabase.getInstance();

        auth= FirebaseAuth.getInstance();

        firebaseUser = auth.getCurrentUser();

        btn_logOut= findViewById(R.id.btn_logOut);

        btn_agregarLista= findViewById(R.id.btn_anadirLista);

        tv_bienvenido= findViewById(R.id.tv_bienvenido);

        et_nombreLista= findViewById(R.id.et_nombreLista);



        listas= new ArrayList<>();

        recyclerView= findViewById(R.id.rv_listaListas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));



        //___________________________________

        if(firebaseUser!=null) {
           // Toast.makeText(HomeListas.this, "" + auth.getCurrentUser().getUid(), Toast.LENGTH_LONG).show();
             idLocal = firebaseUser.getUid();


            reference = firebaseDatabase.getReference().child("usuarios").child(idLocal);


            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    usuario = dataSnapshot.getValue(Usuario.class);

                  tv_bienvenido.setText("Bienvenido "+ usuario.getNombre().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

                    //-----------------------------------------------agrega listas iniciales


            firebaseDatabase.getReference().child("usuarios").child(idLocal).child("listas").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    output.setText("");
                    listas.clear();

                    for(DataSnapshot lista : dataSnapshot.getChildren()){
                        Lista l = lista.getValue(Lista.class);


                        listas.add(new Lista(l.getTitulo()));
                        //listaAdapter.setListas(listas);
                        listaAdapter.setListas(listas);
                        output.append(l.getTitulo()+"\n");
                        //  output.setText("");
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }else{
            Intent intent= new Intent(HomeListas.this, MainActivity.class);
            startActivity(intent);
        }


        //-----------------------------------------------------------------------------------------------
        //listas.add( new Lista("Tareas y vainas raras"));

        listaAdapter = new ListaAdapter(listas);
        recyclerView.setAdapter(listaAdapter);

        //------------------------------------------------------------------------------------------------

        btn_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();

                Intent intent= new Intent(HomeListas.this, MainActivity.class);
                startActivity(intent);
               // listas.clear();
            }
        });


        btn_agregarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tituloLista = et_nombreLista.getText().toString();

                if(auth!=null){
                    Lista lista = new Lista(tituloLista);
                    reference.child("listas").child(tituloLista).setValue(lista);
                }
            }
        });


            }
}


