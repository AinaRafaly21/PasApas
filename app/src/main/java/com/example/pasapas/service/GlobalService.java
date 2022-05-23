package com.example.pasapas.service;

import com.example.pasapas.tools.ParamCours;
import com.example.pasapas.tools.ParamQuiz;
import com.example.pasapas.tools.ResponseArray;
import com.example.pasapas.tools.ResponseFormat;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface GlobalService {
    @GET("/courses")
    public Call<ResponseArray> courses(@Header("Authorization") String token);

    @POST("/course")
    public Call<ResponseArray> course(@Header("Authorization") String token, @Body ParamCours paramCours);

    @POST("/quiz")
    public Call<ResponseFormat> quiz(@Header("Authorization") String token, @Body ParamQuiz paramQuiz);
}
