package com.example.tugas2;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class RoomReadActivity extends AppCompatActivity {
    private AppDatabase db;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Mobil> daftarMobil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        daftarMobil = new ArrayList<>();

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "mobildb").allowMainThreadQueries().build();

        rvView = findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        /**
         * Add all data to arraylist
         */
        daftarMobil.addAll(Arrays.asList(db.mobilDAO().selectAllMobils()));

        adapter = new AdapterMobilRecyclerView(daftarMobil, this);
        rvView.setAdapter(adapter);
    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, RoomReadActivity.class);
    }
}
