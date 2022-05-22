package com.example.pasapas.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pasapas.R;
import com.example.pasapas.model.Enfants;
import com.example.pasapas.model.Users;
import com.example.pasapas.service.UserService;
import com.example.pasapas.tools.ResponseFormat;
import com.example.pasapas.tools.RetrofitClientInstance;
import com.example.pasapas.tools.Tools;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreationEnfant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_enfant);

        EditText nom = findViewById(R.id.inputNom);
        EditText age = findViewById(R.id.inputAge);
        Button button = findViewById(R.id.addKids);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(nom.getText().toString(), age.getText().toString());
            }
        });
    }

    void redirect(){
        Intent intent = new Intent(CreationEnfant.this, EnfantsActivity.class);
        startActivity(intent);
    }

    void add(String nom, String age) {
        if(Integer.valueOf(age) > 6) Toast.makeText(CreationEnfant.this, "Ooops, nous n'avons pas encore de cours pour le plus de 6ans.", Toast.LENGTH_LONG).show();
        final ProgressDialog progressDialog = new ProgressDialog(CreationEnfant.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setTitle("Connexion"); //set Title
        progressDialog.setMessage("Chargement ... "); // set message
        progressDialog.show(); // show progress dialog
        if(!TextUtils.isEmpty(nom) && Integer.valueOf(age) > 0) {
            Enfants enfants = new Enfants(nom, Integer.valueOf(age));
            SharedPreferences sharedPreferences= this.getSharedPreferences("userIdentity", Context.MODE_PRIVATE);
            Users user = Tools.getUser(sharedPreferences);
            user.addEnfant(enfants);
            String token = sharedPreferences.getString("token", null);
            System.out.println("*** TOKEN = " +  token);
            UserService userservice = RetrofitClientInstance.getRetrofitInstance().create(UserService.class);
            userservice.update(token, user).enqueue(
                    new Callback<ResponseFormat>() {
                        @Override
                        public void onResponse(Call<ResponseFormat> call, Response<ResponseFormat> response) {
                            progressDialog.dismiss(); //dismiss progress dialog
                            System.out.println(response.body().toString());
                            if(response.body().getCode() == 200) {
//                            text.setText(response.body().code);
                                System.out.println("Body = " + response.body().getData().toString());
                                Tools.setUser(sharedPreferences, response.body().getData());
                                redirect();
                            }else {
                                Toast.makeText(CreationEnfant.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseFormat> call, Throwable t) {
                            // if error occurs in network transaction then we can get the error in this method.
                            System.out.println(t.getMessage());
                            progressDialog.dismiss();
                            Toast.makeText(CreationEnfant.this, t.getMessage(), Toast.LENGTH_LONG).show(); //dismiss progress dialog
                        }
                    });
        } else {
            progressDialog.dismiss();
            Toast.makeText(CreationEnfant.this, "Age ou nom invalide", Toast.LENGTH_LONG).show();
        }
    }
}