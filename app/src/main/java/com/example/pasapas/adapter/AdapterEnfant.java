package com.example.pasapas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pasapas.R;
import com.example.pasapas.model.Enfants;

import java.util.ArrayList;

public class AdapterEnfant extends ArrayAdapter<Enfants> {

    public AdapterEnfant(Context context, ArrayList<Enfants> array){
        super(context, R.layout.list_enfant, R.id.enfants, array);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Enfants enfants = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_enfant, parent,false);
        }

        TextView nom = convertView.findViewById(R.id.nom);
        TextView age = convertView.findViewById(R.id.age);

        nom.setText(enfants.getNom());
        age.setText(enfants.getAge().toString());
        return super.getView(position, convertView, parent);
    }
}
