package com.example.proyectofinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

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

    //*****************


    private  void listarCoros(String respuesta){
        final ArrayList<CorosAle> listar = new ArrayList<CorosAle>();
        try{
            JSONArray jsonArreglo = new JSONArray(respuesta);
            for (int i=0; i<jsonArreglo.length(); i++){
                CorosAle a = new CorosAle();
                a.setId(jsonArreglo.getJSONObject(i).getInt("id_cale"));
                a.setTitulo(jsonArreglo.getJSONObject(i).getString("titulo"));
                a.setAutor(jsonArreglo.getJSONObject(i).getString("autor"));
                a.setLetra(jsonArreglo.getJSONObject(i).getString("letra"));

                listar.add(a);

            }

            ArrayAdapter<CorosAle> a = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, listar);
            lvdatoscal.setAdapter(a);

            lvdatoscal.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    CorosAle a = listar.get(position);
                    String url = "https://appmovilgamez.000webhostapp.com/eliminarale.php?id_cale="+a.getId();


                    return true;
                }
            });


        }catch(Exception el){
            el.printStackTrace();
        }


    }

    //***************
}
