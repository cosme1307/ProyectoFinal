package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrarCoros extends AppCompatActivity implements View.OnClickListener {

    EditText etnombre, etautor, etletra;
    Button btnR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_coros);


        etnombre = findViewById(R.id.etNombreCoro);
        etautor = findViewById(R.id.etAutorCoro);
        etletra = findViewById(R.id.etLetraCoro);

        btnR = findViewById(R.id.btnGuardarCoros);

        btnR.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
