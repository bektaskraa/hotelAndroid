package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button yeniRezervasyonButton = findViewById(R.id.yeniOda);
        Button odalarButton = findViewById(R.id.button2);
        Button rezervasyonlarButton = findViewById(R.id.button3);

        yeniRezervasyonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Yeni Rezervasyon Aktivitesine geçiş yap
                Intent intent = new Intent(MainActivity.this, RezervasyonForm.class);
                startActivity(intent);
            }
        });

        odalarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Odalar.class);
                startActivity(intent);
            }
        });

        rezervasyonlarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Rezervasyonlar.class);
                startActivity(intent);
            }
        });
    }
}