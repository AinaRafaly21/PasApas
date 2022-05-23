package com.example.pasapas.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pasapas.MainActivity;
import com.example.pasapas.R;
import com.example.pasapas.adapter.AdapterEnfant;
import com.example.pasapas.databinding.ActivityEnfantsBinding;
import com.example.pasapas.model.Enfants;
import com.example.pasapas.model.Users;
import com.example.pasapas.tools.Tools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import java.util.ArrayList;

public class EnfantsActivity extends AppCompatActivity {
    ActivityEnfantsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEnfantsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Users users = Tools.getUser(getSharedPreferences("userIdentity", Context.MODE_PRIVATE));
        if(users.getEnfants() == null) users.setEnfants(new ArrayList<Enfants>());
        AdapterEnfant adapter = new AdapterEnfant(EnfantsActivity.this, users.getEnfants());
        binding.listview.setAdapter(adapter);
        binding.listview.setClickable(true);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                details(i);
            }
        });
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create();
            }
        });
    }

    void create(){
        Intent intent = new Intent(EnfantsActivity.this, CreationEnfant.class);
        startActivity(intent);
    }

    void details(int i){
        Intent intent = new Intent(EnfantsActivity.this, MainActivity.class);
        Tools.setKids(getSharedPreferences("userIdentity", Context.MODE_PRIVATE), i);
        startActivity(intent);
    }
}