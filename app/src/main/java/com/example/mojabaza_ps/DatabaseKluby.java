package com.example.mojabaza_ps;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {KlubPilkarski.class}, version = 1, exportSchema = false)
public abstract class DatabaseKluby extends RoomDatabase {
    public abstract DaoKlubyPilkarskie zwrocDaoKlubyPilkarskie();
}
