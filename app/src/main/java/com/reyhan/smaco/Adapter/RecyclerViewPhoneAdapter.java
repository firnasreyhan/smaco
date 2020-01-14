package com.reyhan.smaco.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.reyhan.smaco.Model.TypeModelResponse;
import com.reyhan.smaco.R;

import java.util.ArrayList;

public class RecyclerViewPhoneAdapter extends RecyclerView.Adapter<RecyclerViewPhoneAdapter.ViewHolder>{

    private ArrayList<TypeModelResponse.TypeModel> arrayList;

    public RecyclerViewPhoneAdapter(ArrayList<TypeModelResponse.TypeModel> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_phone, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TypeModelResponse.TypeModel model = arrayList.get(position);

        holder.tvType.setText(model.tipe);
        holder.tvBrand.setText(model.merek);
        holder.tvCpu.setText(model.prosesor + " GHz");
        holder.tvRam.setText(String.format("%.0f", model.ram) + " GB");
        holder.tvStorage.setText(String.format("%.0f", model.penyimpanan) + " GB");
        holder.tvCamera.setText(model.kamera + " MP");
        holder.tvBattery.setText(String.format("%.0f", model.baterai) + " mAh");
        holder.tvPrice.setText("Rp. " + String.format("%.0f", model.harga));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvType, tvBrand, tvCpu, tvRam, tvStorage, tvCamera, tvBattery, tvPrice;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.tvType);
            tvBrand = itemView.findViewById(R.id.tvBrand);
            tvCpu = itemView.findViewById(R.id.tvCpu);
            tvRam = itemView.findViewById(R.id.tvRam);
            tvStorage = itemView.findViewById(R.id.tvStorage);
            tvCamera = itemView.findViewById(R.id.tvCamera);
            tvBattery = itemView.findViewById(R.id.tvBattery);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }

    }
}
