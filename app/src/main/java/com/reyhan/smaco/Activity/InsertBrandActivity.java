package com.reyhan.smaco.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.reyhan.smaco.Api.Api;
import com.reyhan.smaco.Api.ApiClient;
import com.reyhan.smaco.Model.BrandModelResponse;
import com.reyhan.smaco.R;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertBrandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_brand);

        Api api = ApiClient.getClient();

        EditText etBrand = findViewById(R.id.etBrand);
        MaterialButton mbSave = findViewById(R.id.mbSave);

        mbSave.setOnClickListener(v -> {
            api.postBrand(
                    etBrand.getText().toString()
            ).enqueue(new Callback<BrandModelResponse>() {
                @Override
                public void onResponse(@NotNull Call<BrandModelResponse> call, @NotNull Response<BrandModelResponse> response) {
                    Log.e("Brand", "Success");
                }

                @Override
                public void onFailure(@NotNull Call<BrandModelResponse> call, @NotNull Throwable t) {
                    Log.e("Brand", Objects.requireNonNull(t.getMessage()));
                }
            });
            Toast.makeText(InsertBrandActivity.this, "Terima Kasih, data akan divalidasi oleh Admin", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
