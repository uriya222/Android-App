package com.example.lecturerrating;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ShapeDrawable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String PERSONAL_PROFILE_KEY = "personal profile";

    private static Utils utils;
    private SharedPreferences sharedPreferences;

    private Utils(Context context){
        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

    }

    public void InitProfile(PersonalProfile profile) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String s = gson.toJson(profile);
        System.out.println(s);
        editor.putString(PERSONAL_PROFILE_KEY,s);
        editor.apply();
    }

    public static Utils getInstance(Context context){
        if(utils == null){
            utils = new Utils(context);
        }
        return utils;
    }

    public void UpdateProfile(PersonalProfile profile){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.remove(PERSONAL_PROFILE_KEY);
        editor.putString(PERSONAL_PROFILE_KEY,gson.toJson(profile));
        editor.apply();
    }

    public PersonalProfile getProfile() {
        Gson gson = new Gson();
        Type type = new TypeToken<PersonalProfile>(){}.getType();
        return gson.fromJson(sharedPreferences.getString(PERSONAL_PROFILE_KEY,null),type );
    }
}
