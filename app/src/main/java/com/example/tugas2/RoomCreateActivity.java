package com.example.tugas2;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RoomCreateActivity extends AppCompatActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "mobildb").build();

        final EditText etNamaMobil = findViewById(R.id.et_namamobil);
        final EditText etMerkMobil = findViewById(R.id.et_merkmobil);
        final EditText etHargaMobil = findViewById(R.id.et_hargamobil);
        Button btSubmit = findViewById(R.id.bt_submit);

        final Mobil mobil = (Mobil) getIntent().getSerializableExtra("data");

        if (mobil != null) {
            etNamaMobil.setText(mobil.getNamaMobil());
            etMerkMobil.setText(mobil.getMerkMobil());
            etHargaMobil.setText(mobil.getHargaMobil());
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mobil.setNamaMobil(etNamaMobil.getText().toString());
                    mobil.setMerkMobil(etMerkMobil.getText().toString());
                    mobil.setHargaMobil(etHargaMobil.getText().toString());

                    updateMobil(mobil);
                }
            });
        } else {
            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Mobil b = new Mobil();
                    b.setHargaMobil(etHargaMobil.getText().toString());
                    b.setMerkMobil(etMerkMobil.getText().toString());
                    b.setNamaMobil(etNamaMobil.getText().toString());
                    insertData(b);
                }
            });
        }
    }

    private void updateMobil(final Mobil mobil){
        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.mobilDAO().updateMobil(mobil);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(RoomCreateActivity.this, "status row "+status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    private void insertData(final Mobil mobil){

        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.mobilDAO().insertMobil(mobil);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(RoomCreateActivity.this, "status row "+status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, RoomCreateActivity.class);
    }
}