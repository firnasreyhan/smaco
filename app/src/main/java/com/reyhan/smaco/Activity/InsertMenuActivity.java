package com.reyhan.smaco.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.reyhan.smaco.R;

public class InsertMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_menu);

        CardView cvBrand = findViewById(R.id.cvBrand);
        CardView cvType = findViewById(R.id.cvType);

        cvBrand.setOnClickListener(v -> startActivity(new Intent(InsertMenuActivity.this, InsertBrandActivity.class)));

        cvType.setOnClickListener(v -> startActivity(new Intent(InsertMenuActivity.this, InsertTypeActivity.class)));
    }
}
