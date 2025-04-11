package com.example.mojabaza_ps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    DatabaseKluby databaseKluby;
    private Button button;
    private EditText editTextNazwa;
    private EditText editTextLiga;
    private EditText editTextTrener;
    private Spinner spinnerLiga;
    private ListView listView;
    private Button buttonUpdate;
    private KlubPilkarski selectedKlub;

    private ArrayAdapter<KlubPilkarski> arrayAdapter;
    private List<KlubPilkarski> klubPilkarskis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editTextNazwa = findViewById(R.id.editTextNazwa);
        editTextLiga = findViewById(R.id.editTextLiga);
        editTextTrener = findViewById(R.id.editTextTrener);
        spinnerLiga = findViewById(R.id.spinner);
        listView = findViewById(R.id.listView);
        buttonUpdate = findViewById(R.id.buttonUpdate);

        RoomDatabase.Callback mojCallback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }

            @Override
            public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                super.onDestructiveMigration(db);
            }
        };
        databaseKluby = Room.databaseBuilder(
                MainActivity.this,
                DatabaseKluby.class,
                "kluby_db").addCallback(mojCallback).allowMainThreadQueries().build();

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nazwa = editTextNazwa.getText().toString();
                        int miejsceWTabeli = Integer.parseInt(spinnerLiga.getSelectedItem().toString());
                        String liga = editTextLiga.getText().toString();
                        String trener = editTextTrener.getText().toString();
                        KlubPilkarski klubPilkarski = new KlubPilkarski(nazwa, 1943, miejsceWTabeli, liga, trener);
                        dodajKlubDoBazy(klubPilkarski);
                    }

                }

        );

        buttonUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nazwa = editTextNazwa.getText().toString();
                        int miejsceWTabeli = Integer.parseInt(spinnerLiga.getSelectedItem().toString());
                        String liga = editTextLiga.getText().toString();
                        String trener = editTextTrener.getText().toString();
                        KlubPilkarski klubPilkarski = new KlubPilkarski(nazwa, 1943, miejsceWTabeli, liga, trener);
                        aktualizujKlub();
                    }
                }
        );

        wypiszKlubyZBazy();

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        databaseKluby.zwrocDaoKlubyPilkarskie().usunKlub(klubPilkarskis.get(i));
                        klubPilkarskis.remove(i);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
        );
    }

            private void dodajKlubDoBazy(KlubPilkarski klubPilkarski){
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executorService.execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            databaseKluby.zwrocDaoKlubyPilkarskie().wstawKlub(klubPilkarski);

                            handler.post(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MainActivity.this, "Dodane do bazy", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                            );
                        }
                    }
            );
        }
        private void wypiszKlubyZBazy(){
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executorService.execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            klubPilkarskis = databaseKluby.zwrocDaoKlubyPilkarskie().zwrocWszystkieKluby();

                            handler.post(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            arrayAdapter = new ArrayAdapter<>(
                                                    MainActivity.this,
                                                    android.R.layout.simple_list_item_1,
                                                    klubPilkarskis

                                            );
                                            listView.setAdapter(arrayAdapter);

                                        }
                                    }
                            );
                        }
                    }
            );
        }

        private void aktualizujKlub(){
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executorService.execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            klubPilkarskis = databaseKluby.zwrocDaoKlubyPilkarskie().zwrocWszystkieKluby();

                            handler.post(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MainActivity.this, "zaaktualizowano klub", Toast.LENGTH_SHORT).show();
                                            wypiszKlubyZBazy();
                                        }
                                    }
                            );
                        }
                    }
            );
        }
    }
