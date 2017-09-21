package com.arbp.richard.proyectomunicipios;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.arbp.richard.proyectomunicipios.adapter.ListaMunicipiosAdapter;
import com.arbp.richard.proyectomunicipios.model.DatosRespuesta;
import com.arbp.richard.proyectomunicipios.model.Municipio;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityMunicipios extends AppCompatActivity{
    CollapsingToolbarLayout collapsing;

    private RecyclerView recyclerView;
    private ListaMunicipiosAdapter listaMunicipiosAdapter;
    ImageView imageView;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipios);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collapsing = (CollapsingToolbarLayout)findViewById(R.id.collapsingM);
        collapsing.setTitle("Municipios");

        imageView = (ImageView)findViewById(R.id.imageDep);

        recyclerView = (RecyclerView)findViewById(R.id.rV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listaMunicipiosAdapter = new ListaMunicipiosAdapter(this, this);
        recyclerView.setAdapter(listaMunicipiosAdapter);


        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fabM);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "REGLAMENTO PARA FUNCIONAMIENTO\n" +
                        "DE LA UNIDAD DESCONCENTRADA SUSTENTAR\n" +
                        "ESTRUCTURA ORGANIZACIÓNAL, FUNCIONES Y ATRIBUCIONES",Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(ActivityMunicipios.this, VisorLey.class);
                intent.putExtra("ley", "reglamento.pdf");
                startActivity(intent);
            }
        });
        cargarDatos();
    }

    private void cargarDatos() {
        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        String departamento = (String)bundle.get("Departamen");

        DatosGobBoService service = ServiceGenerator.createService(DatosGobBoService.class);

        Call<DatosRespuesta> call = service.municipiosDepartamento("a7ac328d-9bc5-46fc-b0ec-9ad5b5c0a513", departamento);
        call.enqueue(new Callback<DatosRespuesta>() {
            @Override
            public void onResponse(Call<DatosRespuesta> call, Response<DatosRespuesta> response) {
                if(response.isSuccessful()){
                    ArrayList<Municipio>lista = response.body().getResult().getRecords();
                    listaMunicipiosAdapter.adicionarListaMunicipio(lista);
                    for (int i=0; i<lista.size(); i++){
                        Municipio m = lista.get(i);

                        Glide.with(ActivityMunicipios.this)
                                .load("http://webservice111.esy.es/departamentos/"+ m.getDEPARTAMEN()+".png")
                                .centerCrop()
                                .crossFade()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(imageView);

                    }
                }
            }

            @Override
            public void onFailure(Call<DatosRespuesta> call, Throwable t) {

            }
        });

    }

} 