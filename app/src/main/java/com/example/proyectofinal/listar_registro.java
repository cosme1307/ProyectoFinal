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

public class listar_registro extends AppCompatActivity {
    private ListView lvdatos;
    private AsyncHttpClient cliente = new AsyncHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_registro);

        lvdatos = findViewById(R.id.lvDatosRa);

        cliente = new AsyncHttpClient();
        obtenerAlabanzas();
    }

    private void obtenerAlabanzas(){
        String url = "https://proyectofinalsis22.000webhostapp.com/obtenerDatos.php";
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

    private  void listarAlabanzas(String respuesta){
        final ArrayList<Alabanzas> lista = new ArrayList<Alabanzas>();
        try{
            JSONArray jsonArreglo = new JSONArray(respuesta);
            for (int i=0; i<jsonArreglo.length(); i++){
                Alabanzas a = new Alabanzas();
                a.setId(jsonArreglo.getJSONObject(i).getInt("id"));
                a.setTitulo(jsonArreglo.getJSONObject(i).getString("titulo"));
                a.setAutor(jsonArreglo.getJSONObject(i).getString("autor"));
                a.setLetra(jsonArreglo.getJSONObject(i).getString("letra"));

                lista.add(a);

            }

            ArrayAdapter<Alabanzas> a = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, lista);
            lvdatos.setAdapter(a);

            lvdatos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    Alabanzas a = lista.get(position);
                    String url = "https://proyectofinalsis22.000webhostapp.com/eliminar.php?id_a="+a.getId();

                    cliente.post(url, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (statusCode == 200){
                                Toast.makeText(listar_registro.this, "Alabanza liminada Correctamente", Toast.LENGTH_SHORT).show();
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                obtenerAlabanzas();
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        }
                    });

                    return true;
                }
            });


        }catch(Exception el){
            el.printStackTrace();
        }


    }

}
