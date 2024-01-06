package com.example.hotel;

public class Rezervasyon {
    private String odaBasligi;
    private String musteriAdi;
    private String baslangicTarihi;
    private int kacGun;

    public Rezervasyon(String odaBasligi, String musteriAdi, String baslangicTarihi, int kacGun) {
        this.odaBasligi = odaBasligi;
        this.musteriAdi = musteriAdi;
        this.baslangicTarihi = baslangicTarihi;
        this.kacGun = kacGun;
    }

    // Getter ve setter metotları


    public String getOdaBasligi() {
        return odaBasligi;
    }

    public void setOdaBasligi(String odaBasligi) {
        this.odaBasligi = odaBasligi;
    }

    public String getMusteriAdi() {
        return musteriAdi;
    }

    public void setMusteriAdi(String musteriAdi) {
        this.musteriAdi = musteriAdi;
    }

    public String getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public void setBaslangicTarihi(String baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
    }

    public int getKacGun() {
        return kacGun;
    }

    public void setKacGun(int kacGun) {
        this.kacGun = kacGun;
    }
}
