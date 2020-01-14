package com.reyhan.smaco.Preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.reyhan.smaco.Model.WeightModel;

public class AppPreference {
    private static final String PREF = "PREF";
    private static final String WEIGHT_PREF = "WEIGHT_PREF";

    public static void saveWeight(Context context, WeightModel model){
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE)
                .edit().putString(WEIGHT_PREF, new Gson().toJson(model)).apply();
    }

    public static WeightModel getWeight(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        if(pref.contains(WEIGHT_PREF)){
            Gson gson = new Gson();

            return gson.fromJson(pref.getString(WEIGHT_PREF, ""), WeightModel.class);
        }

        return null;
    }

    public static void removeWeight(Context context){
        SharedPreferences pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        if(pref.contains(WEIGHT_PREF)){
            pref.edit().remove(WEIGHT_PREF).apply();
        }
    }
}
