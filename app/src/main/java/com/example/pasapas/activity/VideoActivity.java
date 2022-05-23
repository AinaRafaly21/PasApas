package com.example.pasapas.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pasapas.R;
import com.example.pasapas.model.Cours;
import com.example.pasapas.tools.ParamQuiz;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class VideoActivity extends AppCompatActivity {
    CircularProgressIndicator progressIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Cours cours = (Cours) getIntent().getSerializableExtra("cours");
        progressIndicator = findViewById(R.id.circularVideo);
        setTilte(cours);
        displayVideo(cours);
        Button button= findViewById(R.id.passToQuiz);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirect(cours);
            }
        });
    }

    void setTilte(Cours cours) {

        TextView propos = findViewById(R.id.coursPropos);
        propos.setText(cours.getNom() + " - Niveau " + cours.getNiveau());

        TextView titre = findViewById(R.id.titreVideo);
        titre.setText(cours.getTitre());
    }

    void displayVideo(Cours cours) {
        WebView webView = findViewById(R.id.webview);
        String html = "<body style=\"width: 100%; height: 100%; overflow:hidden; overflow-x: hidden; overflow-y: hidden; position:absolute; \"> " ;
        html += "<iframe width=\"360\" height=\"315\" src=\""+
                cours.getUrl_video() +
                "\" title=\""+ cours.getTitre() +
                "\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; fullscreen\" allowfullscreen></iframe>";
        html += "</body>";
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webView.loadData(html, "text/html", "utf-8");
        progressIndicator.hide();
        webView.setVisibility(View.VISIBLE);
    }

    void redirect(Cours cours) {
        Intent intent = new Intent(VideoActivity.this, QuizActivity.class);
        ParamQuiz paramQuiz = new ParamQuiz(cours.getNom(), cours.getCategorie(), cours.getNiveau());
        intent.putExtra("paramQuiz", paramQuiz);
        startActivity(intent);
    }

}