package com.example.pasaka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

    public class MainActivity extends AppCompatActivity {
        ImageView rodykleDes;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            rodykleDes = findViewById(R.id.rodykleDesine);
            rodykleDes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent rodyklepirmyn = new Intent(MainActivity.this, antrasPuslapis.class);
                    startActivity(rodyklepirmyn);
                }
            });
        }
    }
