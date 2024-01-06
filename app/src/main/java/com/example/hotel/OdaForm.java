package com.example.hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OdaForm extends AppCompatActivity {

    private EditText editTextRoomTitle;
    private Button buttonAddRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oda_form);

        editTextRoomTitle = findViewById(R.id.editTextRoomTitle);
        buttonAddRoom = findViewById(R.id.buttonAddRoom);

        buttonAddRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kullanıcının girdiği oda başlığını al
                String roomTitle = editTextRoomTitle.getText().toString();

                // Burada bu bilgiyi kullanarak veritabanına eklemeyi veya başka bir işlemi gerçekleştirmeyi sağlayabilirsiniz.
                // Örneğin: SQLite veritabanına ekleme işlemi
                DatabaseHelper roomDatabaseHelper = new DatabaseHelper(OdaForm.this);
                long result = roomDatabaseHelper.addRoom(roomTitle);

                // Ekleme işleminin başarılı olup olmadığını kontrol et
                if (result != -1) {
                    // Oda ekleme işlemi tamamlandıktan sonra bir geri bildirim verebilirsiniz.
                    // Örneğin: Toast mesajı gösterme
                    Toast.makeText(OdaForm.this, "Oda Eklendi", Toast.LENGTH_SHORT).show();
                } else {
                    // Hata durumunda bir mesaj gösterme
                    Toast.makeText(OdaForm.this, "Oda eklenirken bir hata oluştu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
