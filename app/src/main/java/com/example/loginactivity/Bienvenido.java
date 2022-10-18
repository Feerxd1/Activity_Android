package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Bienvenido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_bienvenido);
    }

        public void calculadora (View view){
            Intent intent = new Intent(this, calc.class);
            startActivity(intent);
        }

        public void maps (View view){
            Intent intent = new Intent(this, mapa.class);
            startActivity(intent);
        }


        public void back (View view){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        public void datos (View view){
            Intent intent = new Intent(this, IngresoDatos.class);
            startActivity(intent);
        }
    }