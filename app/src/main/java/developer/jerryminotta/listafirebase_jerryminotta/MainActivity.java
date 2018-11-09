package developer.jerryminotta.listafirebase_jerryminotta;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    EditText et_correo, et_contrasena;
    Button btn_iniciarsesion, btn_registrarse;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        firebaseDatabase= FirebaseDatabase.getInstance();
        reference= firebaseDatabase.getReference().child("usuarios");

        et_correo = findViewById(R.id.et_correoLogin);
        et_contrasena= findViewById(R.id.et_contraLogin);
        btn_iniciarsesion = findViewById(R.id.btn_iniciarSesionLogin);
        btn_registrarse= findViewById(R.id.btn_registrarseLogin);



        btn_iniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String correo = et_correo.getText().toString();
                String contrasena= et_contrasena.getText().toString();

                auth.signInWithEmailAndPassword(correo, contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent= new Intent(MainActivity.this, HomeListas.class);
                            startActivity(intent);
                            finish();

                        }else{
                            Toast.makeText(MainActivity.this, "Correo o contrasena no validos",  Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });


        /*
        btn_iniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data: dataSnapshot.getChildren()){
                            Usuario s= data.getValue(Usuario.class);
                            Toast.makeText(MainActivity.this, s.getNombre(), Toast.LENGTH_SHORT).show();

                            if(s.getCorreo().equals(et_correo.getText().toString()) || s.getPass().equals(et_contrasena.getText().toString())){
                                Intent intent= new Intent(MainActivity.this, HomeListas.class);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

*/

        btn_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, Registro.class);
                startActivity(intent);
            }
        });


    }
}
