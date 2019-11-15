package com.example.proyectofinal;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequestCoro extends StringRequest {

    private static final String REGISTER_REQUEST_URL ="http://192.168.43.68/sis22/Coro.php" ;
    private Map<String, String> params;
    public RegisterRequestCoro(String titulo, String autor, String letra, Response.Listener<String> listener){

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params=new HashMap<>();



        params.put("titulo", titulo);
        params.put("autor", autor);
        params.put("letra", letra);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}