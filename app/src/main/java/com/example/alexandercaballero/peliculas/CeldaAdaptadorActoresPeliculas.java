package com.example.alexandercaballero.peliculas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Alexander Caballero on 16/2/2017.
 */

public class CeldaAdaptadorActoresPeliculas extends ArrayAdapter<JSONObject> {

    public CeldaAdaptadorActoresPeliculas(Context context, int textViewResourseId){
        super(context, textViewResourseId);
    }
    public CeldaAdaptadorActoresPeliculas(Context context, int resourse, List<JSONObject> items){
        super(context,resourse,items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View celda = convertView;
        if (celda==null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            celda= layoutInflater.inflate(R.layout.celda_adaptador_actores_peliculas,null);
        }
        //Video video;


        TextView actor=(TextView) celda.findViewById(R.id.actor);
        TextView pelicula=(TextView) celda.findViewById(R.id.nombrePelicula );
        TextView personaje=(TextView) celda.findViewById(R.id.personaje);

        NetworkImageView niv= (NetworkImageView)celda.findViewById(R.id.imagenPelicula);
        NetworkImageView niv1= (NetworkImageView)celda.findViewById(R.id.fotoActor);
        JSONObject elemento=this.getItem(position);
        try {

            String url=elemento.getString("imagen");
            String url1=elemento.getString("foto");
            actor.setText("Actor: "+elemento.getString("nombreactor"));
            pelicula.setText("Película: "+elemento.getString("nombrepelicula"));
            personaje.setText("Personaje: "+elemento.getString("personaje"));

            //int img= Integer.parseInt(imagen);
           // String url = "https://www.kiva.org/img/512/"+img+".jpg";
            niv.setImageUrl(url,MySingleton.getInstance(MainActivity.mContext).getImageLoader());
            niv1.setImageUrl(url1,MySingleton.getInstance(MainActivity.mContext).getImageLoader());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return celda;
    }
}
