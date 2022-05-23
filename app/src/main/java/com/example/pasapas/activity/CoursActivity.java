package com.example.pasapas.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pasapas.R;
import com.example.pasapas.adapter.AdapterCours;
import com.example.pasapas.databinding.ActivityCoursBinding;
import com.example.pasapas.model.Cours;
import com.example.pasapas.model.Enfants;
import com.example.pasapas.model.Users;
import com.example.pasapas.service.GlobalService;
import com.example.pasapas.tools.ParamCours;
import com.example.pasapas.tools.ResponseArray;
import com.example.pasapas.tools.RetrofitClientInstance;
import com.example.pasapas.tools.Tools;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoursActivity extends AppCompatActivity {
    CircularProgressIndicator progressIndicator;
    ActivityCoursBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCoursBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TextView textView = findViewById(R.id.textCours);
        textView.setText(getIntent().getStringExtra("nomCours"));
        progressIndicator = findViewById(R.id.circularCours);
        getCourse(getIntent().getStringExtra("nomCours"));

    }

    void getCourse(String cours) {
        SharedPreferences sharedPreferences = this.getSharedPreferences("userIdentity", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        Integer indexKids = sharedPreferences.getInt("index", 0);
        Users users = Tools.getUser(sharedPreferences);
        Enfants enfants = users.getEnfants().get(indexKids);
        ParamCours paramCours = new ParamCours(cours, enfants.getCategorie());
        System.out.println("ParamCours = " + paramCours);
        GlobalService globalService = RetrofitClientInstance.getRetrofitInstance().create(GlobalService.class);
        globalService.course(token, paramCours).enqueue(
                new Callback<ResponseArray>() {
                    @Override
                    public void onResponse(Call<ResponseArray> call, Response<ResponseArray> response) {
                        System.out.println(response.body().toString());
                        if(response.body().getCode() == 200) {
//                            text.setText(response.body().code);
                            System.out.println("Body = " + response.body().getData().toString());
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<Cours>>(){}.getType();
                            List<Cours> array = gson.fromJson(response.body().getData(), type);
                            progressIndicator.hide();
                            generateList(array);
                        }else {
                            progressIndicator.hide();
                            Toast.makeText(CoursActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseArray> call, Throwable t) {
                        // if error occurs in network transaction then we can get the error in this method.
                        System.out.println(t.getMessage());
                        progressIndicator.hide();
                        Toast.makeText(CoursActivity.this, t.getMessage(), Toast.LENGTH_LONG).show(); //dismiss progress dialog
                    }
                });
    }

    void details(Cours cours) {
        Intent intent = new Intent(CoursActivity.this, VideoActivity.class);
        intent.putExtra("cours", cours);
        startActivity(intent);
    }

    void generateList(List<Cours> list) {
        binding.listview.setAdapter(new AdapterCours(CoursActivity.this, list));
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                details(list.get(i));
            }
        });
    }
}