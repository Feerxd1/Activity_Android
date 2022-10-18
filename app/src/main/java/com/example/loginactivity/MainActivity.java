package com.example.loginactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);



        final Button boton = (Button) findViewById(R.id.login);
        final EditText usuario = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final AlertDialog.Builder mensaje = new AlertDialog.Builder(MainActivity.this);

        final String[] user = {"usuario", "admin", "otro"};//Array de usuarios
        final String[] pass = {"123", "123", "123"};//Array de contraseñas


        //solicita al usuario permisos para acceder a la ubicación al iniciar la app

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }


        //solicita permiso para acceder a actividad fisica al iniciar la app

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 1);
        }

        //solicita permiso para usar linterna al iniciar la app

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (usuario.getText().toString().isEmpty()) {
                    mensaje.setMessage("Ingrese un usuario");
                    mensaje.setNegativeButton("OK", null);
                    mensaje.show();

                } else if (password.getText().toString().isEmpty()) {
                    mensaje.setMessage("Ingrese una contraseña");
                    mensaje.setNegativeButton("OK", null);
                    mensaje.show();

                } else {
                    for (int i = 0; i < user.length; i++) {
                        if (usuario.getText().toString().equals(user[i]) && password.getText().toString().equals(pass[i])) {
                            Intent intent = new Intent(MainActivity.this, Bienvenido.class);
                            startActivity(intent);
                        } else if (i == user.length - 1) {
                            mensaje.setMessage("Usuario o contraseña incorrectos");
                            mensaje.setNegativeButton("OK", null);
                            mensaje.show();
                        }
                         else if (usuario.getText().toString().equals("usuario")) {
                            Toast.makeText(MainActivity.this, "Bienvenido usuario", Toast.LENGTH_LONG).show();;
                        }
                         else if (usuario.getText().toString().equals("admin")) {
                            Toast.makeText(MainActivity.this, "Bienvenido admin", Toast.LENGTH_LONG).show();
                        }
                         else if (usuario.getText().toString().equals("otro")) {
                            Toast.makeText(MainActivity.this, "Bienvenido otro", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }

    public void facebook(View view) {
        Intent intent = new Intent(this, facebook.class);
        startActivity(intent);
    }

    public void instagram(View view) {
        Intent intent = new Intent(this, Instagram.class);
        startActivity(intent);
    }

    public void twitter(View view) {
        Intent intent = new Intent(this, twitter.class);
        startActivity(intent);
    }

    public void reddit(View view) {
        Intent intent = new Intent(this, reddit.class);
        startActivity(intent);
    }

    public void github(View view) {
        Intent intent = new Intent(this, github.class);
        startActivity(intent);
    }
}
