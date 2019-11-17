package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class registro_coro_ale extends AppCompatActivity {
    private ListView lvdatoscal;
    private AsyncHttpClient clientecal = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_coro_ale);

        lvdatoscal = findViewById(R.id.lvDatosale);
        clientecal = new AsyncHttpClient();
        obtenerCoros();
    }

    private void obtenerCoros(){
        String url = "https://appmovilgamez.000webhostapp.com/obtenerCoroAle.php";
        clientecal.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200){
                    listarCoros(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
