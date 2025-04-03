package com.example.mojabaza_ps;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "kluby_pilkarskie")
public class KlubPilkarski {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "nazwa_klubu")
    private int id;
    private String nazwa;

    @ColumnInfo(name = "informacje")
    private int rokZalozenia;
    private int miejsceWTabeli;
    private String liga;
    private String trener;

    @Ignore
    public KlubPilkarski(){
    }

    public KlubPilkarski(String nazwa, int rokZalozenia, int miejsceWTabeli, String liga, String trener) {
        id = 0;
        this.nazwa = nazwa;
        this.rokZalozenia = rokZalozenia;
        this.miejsceWTabeli = miejsceWTabeli;
        this.liga = liga;
        this.trener = trener;
    }

    public int getId(){ return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getRokZalozenia() {return rokZalozenia;}

    public void setRokZalozenia(int rokZalozenia) {this.rokZalozenia = rokZalozenia;}

    public int getMiejsceWTabeli() {return miejsceWTabeli;}

    public void setMiejsceWTabeli(int miejsceWTabeli) {this.miejsceWTabeli = miejsceWTabeli;}

    public String getLiga() {return liga;}

    public void setLiga(String liga) {this.liga = liga;}

    public String getTrener() {return trener;}

    public void setTrener(String trener) {this.trener = trener;}

        @Override
        public String toString(){
            return nazwa;
        }
    }


