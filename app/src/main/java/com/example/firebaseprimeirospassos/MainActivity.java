package com.example.firebaseprimeirospassos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    //Objetos para manipulação do banco de dados
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    //Objetos
    private EditText editTextNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.edit_text_nome);

        conectarBanco();
    }

    private void conectarBanco(){
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void salvarDado(View v){
        databaseReference.child("Dicionario")
                        .child(UUID.
                        randomUUID().
                        toString()).
                        child("Valor").
                        setValue(editTextNome.getText().toString());
    }

    public void apagarDado(View v){
        databaseReference.child("Dicionario").child("Valor").removeValue();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_principal, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.item_leitura){
            Intent intent = new Intent(this, LeituraActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
