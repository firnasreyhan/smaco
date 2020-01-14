package com.reyhan.smaco.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.reyhan.smaco.Adapter.RecyclerViewPhoneAdapter;
import com.reyhan.smaco.Api.Api;
import com.reyhan.smaco.Api.ApiClient;
import com.reyhan.smaco.Model.BrandModelResponse;
import com.reyhan.smaco.Model.TypeModelResponse;
import com.reyhan.smaco.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    private RecyclerView rvPhoneList;
    private ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        rvPhoneList = findViewById(R.id.rvPhoneList);
        shimmerFrameLayout = findViewById(R.id.shimmerFrameLayout);

        ArrayList<TypeModelResponse.TypeModel> list = (ArrayList<TypeModelResponse.TypeModel>) getIntent().getSerializableExtra("search");
        int activity = getIntent().getIntExtra("activity", 0);

        if (activity == 1) {
            setRecyclerView(list);
        } else {
            Api api = ApiClient.getClient();
            api.getType().enqueue(new Callback<TypeModelResponse>() {
                @Override
                public void onResponse(@NotNull Call<TypeModelResponse> call, @NotNull Response<TypeModelResponse> response) {
                    assert response.body() != null;
                    Log.e("Type", String.valueOf(response.body().data.size()));
                    setRecyclerView(response.body().data);
                }

                @Override
                public void onFailure(@NotNull Call<TypeModelResponse> call, @NotNull Throwable t) {
                    Log.e("Type", Objects.requireNonNull(t.getMessage()));
                }
            });
        }
    }

    public void setRecyclerView(ArrayList<TypeModelResponse.TypeModel> list) {
        rvPhoneList.setLayoutManager(new LinearLayoutManager(this));
        rvPhoneList.setAdapter(new RecyclerViewPhoneAdapter(list));

        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
        rvPhoneList.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        shimmerFrameLayout.startShimmer();
    }

    @Override
    protected void onPause() {
        shimmerFrameLayout.stopShimmer();
        super.onPause();
    }
}
