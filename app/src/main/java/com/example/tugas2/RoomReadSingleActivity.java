package com.example.tugas2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RoomReadSingleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        EditText etNama = findViewById(R.id.et_namamobil);
        EditText etMerk = findViewById(R.id.et_merkmobil);
        EditText etHarga = findViewById(R.id.et_hargamobil);
        Button btSubmit = findViewById(R.id.bt_submit);

        etNama.setEnabled(false);
        etMerk.setEnabled(false);
        etHarga.setEnabled(false);
        btSubmit.setVisibility(View.GONE);

        Mobil mobil = (Mobil) getIntent().getSerializableExtra("data");
        if(mobil!=null){
            etNama.setText(mobil.getNamaMobil());
            etMerk.setText(mobil.getMerkMobil());
            etHarga.setText(mobil.getHargaMobil());
        }

    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, RoomReadSingleActivity.class);
    }
}