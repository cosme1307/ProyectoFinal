package com.example.proyectofinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrarCoros extends AppCompatActivity implements View.OnClickListener {

    //Se declaran las variales que se vas a usar
    EditText etnombre, etautor, etletra;
    Button btnR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_coros);

        //Se igualan las variables a sus campos de la interfaz por medio de su id
        etnombre = findViewById(R.id.etNombreCoro);
        etautor = findViewById(R.id.etAutorCoro);
        etletra = findViewById(R.id.etLetraCoro);
        btnR = findViewById(R.id.btnGuardarCoros);

        //Se crea el evento OnClickListener del botón
        btnR.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //Se declaran nuevas variables para registrar
        final String titulo = etnombre.getText().toString();
        final String autor = etautor.getText().toString();
        final String letra = etletra.getText().toString();

        // Se valida que si los campos están vacíos que indique que lo están
        if (etnombre.getText().toString().length() == 0){
            etnombre.setError("Campo Obligatorio");
        }else if (etautor.getText().toString().length() == 0){
            etautor.setError("Campo Obligatorio");
        }else if (etletra.getText().toString().length() == 0){
            etletra.setError("Campo Obligatorio");
        }else {

            // Si los campos están llenos entonces procede con el metodo de guardar
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");

                        if (success ){

                            //Si las condiciones se cumplen entonces guarda el registro y lanza un Toast
                            Toast.makeText(RegistrarCoros.this, "Registro realizado exitosamente", Toast.LENGTH_SHORT).show();
                            etnombre.setText(null);
                            etautor.setText(null);
                            etletra.setText(null);
                        }else {

                            //Sino se cumplen las condiciones lanza un error que no se pudo guardar
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarCoros.this);
                            builder.setMessage("Error al registrar")
                                    .setNegativeButton("Retry", null)
                                    .create().show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            //Se agrega el nuego registro a la base de datos
            RegisterRequestCoro registrarRequestCoro = new RegisterRequestCoro(titulo, autor, letra, responseListener);
            RequestQueue queue = Volley.newRequestQueue(RegistrarCoros.this);
            queue.add(registrarRequestCoro);
        }
    }
}
