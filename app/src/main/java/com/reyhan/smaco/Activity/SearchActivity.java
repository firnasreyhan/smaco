package com.reyhan.smaco.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.reyhan.smaco.Api.Api;
import com.reyhan.smaco.Api.ApiClient;
import com.reyhan.smaco.Model.BrandModelResponse;
import com.reyhan.smaco.Model.TypeModelResponse;
import com.reyhan.smaco.Model.WeightModel;
import com.reyhan.smaco.Preference.AppPreference;
import com.reyhan.smaco.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private String cpu = "";
    private String ram = "";
    private String storage = "";
    private String camera = "";
    private String battery = "";
    private String price = "";

    private double wCpu = 0;
    private double wRam = 0;
    private double wStorage = 0;
    private double wCamera = 0;
    private double wBattery = 0;
    private double wPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Api api = ApiClient.getClient();
        weight();

        ArrayList<BrandModelResponse.BrandModel> brandList = new ArrayList<>();

        Spinner sBrand = findViewById(R.id.sBrand);
        RadioGroup rgCpu = findViewById(R.id.rgCpu);
        RadioGroup rgRam = findViewById(R.id.rgRam);
        RadioGroup rgStorage = findViewById(R.id.rgStorage);
        RadioGroup rgCamera = findViewById(R.id.rgCamera);
        RadioGroup rgBattery = findViewById(R.id.rgBattery);
        RadioGroup rgPrice = findViewById(R.id.rgPrice);
        EditText etCpu = findViewById(R.id.etCpu);
        EditText etRam = findViewById(R.id.etRam);
        EditText etStorage = findViewById(R.id.etStorage);
        EditText etCamera = findViewById(R.id.etCamera);
        EditText etBattery = findViewById(R.id.etBattery);
        EditText etPrice = findViewById(R.id.etPrice);
        MaterialButton mbSearch = findViewById(R.id.mbSearch);

        api.getBrand().enqueue(new Callback<BrandModelResponse>() {
            @Override
            public void onResponse(@NotNull Call<BrandModelResponse> call, @NotNull Response<BrandModelResponse> response) {
                assert response.body() != null;
                brandList.addAll(response.body().data);
                ArrayList<String> list = new ArrayList<>();
                list.add("Semua Merek");
                for (int i = 0; i < brandList.size(); i++) {
                    list.add(brandList.get(i).merek);
                    Log.e("loop", brandList.get(i).merek);
                }
                ArrayAdapter spinnerArrayAdapter = new ArrayAdapter<>(SearchActivity.this, android.R.layout.simple_spinner_dropdown_item, list);
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                sBrand.setAdapter(spinnerArrayAdapter);
            }

            @Override
            public void onFailure(@NotNull Call<BrandModelResponse> call, @NotNull Throwable t) {
                Log.e("Brand", Objects.requireNonNull(t.getMessage()));
            }
        });

        rgCpu.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rbLowCpu:
                    cpu = "<=";
                    break;
                case R.id.rbMidCpu:
                    cpu = "=";
                    break;
                case R.id.rbHighCpu:
                    cpu = ">=";
                    break;
            }
        });

        rgRam.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rbLowRam:
                    ram = "<=";
                    break;
                case R.id.rbMidRam:
                    ram = "=";
                    break;
                case R.id.rbHighRam:
                    ram = ">=";
                    break;
            }
        });

        rgStorage.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rbLowStorage:
                    storage = "<=";
                    break;
                case R.id.rbMidStorage:
                    storage = "=";
                    break;
                case R.id.rbHighStorage:
                    storage = ">=";
                    break;
            }
        });

        rgCamera.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rbLowCamera:
                    camera = "<=";
                    break;
                case R.id.rbMidCamera:
                    camera = "=";
                    break;
                case R.id.rbHighCamera:
                    camera = ">=";
                    break;
            }
        });

        rgBattery.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rbLowBattery:
                    battery = "<=";
                    break;
                case R.id.rbMidBattery:
                    battery = "=";
                    break;
                case R.id.rbHighBattery:
                    battery = ">=";
                    break;
            }
        });

        rgPrice.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rbLowPrice:
                    price = "<=";
                    break;
                case R.id.rbMidPrice:
                    price = "=";
                    break;
                case R.id.rbHighPrice:
                    price = ">=";
                    break;
            }
        });

        mbSearch.setOnClickListener(v -> {
            String query = "";
            if (sBrand.getSelectedItemPosition() != 0) {
                query = query + " AND merek.MEREK ='" + brandList.get(sBrand.getSelectedItemPosition()-1).merek + "'";
            }

            if (!cpu.isEmpty() && !etCpu.getText().toString().isEmpty()) {
                query = query + " AND tipe.PROSESOR " + cpu + " " + etCpu.getText().toString();
            }

            if (!ram.isEmpty() && !etRam.getText().toString().isEmpty()) {
                query = query + " AND tipe.RAM " + ram + " " + etRam.getText().toString();
            }

            if (!storage.isEmpty() && !etStorage.getText().toString().isEmpty()) {
                query = query + " AND tipe.PENYIMPANAN " + storage + " " + etStorage.getText().toString();
            }

            if (!camera.isEmpty() && !etCamera.getText().toString().isEmpty()) {
                query = query + " AND tipe.KAMERA " + camera + " " + etCamera.getText().toString();
            }

            if (!battery.isEmpty() && !etBattery.getText().toString().isEmpty()) {
                query = query + "AND tipe.BATERAI " + battery + " " + etBattery.getText().toString();
            }

            if (!price.isEmpty() && !etPrice.getText().toString().isEmpty()) {
                query = query + " AND tipe.HARGA " + price + " " + etPrice.getText().toString();
            }

            api.postTypeFilter(query).enqueue(new Callback<TypeModelResponse>() {
                @Override
                public void onResponse(@NotNull Call<TypeModelResponse> call, @NotNull Response<TypeModelResponse> response) {
                    Log.e("Filter", "Sukses");
                    assert response.body() != null;
                    if (!response.body().data.isEmpty()) {
                        calculate(response.body().data);
                    } else {
                        Toast.makeText(SearchActivity.this, "Pencarian Tidak Ditemukan, Silahkan Coba Lagi", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(@NotNull Call<TypeModelResponse> call, @NotNull Throwable t) {
                    Log.e("Filter", Objects.requireNonNull(t.getMessage()));
                }
            });
        });
    }

    public void calculate(ArrayList<TypeModelResponse.TypeModel> list) {
        ArrayList<TypeModelResponse.TypeModel> normalisasi = new ArrayList<>();
        ArrayList<TypeModelResponse.TypeModel> normalisasiTerbobot = new ArrayList<>();
        ArrayList<TypeModelResponse.TypeModel> solusiIdeal = new ArrayList<>();
        ArrayList<Double> positif = new ArrayList<>();
        ArrayList<Double> negatif = new ArrayList<>();
        ArrayList<TypeModelResponse.TypeModel> typeWithScore = new ArrayList<>();
        double kaCpu = 0, kaRam = 0, kaStorage = 0, kaCamera = 0, kaBattery = 0, kaPrice = 0;

        for (int i = 0; i < list.size(); i++) {
            kaCpu = kaCpu + Math.pow(list.get(i).prosesor, 2);
            kaRam = kaRam + Math.pow(list.get(i).ram, 2);
            kaStorage = kaStorage + Math.pow(list.get(i).penyimpanan, 2);
            kaCamera = kaCamera + Math.pow(list.get(i).kamera, 2);
            kaBattery = kaBattery + Math.pow(list.get(i).baterai, 2);
            kaPrice = kaPrice + Math.pow(list.get(i).harga, 2);
        }

        kaCpu = Math.sqrt(kaCpu);
        kaRam = Math.sqrt(kaRam);
        kaStorage = Math.sqrt(kaStorage);
        kaCamera = Math.sqrt(kaCamera);
        kaBattery = Math.sqrt(kaBattery);
        kaPrice = Math.sqrt(kaPrice);

        Log.e("kaCpu", String.valueOf(kaCpu));
        Log.e("kaRam", String.valueOf(kaRam));
        Log.e("kaStorage", String.valueOf(kaStorage));
        Log.e("kaCamera", String.valueOf(kaCamera));
        Log.e("kaBattery", String.valueOf(kaBattery));
        Log.e("kaPrice", String.valueOf(kaPrice));

        for (int i = 0; i < list.size(); i++) {
            normalisasi.add(
                    new TypeModelResponse.TypeModel(
                            list.get(i).idTipe,
                            list.get(i).merek,
                            list.get(i).tipe,
                            list.get(i).harga/kaPrice,
                            list.get(i).kamera/kaCamera,
                            list.get(i).baterai/kaBattery,
                            list.get(i).ram/kaRam,
                            list.get(i).penyimpanan/kaStorage,
                            list.get(i).prosesor/kaCpu,
                            0
                    )
            );
        }

        for (int i = 0; i < list.size(); i++) {
            normalisasiTerbobot.add(
                    new TypeModelResponse.TypeModel(
                            normalisasi.get(i).idTipe,
                            normalisasi.get(i).merek,
                            normalisasi.get(i).tipe,
                            normalisasi.get(i).harga * wPrice,
                            normalisasi.get(i).kamera * wCamera,
                            normalisasi.get(i).baterai * wBattery,
                            normalisasi.get(i).ram * wRam,
                            normalisasi.get(i).penyimpanan * wStorage,
                            normalisasi.get(i).prosesor * wCpu,
                            0
                    )
            );
        }

        for (int i = 0; i < 2; i++) {
            TypeModelResponse.TypeModel model = new TypeModelResponse.TypeModel();
            model.harga = normalisasiTerbobot.get(0).harga;
            model.kamera = normalisasiTerbobot.get(0).kamera;
            model.baterai = normalisasiTerbobot.get(0).baterai;
            model.ram = normalisasiTerbobot.get(0).ram;
            model.penyimpanan = normalisasiTerbobot.get(0).penyimpanan;
            model.prosesor = normalisasiTerbobot.get(0).prosesor;
            for (int j = 1; j < list.size(); j++) {
                if (i == 0) {
                    if (model.harga > normalisasiTerbobot.get(j).harga) {
                        model.harga = normalisasiTerbobot.get(j).harga;
                    }
                    if (model.kamera < normalisasiTerbobot.get(j).kamera) {
                        model.kamera = normalisasiTerbobot.get(j).kamera;
                    }
                    if (model.baterai < normalisasiTerbobot.get(j).baterai) {
                        model.baterai = normalisasiTerbobot.get(j).baterai;
                    }
                    if (model.ram < normalisasiTerbobot.get(j).ram) {
                        model.ram = normalisasiTerbobot.get(j).ram;
                    }
                    if (model.penyimpanan < normalisasiTerbobot.get(j).penyimpanan) {
                        model.penyimpanan = normalisasiTerbobot.get(j).penyimpanan;
                    }
                    if (model.prosesor < normalisasiTerbobot.get(j).prosesor) {
                        model.prosesor = normalisasiTerbobot.get(j).prosesor;
                    }
                }
                if (i == 1) {
                    if (model.harga < normalisasiTerbobot.get(j).harga) {
                        model.harga = normalisasiTerbobot.get(j).harga;
                    }
                    if (model.kamera > normalisasiTerbobot.get(j).kamera) {
                        model.kamera = normalisasiTerbobot.get(j).kamera;
                    }
                    if (model.baterai > normalisasiTerbobot.get(j).baterai) {
                        model.baterai = normalisasiTerbobot.get(j).baterai;
                    }
                    if (model.ram > normalisasiTerbobot.get(j).ram) {
                        model.ram = normalisasiTerbobot.get(j).ram;
                    }
                    if (model.penyimpanan > normalisasiTerbobot.get(j).penyimpanan) {
                        model.penyimpanan = normalisasiTerbobot.get(j).penyimpanan;
                    }
                    if (model.prosesor > normalisasiTerbobot.get(j).prosesor) {
                        model.prosesor = normalisasiTerbobot.get(j).prosesor;
                    }
                }
            }
            solusiIdeal.add(model);
        }

        Log.e("solusiIdeal+", String.valueOf(solusiIdeal.get(0).harga));
        Log.e("solusiIdeal+", String.valueOf(solusiIdeal.get(0).kamera));
        Log.e("solusiIdeal+", String.valueOf(solusiIdeal.get(0).baterai));
        Log.e("solusiIdeal+", String.valueOf(solusiIdeal.get(0).prosesor));
        Log.e("solusiIdeal+", String.valueOf(solusiIdeal.get(0).ram));
        Log.e("solusiIdeal+", String.valueOf(solusiIdeal.get(0).penyimpanan));


        Log.e("solusiIdeal-", String.valueOf(solusiIdeal.get(1).harga));
        Log.e("solusiIdeal-", String.valueOf(solusiIdeal.get(1).kamera));
        Log.e("solusiIdeal-", String.valueOf(solusiIdeal.get(1).baterai));
        Log.e("solusiIdeal-", String.valueOf(solusiIdeal.get(1).prosesor));
        Log.e("solusiIdeal-", String.valueOf(solusiIdeal.get(1).ram));
        Log.e("solusiIdeal-", String.valueOf(solusiIdeal.get(1).penyimpanan));

        for (int i = 0; i < list.size(); i++) {
            double solusiPositif =
                    Math.sqrt(
                            Math.pow(solusiIdeal.get(0).harga - normalisasiTerbobot.get(i).harga, 2) +
                            Math.pow(solusiIdeal.get(0).kamera - normalisasiTerbobot.get(i).kamera, 2) +
                            Math.pow(solusiIdeal.get(0).baterai - normalisasiTerbobot.get(i).baterai, 2) +
                            Math.pow(solusiIdeal.get(0).prosesor - normalisasiTerbobot.get(i).prosesor, 2) +
                            Math.pow(solusiIdeal.get(0).ram - normalisasiTerbobot.get(i).ram, 2) +
                            Math.pow(solusiIdeal.get(0).penyimpanan - normalisasiTerbobot.get(i).penyimpanan, 2)
                    );

            double solusiNegatif =
                    Math.sqrt(
                            Math.pow(normalisasiTerbobot.get(i).harga - solusiIdeal.get(1).harga, 2) +
                            Math.pow(normalisasiTerbobot.get(i).kamera - solusiIdeal.get(1).kamera, 2) +
                            Math.pow(normalisasiTerbobot.get(i).baterai - solusiIdeal.get(1).baterai, 2) +
                            Math.pow(normalisasiTerbobot.get(i).prosesor - solusiIdeal.get(1).prosesor, 2) +
                            Math.pow(normalisasiTerbobot.get(i).ram - solusiIdeal.get(1).ram, 2) +
                            Math.pow(normalisasiTerbobot.get(i).penyimpanan - solusiIdeal.get(1).penyimpanan, 2)
                    );

            positif.add(solusiPositif);
            negatif.add(solusiNegatif);
        }

        for (int i = 0; i < list.size(); i++) {
            Log.e("negatif", String.valueOf(negatif.get(i)));
            typeWithScore.add(
                    new TypeModelResponse.TypeModel(
                            list.get(i).idTipe,
                            list.get(i).merek,
                            list.get(i).tipe,
                            list.get(i).harga,
                            list.get(i).kamera,
                            list.get(i).baterai,
                            list.get(i).ram,
                            list.get(i).penyimpanan,
                            list.get(i).prosesor,
                            (negatif.get(i)/(negatif.get(i) + positif.get(i)))
                    )
            );
        }

        Collections.sort(typeWithScore, (o1, o2) -> Double.compare(o2.skor, o1.skor));

        Intent intent = new Intent(SearchActivity.this, ListActivity.class);
        intent.putExtra("search", typeWithScore);
        intent.putExtra("activity", 1);
        startActivity(intent);
    }

    public void weight() {
        WeightModel weight = AppPreference.getWeight(this);
        assert weight != null;
        double sumWeight = weight.cpu + weight.ram + weight.storage + weight.camera + weight.battery + weight.price;

        wCpu = weight.cpu/sumWeight;
        Log.e("wCpu", String.valueOf(weight.cpu/sumWeight));
        wRam = weight.ram/sumWeight;
        Log.e("wRam", String.valueOf(weight.ram/sumWeight));
        wStorage = weight.storage/sumWeight;
        Log.e("wStorage", String.valueOf(weight.storage/sumWeight));
        wCamera = weight.camera/sumWeight;
        Log.e("wCamera", String.valueOf(weight.camera/sumWeight));
        wBattery = weight.battery/sumWeight;
        Log.e("wBattery", String.valueOf(weight.battery/sumWeight));
        wPrice = weight.price/sumWeight;
        Log.e("wPrice", String.valueOf(weight.price/sumWeight));
    }
}
