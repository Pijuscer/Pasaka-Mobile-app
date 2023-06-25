package com.example.pasaka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import pl.droidsonroids.gif.GifImageView;

public class penktasPuslapis extends AppCompatActivity {

    GifImageView vilkas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penktas_puslapis);
        vilkas = findViewById(R.id.vilkasgifas);
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.vilkogarsas);
        vilkas.setOnClickListener(new View.OnClickListener() {
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
                Intent rodyklepirmyn = new Intent(penktasPuslapis.this, sestasPuslapis.class);
                startActivity(rodyklepirmyn);
            }
        });

        ImageView rodykleKair;

        rodykleKair = findViewById(R.id.rodykleKaire);
        rodykleKair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rodykleatgal = new Intent(penktasPuslapis.this,ketvirtasPuslapis.class);
                startActivity(rodykleatgal);
            }
        });
    }
}