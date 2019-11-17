package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class CorosAleActivity extends AppCompatActivity {
    private EditText ettitulocal, etautorcal, etletracal;
    private Button btnRegistrarcal;
    private ListView lvdatoscal;
    private AsyncHttpClient clientecal = new AsyncHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coros_ale);

        ettitulocal = findViewById(R.id.ettituloal);
        etautorcal = findViewById(R.id.etautoral);
        etletracal = findViewById(R.id.etletraal);

        btnRegistrarcal = findViewById(R.id.btnRegistraral);

        lvdatoscal = findViewById(R.id.lvDatosale);

        clientecal = new AsyncHttpClient();

        almacenarCoros();

        obtenerCoros();
    }

    private void almacenarCoros() {
        btnRegistrarcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ettitulocal.getText().toString().length()== 0 )  {
                    ettitulocal.setError("Campo Obligatorio");
                }else if (etautorcal.getText().toString().length()== 0){
                    etautorcal.setError("Campo Obligatorio");
                }else  if (etletracal.getText().toString().length()== 0){
                    etletracal.setError("Campo Obligatorio");
                }else{
                    CorosAle a = new CorosAle();
                    a.setTitulo(ettitulocal.getText().toString().replaceAll(" ", "%20"));
                    a.setAutor(etautorcal.getText().toString().replaceAll(" ", "%20"));
                    a.setLetra(etletracal.getText().toString().replaceAll(" ", "%20"));

                    agregarCoros(a);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    obtenerCoros();
                }
            }
        });
    }


    //*****************

    private  void agregarCoros(CorosAle a){
        String url = "https://proyectofinalsis22.000webhostapp.com/agregarale.php?";
        String parametros = "titulo="+a.getTitulo()+"&autor="+a.getAutor()+"&letra="+a.getLetra();
        clientecal.post(url + parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200){
                    Toast.makeText(CorosAleActivity.this, "Coro agregada correctamente", Toast.LENGTH_SHORT).show();
                    ettitulocal.setText("");
                    etautorcal.setText("");
                    etletracal.setText("");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    //*******************

    private void obtenerCoros(){
        String url = "https://proyectofinalsis22.000webhostapp.com/obtenerCoroAle.php";
        clientecal.post(url, new AsyncHttpResponseHandler() {

        });
    }
}
