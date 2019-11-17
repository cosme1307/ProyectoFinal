package com.example.proyectofinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private EditText ettitulo, etautor, etletra;
    private Button btnRegistrar;
    private ListView lvdatos;
    private AsyncHttpClient cliente = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ettitulo = findViewById(R.id.ettitulo);
        etautor = findViewById(R.id.etautor);
        etletra = findViewById(R.id.etletra);

        btnRegistrar = findViewById(R.id.btnRegistrar);

        lvdatos = findViewById(R.id.lvDatosR);

        cliente = new AsyncHttpClient();
        almacenarAlabanzas();
        obtenerAlabanzas();
    }

    private void almacenarAlabanzas() {
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ettitulo.getText().toString().length()== 0 )  {
                    ettitulo.setError("Campo Obligatorio");
                }else if (etautor.getText().toString().length()== 0){
                    etautor.setError("Campo Obligatorio");
                }else  if (etletra.getText().toString().length()== 0){
                    etletra.setError("Campo Obligatorio");
                }else{
                    Alabanzas a = new Alabanzas();
                    a.setTitulo(ettitulo.getText().toString().replaceAll(" ", "%20"));
                    a.setAutor(etautor.getText().toString().replaceAll(" ", "%20"));
                    a.setLetra(etletra.getText().toString().replaceAll(" ", "%20"));

                    agregarAlabanza(a);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    obtenerAlabanzas();
                }
            }
        });
    }



    private  void agregarAlabanza(Alabanzas a){
        String url = "https://appmovilgamez.000webhostapp.com/agregar.php?";
        String parametros = "titulo="+a.getTitulo()+"&autor="+a.getAutor()+"&letra="+a.getLetra();
        cliente.post(url + parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200){
                    Toast.makeText(MainActivity.this, "Alabanza agregada correctamente", Toast.LENGTH_SHORT).show();
                    ettitulo.setText("");
                    etautor.setText("");
                    etletra.setText("");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    private void obtenerAlabanzas(){
        String url = "https://appmovilgamez.000webhostapp.com/obtenerDatos.php";
        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200){
                    listarAlabanzas(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


}
