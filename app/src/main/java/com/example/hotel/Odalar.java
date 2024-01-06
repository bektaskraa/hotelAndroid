package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Odalar extends AppCompatActivity {

    private ListView listViewOdalariGoster;
    private Button yeniOda;
    private TextView odalarTextView;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odalar);

        // DatabaseHelper sınıfını başlat
        databaseHelper = new DatabaseHelper(this);

        // XML elemanlarını bağla
        listViewOdalariGoster = findViewById(R.id.listyViewOdalar);
        yeniOda = findViewById(R.id.yeniOda);
        odalarTextView = findViewById(R.id.textView);

        yeniOda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Odalar.this,OdaForm.class);
                startActivity(i);

            }
        });

        // Veritabanından odaları al
        List<String> odalar = getOdalariGoster();

        // ArrayAdapter kullanarak ListView'a verileri bağla
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.room_item, R.id.textViewRoomTitle, odalar);
        listViewOdalariGoster.setAdapter(adapter);

        // ListView'a tıklama olayı ekle
        listViewOdalariGoster.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Tıklanan odayı al
                String selectedRoom = (String) parent.getItemAtPosition(position);

                // Odayı silme işlemi
                showDeleteConfirmationDialog(selectedRoom);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        databaseHelper = new DatabaseHelper(this);
        List<String> odalar = getOdalariGoster();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.room_item, R.id.textViewRoomTitle, odalar);
        listViewOdalariGoster.setAdapter(adapter);
    }

    private List<String> getOdalariGoster() {
        List<String> odalar = databaseHelper.getAllRooms();
        return odalar;
    }

    private void showDeleteConfirmationDialog(final String selectedRoom) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Odayı silmek istediğinize emin misiniz?")
                .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Kullanıcı evet derse odayı sil
                        deleteRoom(selectedRoom);
                    }
                })
                .setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Kullanıcı hayır derse işlemi iptal et
                        dialog.dismiss();
                    }
                });

        // Dialog penceresini göster
        builder.create().show();
    }

    private void deleteRoom(String roomTitle) {
        // Odayı veritabanından sil
        databaseHelper.removeRoom(roomTitle);

        // Odayı ListView'dan kaldır
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) listViewOdalariGoster.getAdapter();
        adapter.remove(roomTitle);
        adapter.notifyDataSetChanged();
    }
}
