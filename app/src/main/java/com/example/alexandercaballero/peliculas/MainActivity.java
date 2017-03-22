package com.example.alexandercaballero.peliculas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mContext=this;


    }

public void verTrailer(View view){
    TextView trailer=(TextView)findViewById(R.id.status);
    String u;
    u=(String)trailer.getText();

    Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse(u));
    startActivity(intent);
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
            String url="http://moviemaniapeliculas.esy.es/obtener_peliculas.php";
            getPeliculas(url);

        } else if (id == R.id.nav_directores) {
            String url="http://moviemaniapeliculas.esy.es/obtener_peliculas.php";
            getDirectores(url);
        } else if (id == R.id.nav_horarios) {

        } else if (id == R.id.nav_salas) {

        } else if (id == R.id.nav_elenco) {
         

        } else if (id == R.id.nav_chino) {


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

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
                            JSONArray loans=response.getJSONArray("peliculas");

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
                            JSONArray loans=response.getJSONArray("peliculas");

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
                            JSONArray loans=response.getJSONArray("peliculas");

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
                            JSONArray loans=response.getJSONArray("actorpelicula");

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
}


