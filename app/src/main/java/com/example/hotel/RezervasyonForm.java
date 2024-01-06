package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class RezervasyonForm extends AppCompatActivity {

    private Spinner spinnerRooms;
    private EditText editTextCustomerName, editTextDuration;
    private DatePicker datePicker;
    private Button buttonAddReservation;
    private DatabaseHelper databaseHelper;
    private List<String> roomList; // Oda başlıklarını içeren liste
    private ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezervasyon_form);

        // DatabaseHelper sınıfını başlat
        databaseHelper = new DatabaseHelper(this);

        // XML elemanlarını bağla
        spinnerRooms = findViewById(R.id.spinnerRooms);
        editTextCustomerName = findViewById(R.id.editTextCustomerName);
        editTextDuration = findViewById(R.id.editTextDuration);
        datePicker = findViewById(R.id.datePicker);
        buttonAddReservation = findViewById(R.id.buttonAddReservation);

        // Oda başlıkları için veritabanından verileri al
        roomList = databaseHelper.getAllRooms();

        // Spinner için adapter oluştur
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roomList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRooms.setAdapter(spinnerAdapter);

        // Rezervasyon ekleme butonuna tıklama olayı ekle
        buttonAddReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedRoomTitle = roomList.get(spinnerRooms.getSelectedItemPosition());
                String customerName = editTextCustomerName.getText().toString();
                int duration = Integer.parseInt(editTextDuration.getText().toString());

                // DatePicker'dan seçilen tarih bilgisini al
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1; // Ay 0-11 indekslidir
                int year = datePicker.getYear();
                String startDate = year + "-" + month + "-" + day;

                databaseHelper.addReservation(selectedRoomTitle,customerName,startDate,duration);

                // Burada bu bilgileri kullanarak veritabanına veya başka bir depolama mekanizmasına kaydetme işlemi yapabilirsiniz.
                // Bu örnekte sadece bir Toast mesajı gösteriliyor.
                String message = "Rezervasyon Eklendi:\n" +
                        "Oda Başlığı: " + selectedRoomTitle + "\n" +
                        "Müşteri Adı: " + customerName + "\n" +
                        "Başlangıç Tarihi: " + startDate + "\n" +
                        "Kaç Gün: " + duration;
                Toast.makeText(RezervasyonForm.this, message, Toast.LENGTH_LONG).show();
            }
        });

        // DatePicker'da tarih değiştiğinde tetiklenecek olayı ekle
        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Tarih değiştiğinde yapılacak işlemleri burada gerçekleştirebilirsiniz.
            }
        });
    }
}
