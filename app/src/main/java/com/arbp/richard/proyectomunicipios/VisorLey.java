package com.arbp.richard.proyectomunicipios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

public class VisorLey extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visor);

        PDFView pdfView = (PDFView) findViewById(R.id.pdfView);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String ley = (String)bundle.get("ley");
        pdfView.fromAsset( ley ).load();
    }
} 