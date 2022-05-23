package com.example.pasapas.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

//        ImageView imageView = convertView.findViewById(R.id.imageQuiz);
//        System.out.println("Qcm == " + qcm);
//        byte[] decodedString = Base64.decode(qcm.getImage().getBytes(), Base64.DEFAULT);
//        System.out.println("Decode ** " + decodedString);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//        System.out.println("Bitmap ** " + decodedByte);
//        imageView.setImageBitmap(Bitmap.createScaledBitmap(decodedByte, 120, 120, false));

        TextView textView = convertView.findViewById(R.id.reponse);

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

    void response(Qcm qcm, String choix, TextView textView) {
        textView.setVisibility(View.VISIBLE);
        System.out.println("Reponse = " + qcm.getReponse() + " / Choix = " + choix);
        if(qcm.getReponse().compareToIgnoreCase(choix) == 0) textView.setText("Bravo, c'est vrai!!");
        else textView.setText("Ooh non, c'est faux");
    }
}

