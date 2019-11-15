package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Metodo para que se integre el menú a la interfaz
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //Si toca una opción del menú iniciará la activity que le corresponde
        if (id == R.id.itemRegisAlabanzas) {
            Intent intent = new Intent(this, RegistrarAlabanzas.class);
            startActivity(intent);
            return true;
        }if (id == R.id.itemRegisCoros) {
            //Intent sirve para lanzar otra actividad
            Intent intent = new Intent(this, RegistrarCoros.class);
            startActivity(intent);
            //se retorna true porque el metodo espera un valor booleano
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Metodo que inicia la activity Coros para luego llamarse con el evento onClic de un botón
    public void coros (View view){
        Intent intent = new Intent(this, Coros.class);
        startActivity(intent);
    }

    //Metodo que inicia la activity Alabanzas para luego llamarse con el evento onClic de un botón
    public void alabanzas (View view){
        Intent intent = new Intent(this, Alabanzas.class);
        startActivity(intent);
    }
}
