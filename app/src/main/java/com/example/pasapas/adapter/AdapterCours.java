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
import com.example.pasapas.model.Cours;

import java.util.List;

public class AdapterCours extends ArrayAdapter<Cours> {

    public AdapterCours(Context context, List<Cours> arrayList) {
        super(context, R.layout.list_cours, R.id.cours, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Cours cours = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_cours, parent,false);
        }

        TextView niveau = convertView.findViewById(R.id.niveauCours);
        TextView categorie = convertView.findViewById(R.id.catCours);

        niveau.setText(cours.getTitre());
        categorie.setText(cours.getNom() + " - Niveau " + cours.getNiveau());

        return super.getView(position, convertView, parent);
    }
}
