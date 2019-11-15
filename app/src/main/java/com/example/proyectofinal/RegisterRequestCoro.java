package com.example.proyectofinal;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequestCoro extends StringRequest {

    //Aquí se pone la dirección de donde está el archivo php para guardar los datos en la base de datos
    //Junto con la ip local
    private static final String REGISTER_REQUEST_URL ="http://192.168.43.68/sis22/Coro.php" ;
    private Map<String, String> params;
    public RegisterRequestCoro(String titulo, String autor, String letra, Response.Listener<String> listener){

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params=new HashMap<>();

        //Estos son los parametros que recibe la tabla
        params.put("titulo", titulo);
        params.put("autor", autor);
        params.put("letra", letra);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
