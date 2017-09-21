package com.arbp.richard.proyectomunicipios;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.arbp.richard.proyectomunicipios.adapter.ListaDepartamentoAdapter;
import com.arbp.richard.proyectomunicipios.model.Departamento;
import com.arbp.richard.proyectomunicipios.model.Pais;
import com.arbp.richard.proyectomunicipios.util.Util;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements ListaDepartamentoAdapter.OnDepartamentosSelectedListener {

    private static final Gson gson = new Gson();
    private RecyclerView recyclerView;
    private ListaDepartamentoAdapter listaDepartamentoAdapter;

    CollapsingToolbarLayout collapsing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collapsing = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        collapsing.setTitle("MUNICIPIOS DE BOLIVIA");

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listaDepartamentoAdapter = new ListaDepartamentoAdapter(this, this);

        recyclerView.setAdapter(listaDepartamentoAdapter);
        cargarDatos();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();
                Intent i = new Intent(MainActivity.this, Leyes.class);
                startActivity(i);
            }
        });


    }

    private void cargarDatos() {
        String json = Util.leerJSON(this);

        Pais departamentos = gson.fromJson(json, Pais.class);
        listaDepartamentoAdapter.setDataset(departamentos.getDepartamentos());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onDepartamentosSelected(Departamento d) {
        Toast.makeText(getApplicationContext(),"Departamento "+  d.getNombre(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ActivityMunicipios.class);
        intent.putExtra("Departamen", "{\"DEPARTAMEN\":\"" + d.getNombre() +"\"}");
        startActivity(intent);

    }
}
