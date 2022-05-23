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

import java.util.ArrayList;
import java.util.List;


public class AdapterMatiere extends ArrayAdapter<String> {

    public AdapterMatiere(Context context, List<String> array) {
        super(context, R.layout.list_matiere, R.id.matieres, array);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String mat = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_matiere, parent,false);
        }
        TextView textView = convertView.findViewById(R.id.nomMatiere);
        textView.setText(mat);
        return super.getView(position, convertView, parent);
    }
}
