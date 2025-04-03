package com.example.mojabaza_ps;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface DaoKlubyPilkarskie {

    @Insert
    public void wstawKlub(KlubPilkarski klub);

    @Delete
    public void usunKlub(KlubPilkarski klub);

    @Update
    public void edytujKlub(KlubPilkarski klub);

    @Query("SELECT * FROM kluby_pilkarskie")
    public List<KlubPilkarski> zwrocWszystkieKluby();


}
