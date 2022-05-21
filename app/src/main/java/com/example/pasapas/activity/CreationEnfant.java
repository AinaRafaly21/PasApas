package com.example.pasapas.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pasapas.R;
import com.example.pasapas.model.Enfants;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreationEnfant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_enfant);
        Button button = findViewById(R.id.addKids);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirect();
            }
        });
    }

    void redirect(){
        Intent intent = new Intent(CreationEnfant.this, EnfantsActivity.class);
        startActivity(intent);
    }

    void add(String nom, String age) {
        Enfants enfants = new Enfants(nom, Integer.valueOf(age));

    }
}