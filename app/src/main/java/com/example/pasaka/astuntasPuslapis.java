package com.example.pasaka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import pl.droidsonroids.gif.GifImageView;

public class astuntasPuslapis extends AppCompatActivity {

    GifImageView meska;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astuntas_puslapis);
        meska = findViewById(R.id.meskagifas);
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.meskosgarsas);
        meska.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

        ImageView rodykleDes;

        rodykleDes = findViewById(R.id.rodykleDesine);
        rodykleDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rodyklepirmyn = new Intent(astuntasPuslapis.this, septintasPuslapis.class);
                startActivity(rodyklepirmyn);
            }
        });

        ImageView rodykleKair;

        rodykleKair = findViewById(R.id.rodykleKaire);
        rodykleKair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rodykleatgal = new Intent(astuntasPuslapis.this, sestasPuslapis.class);
                startActivity(rodykleatgal);
            }
        });
    }
}