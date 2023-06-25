package com.example.pasaka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class septintasPuslapis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_septintas_puslapis);
        ImageView rodykleDes;

        rodykleDes = findViewById(R.id.rodykleDesine);
        rodykleDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rodyklepirmyn = new Intent(septintasPuslapis.this, klausimaiPuslapis.class);
                startActivity(rodyklepirmyn);
            }
        });

        ImageView rodykleKair;

        rodykleKair = findViewById(R.id.rodykleKaire);
        rodykleKair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rodykleatgal = new Intent(septintasPuslapis.this, astuntasPuslapis.class);
                startActivity(rodykleatgal);
            }
        });
    }

}