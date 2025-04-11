package com.example.mojabaza_ps;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoKlubyPilkarskie {

    @Insert
    public void wstawKlub(KlubPilkarski klub);

    @Delete
    public void usunKlub(KlubPilkarski klub);

    @Update
    public void aktualizujKlub(KlubPilkarski klub);

    @Query("SELECT * FROM kluby_pilkarskie")
    public List<KlubPilkarski> zwrocWszystkieKluby();

    @Query("SELECT * FROM kluby_pilkarskie WHERE trener = 'Pep Guardiola' ")
    public List<KlubPilkarski> zwrocKlubZDanymTrenerem();
}
