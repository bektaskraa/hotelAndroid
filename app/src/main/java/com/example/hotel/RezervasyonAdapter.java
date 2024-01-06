// ReservationAdapter.java
package com.example.hotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RezervasyonAdapter extends ArrayAdapter<Rezervasyon> {

    public RezervasyonAdapter(Context context, List<Rezervasyon> reservations) {
        super(context, 0, reservations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        Rezervasyon reservation = getItem(position);

        // Özel öğe tasarımındaki TextView'leri bağla
        TextView textViewRoomTitle = convertView.findViewById(R.id.textViewRoomTitle);
        TextView textViewCustomerName = convertView.findViewById(R.id.textViewCustomerName);
        TextView textViewStartDate = convertView.findViewById(R.id.textViewStartDate);
        TextView textViewDuration = convertView.findViewById(R.id.textViewDuration);

        // Verileri TextView'lere yerleştir
        textViewRoomTitle.setText("Oda: " + reservation.getOdaBasligi());
        textViewCustomerName.setText(reservation.getMusteriAdi());
        textViewStartDate.setText("Başlangıç Tarihi: " + reservation.getBaslangicTarihi());
        textViewDuration.setText(String.valueOf(reservation.getKacGun()) + " gün kalacak");

        return convertView;
    }
}
