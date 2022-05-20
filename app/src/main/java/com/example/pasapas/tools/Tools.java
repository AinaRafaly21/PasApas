package com.example.pasapas.tools;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Patterns;


import com.example.pasapas.model.Users;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class Tools {
    public static boolean validatePassword(String text) {
        return (text.trim().length() >= 4);
    }

    public static boolean validateMail(String text) {
        return (!TextUtils.isEmpty(text) && Patterns.EMAIL_ADDRESS.matcher(text).matches());
    }

    public static ArrayList<Categories> categories() {
        ArrayList<Categories> array = new ArrayList<Categories>();
        array.add(new Categories("Moins de 3 ans", 0, 3));
        array.add(new Categories("Moins de 3 ans", 0, 3));
        return array;
    }

    public static Users getUser(SharedPreferences sharedPreferences) {
//        SharedPreferences sharedPreferences= this.getSharedPreferences("userIdentity", Context.MODE_PRIVATE);
        String s = sharedPreferences.getString("user", null);
        Gson gson = new Gson();
        return gson.fromJson(s, Users.class);
//        return (Users) intent.getSerializableExtra("users");
    }
}
