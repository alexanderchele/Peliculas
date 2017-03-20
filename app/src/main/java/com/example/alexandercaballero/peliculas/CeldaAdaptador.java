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

public class CeldaAdaptador extends ArrayAdapter<JSONObject> {

    public CeldaAdaptador (Context context, int textViewResourseId){
        super(context, textViewResourseId);
    }
    public CeldaAdaptador(Context context, int resourse, List<JSONObject> items){
        super(context,resourse,items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View celda = convertView;
        if (celda==null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            celda= layoutInflater.inflate(R.layout.celda_adaptador,null);
        }
        //Video video;

        TextView id = (TextView) celda.findViewById(R.id.id);
        TextView nombre=(TextView) celda.findViewById(R.id.nombre);
        TextView descripcion=(TextView) celda.findViewById(R.id.descripcion);
        TextView dir=(TextView) celda.findViewById(R.id.dir);

        NetworkImageView niv= (NetworkImageView)celda.findViewById(R.id.imagen);

        JSONObject elemento=this.getItem(position);
        try {

            String url=elemento.getString("url_imagen");

            id.setText(elemento.getString("id_pelicula"));

            nombre.setText("Nombre: "+elemento.getString("nombre"));
            descripcion.setText("Descripción: "+elemento.getString("descripcion"));
            dir.setText("Url: "+url);
            //int img= Integer.parseInt(imagen);
           // String url = "https://www.kiva.org/img/512/"+img+".jpg";
            niv.setImageUrl(url,MySingleton.getInstance(MainActivity.mContext).getImageLoader());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return celda;
    }
}
