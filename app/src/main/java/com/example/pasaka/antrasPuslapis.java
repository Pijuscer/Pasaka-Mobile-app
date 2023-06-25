package com.example.pasaka;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import pl.droidsonroids.gif.GifImageView;

public class antrasPuslapis extends AppCompatActivity {
    GifImageView strazdas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antras_puslapis);
        strazdas = findViewById(R.id.strazdasgifas);
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.strazdogarsas2);
        strazdas.setOnClickListener(new View.OnClickListener() {
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
                Intent rodyklepirmyn = new Intent(antrasPuslapis.this, treciasPuslapis.class);
                startActivity(rodyklepirmyn);
            }
        });

        ImageView rodykleKair;

        rodykleKair = findViewById(R.id.rodykleKaire);
        rodykleKair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rodykleatgal = new Intent(antrasPuslapis.this, MainActivity.class);
                startActivity(rodykleatgal);
            }
        });
    }

}