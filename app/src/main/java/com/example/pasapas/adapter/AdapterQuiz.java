package com.example.pasapas.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.pasapas.R;
import com.example.pasapas.model.Qcm;

import java.util.ArrayList;


public class AdapterQuiz extends ArrayAdapter<Qcm> {
    Button btn1, btn2, btn3;
    public AdapterQuiz(Context context, ArrayList<Qcm> arrayList){
        super(context, R.layout.list_quiz, R.id.quiz, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Qcm qcm = getItem(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_quiz, parent,false);
        }

        ImageView imageView = convertView.findViewById(R.id.imageQuiz);
        Glide.with(convertView)
                .load(qcm.getImage())
                .placeholder(R.color.black)
                .override(300, 200)
                .into(imageView);

        TextView textView = convertView.findViewById(R.id.reponse);
        TextView question = convertView.findViewById(R.id.question);
        question.setText(qcm.getQuestion());

        btn1 = convertView.findViewById(R.id.btn1);
        btn1.setText(qcm.getChoix().get(0));
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                response(qcm, qcm.getChoix().get(0), textView);
            }
        });
        btn2 = convertView.findViewById(R.id.btn2);
        btn2.setText(qcm.getChoix().get(1));
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                response(qcm, qcm.getChoix().get(1), textView);
            }
        });
        btn3 = convertView.findViewById(R.id.btn3);
        btn3.setText(qcm.getChoix().get(2));
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                response(qcm, qcm.getChoix().get(2), textView);
            }
        });


        return super.getView(position, convertView, parent);
    }

    void response(Qcm qcm, String choix, View view) {
//        textView.setVisibility(View.VISIBLE);
        System.out.println("Reponse = " + qcm.getReponse() + " / Choix = " + choix);
        Dialog dialog = new Dialog(view.getContext());
        if(qcm.getReponse().compareToIgnoreCase(choix) == 0) dialog.setContentView(R.layout.true_layout);
        else dialog.setContentView(R.layout.false_layout);

        dialog.show();
    }
}

