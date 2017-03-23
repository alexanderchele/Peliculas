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

public class CeldaAdaptadorCartelera extends ArrayAdapter<JSONObject> {

    public CeldaAdaptadorCartelera(Context context, int textViewResourseId){
        super(context, textViewResourseId);
    }
    public CeldaAdaptadorCartelera(Context context, int resourse, List<JSONObject> items){
        super(context,resourse,items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View celda = convertView;
        if (celda==null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            celda= layoutInflater.inflate(R.layout.celda_adaptador_cartelera,null);
        }
        //Video video;


        TextView cine=(TextView) celda.findViewById(R.id.cine);
        TextView pelicula=(TextView) celda.findViewById(R.id.nombrePeliculaCartelera);
        TextView fecha=(TextView) celda.findViewById(R.id.fecha);
        TextView hora=(TextView) celda.findViewById(R.id.hora);
        TextView sala=(TextView) celda.findViewById(R.id.sala);

        NetworkImageView niv= (NetworkImageView)celda.findViewById(R.id.imagenPeliculaCartelera);

        JSONObject elemento=this.getItem(position);
        try {

            String url=elemento.getString("imagen");


            pelicula.setText(elemento.getString("nombrepelicula"));
            cine.setText(elemento.getString("nombrecine"));
            sala.setText("sala: "+elemento.getString("sala"));
            fecha.setText("Fecha: "+elemento.getString("fecha"));
            hora.setText("hora: "+elemento.getString("hora"));


            //int img= Integer.parseInt(imagen);
           // String url = "https://www.kiva.org/img/512/"+img+".jpg";
            niv.setImageUrl(url,MySingleton.getInstance(MainActivity.mContext).getImageLoader());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return celda;
    }
}
