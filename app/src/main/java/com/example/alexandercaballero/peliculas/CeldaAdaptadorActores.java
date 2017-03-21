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

public class CeldaAdaptadorActores extends ArrayAdapter<JSONObject> {

    public CeldaAdaptadorActores(Context context, int textViewResourseId){
        super(context, textViewResourseId);
    }
    public CeldaAdaptadorActores(Context context, int resourse, List<JSONObject> items){
        super(context,resourse,items);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View celda = convertView;
        if (celda==null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            celda= layoutInflater.inflate(R.layout.celda_adaptador_actores,null);
        }
        //Video video;


        TextView nombre=(TextView) celda.findViewById(R.id.nombreActor);
        TextView biografia=(TextView) celda.findViewById(R.id.biografia);


        NetworkImageView niv= (NetworkImageView)celda.findViewById(R.id.imagenActor);

        JSONObject elemento=this.getItem(position);
        try {

            String url=elemento.getString("url_imagen");
            nombre.setText("Nombre: "+elemento.getString("nombre"));
            biografia.setText("Descripción: "+elemento.getString("biografia"));
            //int img= Integer.parseInt(imagen);
           // String url = "https://www.kiva.org/img/512/"+img+".jpg";
            niv.setImageUrl(url,MySingleton.getInstance(MainActivity.mContext).getImageLoader());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return celda;
    }
}
