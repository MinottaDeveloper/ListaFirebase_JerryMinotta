package developer.jerryminotta.listafirebase_jerryminotta;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registro extends AppCompatActivity {

    EditText et_nombre, et_correo, et_contrasena;
    Button btn_terminar;
    TextView tv_iniciarsesion;

    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    boolean loading= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        auth = FirebaseAuth.getInstance();

        firebaseDatabase= FirebaseDatabase.getInstance();
        reference= firebaseDatabase.getReference().child("usuarios");



        et_contrasena= findViewById(R.id.et_contraRegistro);
        et_correo= findViewById(R.id.et_correoRegistro);
        et_nombre= findViewById(R.id.et_nombreRegistro);

        btn_terminar = findViewById(R.id.btn_terminarRegistro);

        tv_iniciarsesion= findViewById(R.id.tv_yatengocuenta);

        tv_iniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registro.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String nombre= et_nombre.getText().toString();
                final String correo= et_correo.getText().toString();
                final String contrasena= et_contrasena.getText().toString();
                final String lista= "lista".toString();

                if(nombre.equals("") || correo.equals("") || contrasena.equals("")){
                    Toast.makeText(Registro.this, "Complete todos los campos", Toast.LENGTH_LONG).show();
                }

                if(!correo.contains("@")){
                    Toast.makeText(Registro.this, "Correo no valido", Toast.LENGTH_SHORT).show();
                }

                auth.createUserWithEmailAndPassword(correo, contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Registro.this, "Registro Exitoso!", Toast.LENGTH_SHORT).show();

                            Usuario usuario = new Usuario(auth.getCurrentUser().getUid(), nombre, correo, contrasena, lista);
                            reference.child(auth.getCurrentUser().getUid()).setValue(usuario);

                           auth.signInWithEmailAndPassword(correo, contrasena);



                            Intent intent = new Intent(Registro.this, HomeListas.class);
                            startActivity(intent);
                            finish();
                        }else{
                           Toast.makeText(Registro.this, ""+task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });


                Toast.makeText(Registro.this, "Cargando, por favor espere...", Toast.LENGTH_LONG).show();

                et_contrasena.setText("");
                et_correo.setText("");
                et_nombre.setText("");

            }
        });

    }
}
