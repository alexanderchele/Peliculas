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

public class CeldaAdaptadorComentario extends ArrayAdapter<JSONObject> {

    public CeldaAdaptadorComentario(Context context, int textViewResourseId){
        super(context, textViewResourseId);
    }
    public CeldaAdaptadorComentario(Context context, int resourse, List<JSONObject> items){
        super(context,resourse,items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View celda = convertView;
        if (celda==null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            celda= layoutInflater.inflate(R.layout.celda_adaptador_comentarios,null);
        }
        //Video video;


        TextView usuario=(TextView) celda.findViewById(R.id.usuario);
        TextView pelicula=(TextView) celda.findViewById(R.id.nombrePeliculaComentarios);
        TextView fecha=(TextView) celda.findViewById(R.id.fechaComentario);
        TextView comentario=(TextView) celda.findViewById(R.id.comentario);
        TextView calificacion=(TextView) celda.findViewById(R.id.calificación);
        NetworkImageView niv= (NetworkImageView)celda.findViewById(R.id.imagenPeliculaComentarios);

        JSONObject elemento=this.getItem(position);
        try {

            String url=elemento.getString("imagen");

            usuario.setText("Usuario: "+elemento.getString("nombreusuario"));
            pelicula.setText(elemento.getString("nombrepelicula"));
            fecha.setText("Fecha: "+elemento.getString("fechacomentario"));
            comentario.setText("Comentario: "+elemento.getString("comentario"));
            calificacion.setText("calificación: "+elemento.getInt("calificacion"));
//recordar mostrar calificación con estrellas

            niv.setImageUrl(url,MySingleton.getInstance(MainActivity.mContext).getImageLoader());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return celda;
    }
}
