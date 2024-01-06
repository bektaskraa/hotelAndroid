// Rezervasyonlar.java
package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Rezervasyonlar extends AppCompatActivity {

    private ListView listViewReservations;
    private RezervasyonAdapter reservationAdapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezervasyonlar);

        Button button = findViewById(R.id.yeniOda);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Rezervasyonlar.this, RezervasyonForm.class);
                startActivity(intent);
            }
        });

        // DatabaseHelper sınıfını başlat
        databaseHelper = new DatabaseHelper(this);

        // Veritabanından rezervasyon verilerini al
        List<Rezervasyon> reservations = getReservationsFromDatabase();

        // ListView ve adapter'ı bağla
        listViewReservations = findViewById(R.id.listView);
        reservationAdapter = new RezervasyonAdapter(this, reservations);
        listViewReservations.setAdapter(reservationAdapter);

    }

    private List<Rezervasyon> getReservationsFromDatabase() {
        List<Rezervasyon> reservations = databaseHelper.getAllReservations();
        return reservations;
    }

    private void showReservationsAsToasts(List<Rezervasyon> reservations) {
        // Toast mesajları ile verileri göster
        for (Rezervasyon reservation : reservations) {
            String message = "Oda Başlığı: " + reservation.getOdaBasligi() + "\n" +
                    "Müşteri Adı: " + reservation.getMusteriAdi() + "\n" +
                    "Başlangıç Tarihi: " + reservation.getBaslangicTarihi() + "\n" +
                    "Kaç Gün: " + reservation.getKacGun();

            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // DatabaseHelper sınıfını başlat
        databaseHelper = new DatabaseHelper(this);

        // Veritabanından rezervasyon verilerini al
        List<Rezervasyon> reservations = getReservationsFromDatabase();

        // ListView ve adapter'ı bağla
        listViewReservations = findViewById(R.id.listView);
        reservationAdapter = new RezervasyonAdapter(this, reservations);
        listViewReservations.setAdapter(reservationAdapter);
    }
}

