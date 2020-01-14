package com.reyhan.smaco.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.reyhan.smaco.Api.Api;
import com.reyhan.smaco.Api.ApiClient;
import com.reyhan.smaco.Model.BrandModelResponse;
import com.reyhan.smaco.Model.TypeModelResponse;
import com.reyhan.smaco.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_type);

        Api api = ApiClient.getClient();
        ArrayList<BrandModelResponse.BrandModel> brandList = new ArrayList<>();

        Spinner sBrand = findViewById(R.id.sBrand);
        EditText etType = findViewById(R.id.etType);
        EditText etCpu = findViewById(R.id.etCpu);
        EditText etRam = findViewById(R.id.etRam);
        EditText etStorage = findViewById(R.id.etStorage);
        EditText etCamera = findViewById(R.id.etCamera);
        EditText etBattery = findViewById(R.id.etBattery);
        EditText etPrice = findViewById(R.id.etPrice);
        MaterialButton mbSave = findViewById(R.id.mbSave);

        api.getBrand().enqueue(new Callback<BrandModelResponse>() {
            @Override
            public void onResponse(@NotNull Call<BrandModelResponse> call, @NotNull Response<BrandModelResponse> response) {
                assert response.body() != null;
                brandList.addAll(response.body().data);
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < brandList.size(); i++) {
                    list.add(brandList.get(i).merek);
                    Log.e("loop", brandList.get(i).merek);
                }
                ArrayAdapter spinnerArrayAdapter = new ArrayAdapter<>(InsertTypeActivity.this, android.R.layout.simple_spinner_dropdown_item, list);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                sBrand.setAdapter(spinnerArrayAdapter);
            }

            @Override
            public void onFailure(@NotNull Call<BrandModelResponse> call, @NotNull Throwable t) {
                Log.e("Brand", Objects.requireNonNull(t.getMessage()));
            }
        });

        mbSave.setOnClickListener(v -> {
            api.postType(
                    brandList.get(sBrand.getSelectedItemPosition()).idMerek,
                    etType.getText().toString(),
                    etPrice.getText().toString(),
                    etCamera.getText().toString(),
                    etBattery.getText().toString(),
                    etRam.getText().toString(),
                    etStorage.getText().toString(),
                    etCpu.getText().toString()).enqueue(new Callback<TypeModelResponse>() {
                @Override
                public void onResponse(@NotNull Call<TypeModelResponse> call, @NotNull Response<TypeModelResponse> response) {
                    Log.e("Type", "Success");
                }

                @Override
                public void onFailure(@NotNull Call<TypeModelResponse> call, @NotNull Throwable t) {
                    Log.e("Type", Objects.requireNonNull(t.getMessage()));
                }
            });
            Toast.makeText(InsertTypeActivity.this, "Terima Kasih, data akan divalidasi oleh Admin", Toast.LENGTH_LONG).show();
            finish();
        });

    }
}
