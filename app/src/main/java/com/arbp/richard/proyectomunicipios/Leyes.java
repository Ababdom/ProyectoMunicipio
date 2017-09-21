package com.arbp.richard.proyectomunicipios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Leyes extends AppCompatActivity implements View.OnClickListener{
    Button botonley1178, botonley2027, botonley2341, botonley482;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leyes);

        botonley1178 = (Button)findViewById(R.id.btnLey1178);
        botonley2027 = (Button)findViewById(R.id.btnLey2027);
        botonley2341 = (Button)findViewById(R.id.btnLey2341);
        botonley482 = (Button)findViewById(R.id.btnLey482);

        botonley1178.setOnClickListener(this);
        botonley2027.setOnClickListener(this);
        botonley2341.setOnClickListener(this);
        botonley482.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLey1178:
                Intent intent = new Intent(this, VisorLey.class);
                intent.putExtra("ley", "safco.pdf");
                startActivity(intent);
                break;
            case R.id.btnLey2027:
                Intent i = new Intent(this, VisorLey.class);
                i.putExtra("ley", "efp.pdf");
                startActivity(i);
                break;
            case R.id.btnLey2341:
                Intent a = new Intent(this, VisorLey.class);
                a.putExtra("ley", "lpa.pdf");
                startActivity(a);
                break;
            case R.id.btnLey482:
                Intent r = new Intent(this, VisorLey.class);
                r.putExtra("ley", "lgam.pdf");
                startActivity(r);

        }
    }
}