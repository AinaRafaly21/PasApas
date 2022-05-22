package com.example.pasapas.service;

import com.example.pasapas.model.UserLogin;
import com.example.pasapas.model.Users;
import com.example.pasapas.tools.ResponseArray;
import com.example.pasapas.tools.ResponseFormat;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {
    @POST("/login")
    public Call<ResponseFormat> login(@Body UserLogin user);

    @POST("/insertUser")
    public Call<ResponseFormat> singup(@Body UserLogin user);

    @PUT("/updateUser")
    public Call<ResponseFormat> update(@Header("Authorization") String token, @Body Users user);
}
