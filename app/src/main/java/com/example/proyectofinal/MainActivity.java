package com.example.proyectofinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ettitulo = findViewById(R.id.ettitulo);
        etautor = findViewById(R.id.etautor);
        etletra = findViewById(R.id.etletra);

        btnRegistrar = findViewById(R.id.btnRegistrar);

        lvdatos = findViewById(R.id.lvDatosRa);

        cliente = new AsyncHttpClient();
        almacenarAlabanzas();
        obtenerAlabanzas();
    }

    //****MENÚ********

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //Acerca de..
        if (id == R.id.mAcerca) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    context);
            // Establecer el título
            alertDialogBuilder.setTitle("Acerca de");
            // Establecer mensaje de diálogo
            alertDialogBuilder
                    .setMessage("~ COSME JOSÉ \n" +
                            "\n ~ CARLOS ELÍAS \n" +
                            "\n ~ JONATHAN ALEXANDER \n" +
                            "\n ~ EDUARDO JOSUE \n")
                    .setCancelable(false)
                    .setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Si presiona que Aceptar se cerrara el mensaje de dialogo
                            dialog.cancel();
                        }
                    });
            // Crear mensaje AlertDialog
            AlertDialog alertDialog = alertDialogBuilder.create();
            // Mostrar alert
            alertDialog.show();
        }


        return super.onOptionsItemSelected(item);
    }

    //****MENÚ********


    private void almacenarAlabanzas() {
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ettitulo.getText().toString().length() == 0) {
                    ettitulo.setError("Campo Obligatorio");
                } else if (etautor.getText().toString().length() == 0) {
                    etautor.setError("Campo Obligatorio");
                } else if (etletra.getText().toString().length() == 0) {
                    etletra.setError("Campo Obligatorio");
                } else {
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


    private void agregarAlabanza(Alabanzas a) {
        String url = "https://proyectofinalsis22.000webhostapp.com/agregar.php?";
        String parametros = "titulo=" + a.getTitulo() + "&autor=" + a.getAutor() + "&letra=" + a.getLetra();
        cliente.post(url + parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
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


    private void obtenerAlabanzas() {
        String url = "https://proyectofinalsis22.000webhostapp.com/obtenerDatos.php";
        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    listarAlabanzas(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    private void listarAlabanzas(String respuesta) {
        final ArrayList<Alabanzas> lista = new ArrayList<Alabanzas>();
        try {
            JSONArray jsonArreglo = new JSONArray(respuesta);
            for (int i = 0; i < jsonArreglo.length(); i++) {
                Alabanzas a = new Alabanzas();
                a.setId(jsonArreglo.getJSONObject(i).getInt("id_a"));
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
                    String url = "https://proyectofinalsis22.000webhostapp.com/eliminar.php?id=" + a.getId();

                    cliente.post(url, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (statusCode == 200) {
                                Toast.makeText(MainActivity.this, "Alabanza liminada Correctamente", Toast.LENGTH_SHORT).show();
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


            lvdatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Alabanzas a = lista.get(position);
                    StringBuffer b = new StringBuffer();
                    b.append("ID: " + a.getId() + "\n");
                    b.append("TITULO: " + a.getTitulo() + "\n");
                    b.append("AUTOR: " + a.getTitulo() + "\n");
                    b.append("LETRA: " + a.getLetra() + "\n");

                    AlertDialog.Builder al = new AlertDialog.Builder(MainActivity.this);
                    al.setCancelable(true);
                    al.setTitle("Detalle");
                    al.setMessage(a.tostring());
                    al.show();
                }
            });
        } catch (Exception el) {
            el.printStackTrace();
        }


    }

    public void coros(View view) {
        Intent intent = new Intent(this, CorosAleActivity.class);
        startActivity(intent);
    }

    public void registro(View view) {
        Intent intent = new Intent(this, listar_registro.class);
        startActivity(intent);
    }

}
