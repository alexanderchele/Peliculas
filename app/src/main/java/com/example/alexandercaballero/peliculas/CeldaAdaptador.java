package com.example.alexandercaballero.peliculas;

import android.content.Context;
import android.content.Intent;
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
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View celda = convertView;

        if (celda==null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            celda= layoutInflater.inflate(R.layout.celda_adaptador,null);
        }
        //Video video;


        TextView nombre=(TextView) celda.findViewById(R.id.nombre);
      /*  TextView descripcion=(TextView) celda.findViewById(R.id.descripcion);
        TextView genero=(TextView) celda.findViewById(R.id.genero);
        TextView clasificacion=(TextView) celda.findViewById(R.id.clasificasion);
*/
        NetworkImageView niv= (NetworkImageView)celda.findViewById(R.id.imagen);

        JSONObject elemento=this.getItem(position);
        try {

            String url=elemento.getString("imagen");
            nombre.setText(elemento.getString("nombrepelicula"));
           /* descripcion.setText(elemento.getString("descripcion"));
            genero.setText("Genero: "+elemento.getString("genero"));
            clasificacion.setText("Clasificación: "+elemento.getString("clasificacion"));
*/

            niv.setImageUrl(url,MySingleton.getInstance(MainActivity.mContext).getImageLoader());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        celda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetalleActivity.class);
                intent.putExtra("pelicula", getItem(position).toString());
                getContext().startActivity(intent);
            }
        });


        return celda;
    }
}
