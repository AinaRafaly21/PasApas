package com.example.pasapas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pasapas.MainActivity;
import com.example.pasapas.R;
import com.example.pasapas.model.UserLogin;
import com.example.pasapas.model.Users;
import com.example.pasapas.service.UserService;
import com.example.pasapas.tools.ResponseFormat;
import com.example.pasapas.tools.RetrofitClientInstance;
import com.example.pasapas.tools.Tools;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView text = findViewById(R.id.inscription);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToSignup();
            }
        });

        EditText email = findViewById(R.id.inputMail);
        EditText password = findViewById(R.id.inputPassword);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singin(email.getText().toString(), password.getText().toString());
            }
        });
    }

    void singin(String email, String password){
        final ProgressDialog progressDialog = new ProgressDialog(Login.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setTitle("Connexion"); //set Title
        progressDialog.setMessage("Chargement"); // set message
        progressDialog.show(); // show progress dialog
        if(Tools.validateMail(email) && Tools.validatePassword(password)) {
            System.out.println("Okee" + email);
            UserLogin use= new UserLogin(email, password);
            UserService userservice = RetrofitClientInstance.getRetrofitInstance().create(UserService.class);
            userservice.login(use).enqueue(
                    new Callback<ResponseFormat>() {
                        @Override
                        public void onResponse(Call<ResponseFormat> call, Response<ResponseFormat> response) {
                            progressDialog.dismiss(); //dismiss progress dialog
                            System.out.println(response.body().toString());
                            if(response.body().getCode() == 200) {
//                            text.setText(response.body().code);
                                System.out.println("Body = " + response.body().getData().toString());
                                Gson gson = new Gson();
                                Users user = gson.fromJson(response.body().getData(), Users.class);
                                saveToken(user.getToken(), response.body().getData());
                                redirect();
                            }else {
                                Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseFormat> call, Throwable t) {
                            // if error occurs in network transaction then we can get the error in this method.
                            System.out.println(t.getMessage());
                            progressDialog.dismiss();
                            Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_LONG).show(); //dismiss progress dialog
                        }
                    });
        } else {
            progressDialog.dismiss();
            Toast.makeText(Login.this, "Format email ou mot de passe invalide", Toast.LENGTH_LONG).show();
        }

    }

    void redirectToSignup() {
        Intent intent = new Intent(Login.this, Inscription.class);
        startActivity(intent);
    }

    void redirect(){
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }

    void saveToken(String token, JsonObject user) {
        token = "Bearer ${token}";
        SharedPreferences sharedPreferences= this.getSharedPreferences("userIdentity", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.putString("user", user.toString());
        editor.apply();
    }
}