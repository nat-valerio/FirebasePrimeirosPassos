package com.example.firebaseprimeirospassos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class LeituraActivity extends AppCompatActivity {

    //Objetos para manipulação do banco de dados
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private TextView textViewLeitura;
    private TextView textViewLt1;
    private TextView textViewLt2;
    private TextView textViewMedia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitura);

        textViewLeitura = findViewById(R.id.text_view_leitura);
        textViewLt1 = findViewById(R.id.text_view_lt);
        textViewLt2 = findViewById(R.id.text_view_lt2);
        textViewMedia = findViewById(R.id.text_view_media);

        textViewLeitura.setText("");
        textViewLeitura.setText("");
        textViewLt1.setText("");
        textViewMedia.setText("");

        conectarBanco();

        databaseReference.child("Dicionario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                textViewLeitura.setText(
                        dataSnapshot.child("Valor1").child("Nota").getValue().toString());

                textViewLt1.setText(
                        dataSnapshot.child("Valor2").child("Nota").getValue().toString());

                textViewLt2.setText(
                        dataSnapshot.child("Valor3").child("Nota").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void conectarBanco(){
        FirebaseApp.initializeApp(LeituraActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void mediaLeitura(View v){

        double valor1 = Double.parseDouble(textViewLeitura.getText().toString());
        double valor2 = Double.parseDouble(textViewLt1.getText().toString());
        double valor3 = Double.parseDouble(textViewLt2.getText().toString());

        Double resultado = (valor1 + valor2 + valor3)/3;

        textViewMedia.setText(resultado.toString());

    }
}
