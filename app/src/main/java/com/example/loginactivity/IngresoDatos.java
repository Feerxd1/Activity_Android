package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IngresoDatos extends AppCompatActivity {

    public void Atras(View view) {
        Intent intent = new Intent(this, Bienvenido.class);
        startActivity(intent);
    }

    EditText nombre;
    EditText apellido;
    EditText edad;
    Button guardar;
    ListView lista;
    List<String> listas = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ingreso_datos);

        nombre = (EditText) findViewById(R.id.name1);
        apellido = (EditText) findViewById(R.id.lastname1);
        edad = (EditText) findViewById(R.id.age);
        guardar = (Button) findViewById(R.id.saveit);
        lista = (ListView) findViewById(R.id.lista);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listas);

        lista.setAdapter(adapter);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Verificar que los campos no esten vacios



                if (nombre.getText().toString().isEmpty()) {
                    nombre.setError("Ingrese un nombre");
                } else if (apellido.getText().toString().isEmpty()) {
                    apellido.setError("Ingrese un apellido");
                } else if (edad.getText().toString().isEmpty()) {
                    edad.setError("Ingrese una edad");
                }
                //Si los campos no estan vacios, se guardan los datos
                else {
                    listas.add(nombre.getText().toString() + " " + apellido.getText().toString() + " " + edad.getText().toString());
                    adapter.notifyDataSetChanged();
                    nombre.setText("");
                    apellido.setText("");
                    edad.setText("");

                    for (int i = 0; i < listas.size(); i++) {
                        for (int j = 0; j < listas.size(); j++) {
                            if (listas.get(i).equals(listas.get(j)) && i != j) {
                                listas.remove(j);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
            }
        });
    }
}

