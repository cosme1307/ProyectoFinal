package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;

public class CorosAleActivity extends AppCompatActivity {
    private EditText ettitulocal, etautorcal, etletracal;
    private Button btnRegistrarcal;
    private ListView lvdatoscal;
    private AsyncHttpClient clientecal = new AsyncHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coros_ale);


    }
}
