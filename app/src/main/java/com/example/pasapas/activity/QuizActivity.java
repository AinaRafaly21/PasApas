package com.example.pasapas.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pasapas.MainActivity;
import com.example.pasapas.R;
import com.example.pasapas.adapter.AdapterQuiz;
import com.example.pasapas.databinding.ActivityQuizBinding;
import com.example.pasapas.model.Qcm;
import com.example.pasapas.model.Quiz;
import com.example.pasapas.service.GlobalService;
import com.example.pasapas.tools.ParamQuiz;
import com.example.pasapas.tools.ResponseFormat;
import com.example.pasapas.tools.RetrofitClientInstance;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizActivity extends AppCompatActivity {
    CircularProgressIndicator progressIndicator;
    ActivityQuizBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        progressIndicator = findViewById(R.id.circularQuiz);
        Button button = findViewById(R.id.retour);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ParamQuiz paramQuiz = (ParamQuiz) getIntent().getSerializableExtra("paramQuiz");
        getQuiz(paramQuiz);
    }

    void getQuiz(ParamQuiz paramQuiz) {
        SharedPreferences sharedPreferences = this.getSharedPreferences("userIdentity", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        GlobalService globalService = RetrofitClientInstance.getRetrofitInstance().create(GlobalService.class);
        globalService.quiz(token, paramQuiz).enqueue(
                new Callback<ResponseFormat>() {
                    @Override
                    public void onResponse(Call<ResponseFormat> call, Response<ResponseFormat> response) {
                        if(response.body().getCode() == 200) {
                            Gson gson = new Gson();
                            Quiz quiz = gson.fromJson(response.body().getData(), Quiz.class);
                            progressIndicator.hide();
                            generateList(quiz.getQcm());
                        }else {
                            progressIndicator.hide();
                            Toast.makeText(QuizActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseFormat> call, Throwable t) {
                        // if error occurs in network transaction then we can get the error in this method.
                        progressIndicator.hide();
                        Toast.makeText(QuizActivity.this, t.getMessage(), Toast.LENGTH_LONG).show(); //dismiss progress dialog
                    }
                });
    }

    void generateList(ArrayList<Qcm> list) {
        binding.listview.setAdapter(new AdapterQuiz(QuizActivity.this, list));
    }
}