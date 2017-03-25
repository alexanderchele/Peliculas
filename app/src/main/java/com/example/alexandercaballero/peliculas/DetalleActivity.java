package com.example.alexandercaballero.peliculas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;

public class DetalleActivity extends AppCompatActivity {
    String jsonString;
String video="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        setTitle("Detalle de película");
        NetworkImageView niv= (NetworkImageView)findViewById(R.id.imagenDetalle);
        jsonString = getIntent().getStringExtra("pelicula");
       // JSONArray loans=response.getJSONArray("pelicula");
        try {
            JSONObject json = new JSONObject(jsonString);
            String url=json.getString("imagen");
            String video=json.getString("video");
            ((TextView) findViewById(R.id._nombre)).setText(json.getString("nombrepelicula"));
            ((TextView) findViewById(R.id._descripcion)).setText(json.getString("descripcion"));
            ((TextView) findViewById(R.id._genero)).setText("Género: "+json.getString("genero"));
            ((TextView) findViewById(R.id._calificacion)).setText("Clasificación "+json.getString("clasificacion"));
            niv.setImageUrl(url,MySingleton.getInstance(MainActivity.mContext).getImageLoader());

        } catch (JSONException excepcion) {

        } catch (Exception excepcion) {

            Log.d("DetalleActivity", excepcion.getMessage());
        }

        Log.d("DetalleActivity", "Terminar de crear actividad");
    }


}