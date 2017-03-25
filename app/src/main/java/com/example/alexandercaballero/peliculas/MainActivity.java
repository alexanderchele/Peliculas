package com.example.alexandercaballero.peliculas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static Context mContext;
    private Context context;
    JSONObject kivaJason=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mContext=this;
       String url="http://proyectopelicula.esy.es/obtener_pelicula.php";
     getPeliculas(url);


    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_peliculas) {

           String url="http://proyectopelicula.esy.es/obtener_pelicula.php";
            getPeliculas(url);
            setTitle("Peliculas");
        } else if (id == R.id.nav_actores) {
            String url="http://proyectopelicula.esy.es/obtener_actor.php";
            getActores(url);
            setTitle("Actores");
        } else if (id == R.id.nav_directores) {
            String url="http://proyectopelicula.esy.es/director.php";
            getDirectores(url);
            setTitle("Directores");
        } else if (id == R.id.nav_cartelera) {
            String url="http://proyectopelicula.esy.es/cartelera.php";
            getCartelera(url);
            setTitle("Cartelera Cinematográfica");
        } else if (id == R.id.nav_Personajes) {
            String url="http://proyectopelicula.esy.es/actor_pelicula.php";
            getActoresPeliculas(url);
            setTitle("Personajes");
        } else if (id == R.id.nav_directores_peliculas) {
            String url=" http://proyectopelicula.esy.es/pelicula_director.php";
            getDirectoresPeliculas(url);
            setTitle("Directores y sus películas");

        } else if (id == R.id.nav_comentarios) {
            String url="http://proyectopelicula.esy.es/comentario.php";
            getComentario(url);
            setTitle("Comentarios");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public Context getContext() {
        return context;
    }




    // ------------------------METODOS DE CONSULTAS---------------------------
    private void getPeliculas(String url) {
        final Context context=this;
        JsonObjectRequest jor=new JsonObjectRequest(
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Logger.getAnonymousLogger().log(Level.INFO,response.toString());
                        try {
                            kivaJason=response;
                            JSONArray loans=response.getJSONArray("pelicula");

                            ArrayList<JSONObject> dataSourse=new ArrayList<JSONObject>();
                            for(int i=0;i<loans.length();i++)
                            {
                                dataSourse.add(loans.getJSONObject(i));

                            }
                            CeldaAdaptador adapter=new CeldaAdaptador(context,0,dataSourse);
                            ((ListView)findViewById(R.id.lista1)).setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Nota: ponerse a llorar
                        Logger.getAnonymousLogger().log(Level.SEVERE,"Error Fataliti");


                    }
                }
        );
        MySingleton.getInstance(mContext).addToRequestQueue(jor);
    }

    /////Obtener Directores
    private void getDirectores(String url) {
        final Context context=this;
        JsonObjectRequest jor=new JsonObjectRequest(
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Logger.getAnonymousLogger().log(Level.INFO,response.toString());
                        try {
                            JSONArray loans=response.getJSONArray("director");

                            ArrayList<JSONObject> dataSourse=new ArrayList<JSONObject>();
                            for(int i=0;i<loans.length();i++)
                            {
                                dataSourse.add(loans.getJSONObject(i));

                            }
                            CeldaAdaptadorDirectores adapter=new CeldaAdaptadorDirectores(context,0,dataSourse);
                            ((ListView)findViewById(R.id.lista1)).setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Nota: ponerse a llorar
                        Logger.getAnonymousLogger().log(Level.SEVERE,"Error Fataliti");
                    }
                }
        );
        MySingleton.getInstance(mContext).addToRequestQueue(jor);
    }
/// Obteniendo Actores
    private void getActores(String url) {
        final Context context=this;
        JsonObjectRequest jor=new JsonObjectRequest(
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Logger.getAnonymousLogger().log(Level.INFO,response.toString());
                        try {
                            JSONArray loans=response.getJSONArray("actor");

                            ArrayList<JSONObject> dataSourse=new ArrayList<JSONObject>();
                            for(int i=0;i<loans.length();i++)
                            {
                                dataSourse.add(loans.getJSONObject(i));

                            }
                            CeldaAdaptadorActores adapter=new CeldaAdaptadorActores(context,0,dataSourse);
                            ((ListView)findViewById(R.id.lista1)).setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Nota: ponerse a llorar
                        Logger.getAnonymousLogger().log(Level.SEVERE,"Error Fataliti");


                    }
                }
        );
        MySingleton.getInstance(mContext).addToRequestQueue(jor);
    }


    ///////// Obteniendo personajer
    private void getActoresPeliculas(String url) {
        final Context context=this;
        JsonObjectRequest jor=new JsonObjectRequest(
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Logger.getAnonymousLogger().log(Level.INFO,response.toString());
                        try {
                            JSONArray loans=response.getJSONArray("actor_pelicula");

                            ArrayList<JSONObject> dataSourse=new ArrayList<JSONObject>();
                            for(int i=0;i<loans.length();i++)
                            {
                                dataSourse.add(loans.getJSONObject(i));

                            }
                            CeldaAdaptadorActoresPeliculas adapter=new CeldaAdaptadorActoresPeliculas(context,0,dataSourse);
                            ((ListView)findViewById(R.id.lista1)).setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Nota: ponerse a llorar
                        Logger.getAnonymousLogger().log(Level.SEVERE,"Error Fataliti");


                    }
                }
        );
        MySingleton.getInstance(mContext).addToRequestQueue(jor);
    }

    ///////// Obteniendo Directores y sus peliculas
    private void getDirectoresPeliculas(String url) {
        final Context context=this;
        JsonObjectRequest jor=new JsonObjectRequest(
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Logger.getAnonymousLogger().log(Level.INFO,response.toString());
                        try {
                            JSONArray loans=response.getJSONArray("pelicula_director");

                            ArrayList<JSONObject> dataSourse=new ArrayList<JSONObject>();
                            for(int i=0;i<loans.length();i++)
                            {
                                dataSourse.add(loans.getJSONObject(i));

                            }
                            CeldaAdaptadorDirectoresPeliculas adapter=new CeldaAdaptadorDirectoresPeliculas(context,0,dataSourse);
                            ((ListView)findViewById(R.id.lista1)).setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Nota: ponerse a llorar
                        Logger.getAnonymousLogger().log(Level.SEVERE,"Error Fataliti");


                    }
                }
        );
        MySingleton.getInstance(mContext).addToRequestQueue(jor);
    }

    ///obteniendo cartelera
    private void getCartelera(String url) {
        final Context context=this;
        JsonObjectRequest jor=new JsonObjectRequest(
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Logger.getAnonymousLogger().log(Level.INFO,response.toString());
                        try {
                            JSONArray loans=response.getJSONArray("cartelera");

                            ArrayList<JSONObject> dataSourse=new ArrayList<JSONObject>();
                            for(int i=0;i<loans.length();i++)
                            {
                                dataSourse.add(loans.getJSONObject(i));

                            }
                            CeldaAdaptadorCartelera adapter=new CeldaAdaptadorCartelera(context,0,dataSourse);
                            ((ListView)findViewById(R.id.lista1)).setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Nota: ponerse a llorar
                        Logger.getAnonymousLogger().log(Level.SEVERE,"Error Fataliti");


                    }
                }
        );
        MySingleton.getInstance(mContext).addToRequestQueue(jor);
    }


    ///obteniendo comentario
    private void getComentario(String url) {
        final Context context=this;
        JsonObjectRequest jor=new JsonObjectRequest(
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Logger.getAnonymousLogger().log(Level.INFO,response.toString());
                        try {
                            JSONArray loans=response.getJSONArray("comentario");

                            ArrayList<JSONObject> dataSourse=new ArrayList<JSONObject>();
                            for(int i=0;i<loans.length();i++)
                            {
                                dataSourse.add(loans.getJSONObject(i));

                            }
                            CeldaAdaptadorComentario adapter=new CeldaAdaptadorComentario(context,0,dataSourse);
                            ((ListView)findViewById(R.id.lista1)).setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Nota: ponerse a llorar
                        Logger.getAnonymousLogger().log(Level.SEVERE,"Error Fataliti");


                    }
                }
        );
        MySingleton.getInstance(mContext).addToRequestQueue(jor);
    }
    public void verTriler(View view){
        Intent Browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=9-3OCc5g5oE"));
        startActivity(Browser);
    }

}


