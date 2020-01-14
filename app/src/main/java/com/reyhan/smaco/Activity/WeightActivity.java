package com.reyhan.smaco.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.reyhan.smaco.Model.WeightModel;
import com.reyhan.smaco.Preference.AppPreference;
import com.reyhan.smaco.R;

import java.util.ArrayList;

public class WeightActivity extends AppCompatActivity {

    private Spinner sCpu, sRam, sStorage, sCamera, sBattery, sPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        sCpu = findViewById(R.id.sCpu);
        sRam = findViewById(R.id.sRam);
        sStorage = findViewById(R.id.sStorage);
        sCamera = findViewById(R.id.sCamera);
        sBattery = findViewById(R.id.sBattery);
        sPrice = findViewById(R.id.sPrice);
        MaterialButton mbSave = findViewById(R.id.mbSave);

        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("Sangat Tidak Penting");
        arrayList.add("Tidak Penting");
        arrayList.add("Biasa");
        arrayList.add("Penting");
        arrayList.add("Sangat Penting");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sCpu.setAdapter(spinnerArrayAdapter);
        sRam.setAdapter(spinnerArrayAdapter);
        sStorage.setAdapter(spinnerArrayAdapter);
        sCamera.setAdapter(spinnerArrayAdapter);
        sBattery.setAdapter(spinnerArrayAdapter);
        sPrice.setAdapter(spinnerArrayAdapter);

        WeightModel saveWeight = AppPreference.getWeight(this);
        if (saveWeight != null) {
            sCpu.setSelection(saveWeight.cpu - 1);
            sRam.setSelection(saveWeight.ram - 1);
            sStorage.setSelection(saveWeight.storage - 1);
            sCamera.setSelection(saveWeight.camera - 1);
            sBattery.setSelection(saveWeight.battery - 1);
            sPrice.setSelection(saveWeight.price - 1);
        }

        mbSave.setOnClickListener(v -> {
            WeightModel model = new WeightModel();
            model.cpu = sCpu.getSelectedItemPosition() + 1;
            model.ram = sRam.getSelectedItemPosition() + 1;
            model.storage = sStorage.getSelectedItemPosition() + 1;
            model.camera = sCamera.getSelectedItemPosition() + 1;
            model.battery = sBattery.getSelectedItemPosition() + 1;
            model.price = sPrice.getSelectedItemPosition() + 1;

            AppPreference.saveWeight(this, model);

            Toast.makeText(this, "Data Bobot Telah Disimpan", Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
