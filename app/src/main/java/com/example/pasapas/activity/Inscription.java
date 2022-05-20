package com.example.pasapas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pasapas.R;
import com.example.pasapas.model.UserLogin;
import com.example.pasapas.service.UserService;
import com.example.pasapas.tools.ResponseFormat;
import com.example.pasapas.tools.RetrofitClientInstance;
import com.example.pasapas.tools.Tools;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        EditText email = findViewById(R.id.emailAddress);
        EditText password = findViewById(R.id.password);
        EditText password2 = findViewById(R.id.password2);
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().compareToIgnoreCase(password2.getText().toString()) == 0) {
                    singup(email.getText().toString(), password.getText().toString());
                } else Toast.makeText(Inscription.this, "Mot de passe non identique", Toast.LENGTH_LONG).show();
            }
        });
    }

    void singup(String email, String password){
        if(Tools.validateMail(email) && Tools.validatePassword(password)) {
            UserLogin use= new UserLogin(email, password);
            UserService userservice = RetrofitClientInstance.getRetrofitInstance().create(UserService.class);
            userservice.singup(use).enqueue(
                    new Callback<ResponseFormat>() {
                        @Override
                        public void onResponse(Call<ResponseFormat> call, Response<ResponseFormat> response) {
//                        progressDialog.dismiss(); //dismiss progress dialog
                            if(response.body().getCode() == 200) {
//                            text.setText(response.body().code);
//                                System.out.println(response.body().getData().toString());
                                Toast.makeText(Inscription.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                redirect();
                            }else {
                                Toast.makeText(Inscription.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseFormat> call, Throwable t) {
                            // if error occurs in network transaction then we can get the error in this method.
                            Toast.makeText(Inscription.this, t.getMessage(), Toast.LENGTH_LONG).show(); //dismiss progress dialog
                        }
                    });
        } else {
            Toast.makeText(Inscription.this, "Format email ou mot de passe invalide", Toast.LENGTH_LONG).show();
        }

    }

    void redirect(){
        Intent intent = new Intent(Inscription.this, Login.class);
        startActivity(intent);
    }

}