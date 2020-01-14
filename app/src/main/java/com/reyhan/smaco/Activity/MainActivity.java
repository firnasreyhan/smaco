package com.reyhan.smaco.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import com.reyhan.smaco.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView cvSearch = findViewById(R.id.cvSearch);
        CardView cvList = findViewById(R.id.cvList);
        CardView cvInsert = findViewById(R.id.cvInsert);
        CardView cvWeight = findViewById(R.id.cvWeight);

        cvSearch.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SearchActivity.class)));

        cvList.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ListActivity.class)));

        cvInsert.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, InsertMenuActivity.class)));

        cvWeight.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, WeightActivity.class)));
    }
}
