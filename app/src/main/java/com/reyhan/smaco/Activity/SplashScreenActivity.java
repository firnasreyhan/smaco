package com.reyhan.smaco.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.reyhan.smaco.Model.WeightModel;
import com.reyhan.smaco.Preference.AppPreference;
import com.reyhan.smaco.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        WeightModel saveWeight = AppPreference.getWeight(this);
        if (saveWeight == null) {
            WeightModel model = new WeightModel();
            model.cpu = 1;
            model.ram = 1;
            model.storage = 1;
            model.camera = 1;
            model.battery = 1;
            model.price = 1;

            AppPreference.saveWeight(this, model);
        }

        new Handler().postDelayed(() -> {
            /* Create an Intent that will start the Menu-Activity. */
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }, 3000);
    }
}
